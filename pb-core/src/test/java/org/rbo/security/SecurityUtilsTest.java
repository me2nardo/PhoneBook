package org.rbo.security;

import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 *  Test class for SecurityUtils
 */
public class SecurityUtilsTest {

    @Test
    public void testGetCurrentUserLogin() throws Exception{
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("admin","admin"));
        SecurityContextHolder.setContext(securityContext);

        String login = SecurityUtils.getCurrentUserLogin();
        assertThat(login).isEqualTo("admin");
    }

    @Test
    public void testIsAuthenticated() throws Exception{
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("admin","admin"));
        SecurityContextHolder.setContext(securityContext);

        boolean isAuthenticated = SecurityUtils.isAuthenticated();
        assertTrue(isAuthenticated);
    }

    @Test
    public void testIsCurrentUserInRole() throws Exception{
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("admin","admin",authorities));
        SecurityContextHolder.setContext(securityContext);

        boolean isCurrentUserInRole = SecurityUtils.isCurrentUserInRole("ROLE_MANAGER");
        assertFalse(isCurrentUserInRole);
    }
}
