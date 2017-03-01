package org.rbo.security;

import org.apache.commons.lang3.RandomStringUtils;
import org.rbo.dao.PersistentTokenDao;
import org.rbo.dao.UserDao;
import org.rbo.model.PersistentToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.CookieTheftException;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * <p>Taken from <a href="http://jaspan.com/improved_persistent_login_cookie_best_practice">Improved Persistent Login Cookie
 * Best Practice</a></p>
 * @author vitalii.levash
 */
@Service
public class PersistentTokenService extends AbstractRememberMeServices {

    private final Logger LOG = LoggerFactory.getLogger(PersistentTokenService.class);


    private static final int TOKEN_VALIDITY_DAYS = 7;
    private final int TOKEN_SIZE = 20;

    private static final int TOKEN_VALIDITY_SECONDS = 60 * 60 * 24 * TOKEN_VALIDITY_DAYS;

    private UserDao userDao;
    private PersistentTokenDao persistentTokenDao;

    @Autowired
    public PersistentTokenService(String key, UserDetailsService userDetailsService,
                                  UserDao userDao,PersistentTokenDao persistentTokenDao) {
        super(key, userDetailsService);
        this.userDao = userDao;
        this.persistentTokenDao = persistentTokenDao;
    }

    @Override
    protected void onLoginSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        String login = authentication.getName();

        LOG.debug("Creating new persistent login for user {}", login);
        PersistentToken token = userDao.findOneByUsername(login).map(u -> {
            PersistentToken t = new PersistentToken();
            t.setSeries(RandomStringUtils.randomAlphanumeric(TOKEN_SIZE));
            t.setUser(u);
            t.setTokenValue(RandomStringUtils.randomAlphanumeric(TOKEN_SIZE));
            t.setTokenDate(LocalDate.now());
            t.setIpAddress(httpServletRequest.getRemoteAddr());
            t.setUserAgent(httpServletRequest.getHeader("User-Agent"));
            return t;
        }).orElseThrow(() -> new UsernameNotFoundException("User " + login + " was not found in the database"));
        try {
            persistentTokenDao.saveAndFlush(token);
            addCookie(token, httpServletRequest, httpServletResponse);
        } catch (DataAccessException e) {
            LOG.error("Failed to save persistent token ", e);
        }
    }

    @Override
    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String rememberMeCookie = extractRememberMeCookie(request);
        if (rememberMeCookie != null && rememberMeCookie.length() != 0) {
            try {
                String[] cookieTokens = decodeCookie(rememberMeCookie);
                PersistentToken token = getPersistentToken(cookieTokens);
                persistentTokenDao.delete(token);
            } catch (InvalidCookieException ice) {
                LOG.info("Invalid cookie, no persistent token could be deleted", ice);
            } catch (RememberMeAuthenticationException rmae) {
                LOG.debug("No persistent token found, so no token could be deleted", rmae);
            }
        }
        super.logout(request, response, authentication);
    }


    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws RememberMeAuthenticationException, UsernameNotFoundException {
        PersistentToken token = getPersistentToken(cookieTokens);
        String login = token.getUser().getUsername();

        // Token also matches, so login is valid. Update the token value, keeping the *same* series number.
        LOG.debug("Refreshing persistent login token for user '{}', series '{}'", login, token.getSeries());
        token.setTokenDate(LocalDate.now());
        token.setTokenValue(RandomStringUtils.randomAlphanumeric(TOKEN_SIZE));
        token.setIpAddress(httpServletRequest.getRemoteAddr());
        token.setUserAgent(httpServletRequest.getHeader("User-Agent"));
        try {
            persistentTokenDao.saveAndFlush(token);
            addCookie(token, httpServletRequest, httpServletResponse);
        } catch (DataAccessException e) {
            LOG.error("Failed to update token: ", e);
            throw new RememberMeAuthenticationException("Autologin failed due to data access problem", e);
        }
        return getUserDetailsService().loadUserByUsername(login);
    }


    private PersistentToken getPersistentToken(String[] cookieTokens) {
        if (cookieTokens.length != 2) {
            throw new InvalidCookieException("Cookie token did not contain " + 2 +
                    " tokens, but contained '" + Arrays.asList(cookieTokens) + "'");
        }
        String presentedSeries = cookieTokens[0];
        String presentedToken = cookieTokens[1];
        PersistentToken token = persistentTokenDao.findOne(presentedSeries);

        if (token == null) {
            throw new RememberMeAuthenticationException("No persistent token found for series id: " + presentedSeries);
        }


        LOG.info("presentedToken={} / tokenValue={}", presentedToken, token.getTokenValue());
        if (!presentedToken.equals(token.getTokenValue())) {
            persistentTokenDao.delete(token);
            throw new CookieTheftException("Invalid remember-me token (Series/token) mismatch. Implies previous " +
                    "cookie theft attack.");
        }

        if (token.getTokenDate().plusDays(TOKEN_VALIDITY_DAYS).isBefore(LocalDate.now())) {
            persistentTokenDao.delete(token);
            throw new RememberMeAuthenticationException("Remember-me login has expired");
        }
        return token;
    }


    private void addCookie(PersistentToken token, HttpServletRequest request, HttpServletResponse response) {
        setCookie(
                new String[]{token.getSeries(), token.getTokenValue()},
                TOKEN_VALIDITY_SECONDS, request, response);
    }
}
