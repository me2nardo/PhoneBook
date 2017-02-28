package org.rbo.security;

import org.rbo.dao.UserDao;
import org.rbo.exception.Auth.impl.UserNotExistsException;
import org.rbo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Authenticate a user from the database.
 * @author vitalii.levash
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final Logger LOG = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LOG.debug("Authenticating {}", username);
        Optional<User> user = userDao.findByUsername(username);

        return user.map(u-> new org.springframework.security.core.userdetails.User(u.getUsername(),
                u.getPassword(),u.isEnabled(),
                u.isAccountNonExpired(),
                u.isCredentialsNonExpired(),
                u.isAccountNonLocked(),
                u.getAuthorities())).orElseThrow(()-> new UserNotExistsException(username));
    }
}
