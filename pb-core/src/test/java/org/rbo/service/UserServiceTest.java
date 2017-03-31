package org.rbo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rbo.dao.AuthorityDao;
import org.rbo.dao.UserDao;
import org.rbo.exception.Auth.UserExistsException;
import org.rbo.model.Authority;
import org.rbo.model.User;
import org.rbo.service.dto.UserDto;
import org.rbo.service.impl.UserServiceImpl;
import org.rbo.service.mapper.UserMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import java.util.Optional;
import java.util.Set;

import static java.util.Optional.of;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Test for UserService
 * @author vitalii.levash
 * @see org.rbo.service.UserService
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserService.class)
public class UserServiceTest {

    @InjectMocks private UserService userService = new UserServiceImpl();
    @Mock private UserDao userDao;
    @Mock private AuthorityDao authorityDao;
    @Mock private PasswordEncoder passwordEncoder;

    private UserDto userDto = new UserDto();

    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
        /**
         * Set user
         */
        userDto.setName("sName");
        userDto.setLastName("sLastName");
        userDto.setUsername("sUserName");
        userDto.setFirstName("sFirstName");
        userDto.setPassword("sPassword");
        userDto.setEmail("soma@demo.ua");
        /**
         * Set demo authority
         */
        Authority authority = new Authority();
        authority.setName("ROLE_USER");
        when(authorityDao.findOne("ROLE_USER")).thenReturn(authority);
        when(passwordEncoder.encode(any())).thenReturn("DEMO_PASSWORD");
    }
    /**
     * Test register new user with default authority
     */
    @Test
    public void registerUserTest() throws Exception{

        User user = UserMapper.INSTANCE.toUser(userDto);

        when(userDao.findOneByUsernameOrEmail(any(),any())).thenReturn(Optional.empty());

        User returnUser = userService.registerUser(user);

        assertNotNull(returnUser);
        assertThat(returnUser.getUsername(),is("sUserName"));
        assertEquals(returnUser.getAuthority().size(),1);
        assertEquals(returnUser.getAuthority().stream().findFirst().get().getName(),"ROLE_USER");
    }

    /**
     * Test user exists
     */
    @Test(expected = UserExistsException.class)
    public void registerUserFailed() throws Exception{
        User user = UserMapper.INSTANCE.toUser(userDto);

        when(userDao.findOneByUsernameOrEmail(any(),any())).thenReturn(of(user));

        User returnUser = userService.registerUser(user);
    }

    /**
     * Test register user with custom authorities
     */
    @Test
    public void registerUserWithAuthorities() throws Exception{

        Set<String> authorities = new HashSet<>();
        authorities.add("ROLE_ADMIN");
        authorities.add("ROLE_USER");

        userDto.setAuthorities(authorities);

        User user = UserMapper.INSTANCE.toUser(userDto);
        user.setAuthority(UserMapper.INSTANCE.authoritiesFromStrings(authorities));

        when(userDao.findOneByUsernameOrEmail(any(),any())).thenReturn(Optional.empty());

        User userCheck = userService.registerUser(user);

        assertNotNull(userCheck);
        assertThat(userCheck.getUsername(),is("sUserName"));
        assertEquals(userCheck.getAuthority().size(),2);
    }

    /**
     * Test update user
     */
    /*
    @Test
    public void updateUserTest() throws Exception{

        userService.updateUser("sJohn","sLastName","sFirstName","sName");

        User check = userDao.findOneByUsername("sJohn").get();
        Assert.assertThat(check.getName(),is("sName"));

    }
 */
    /**
     * Test update user failed
     */
    /*
    @Test(expected = UserNotExistsException.class)
    public void updateUserTestFailed() throws Exception{

        userService.updateUser("sJohn21","sLastName","sFirstName","sName");
    }
    */

}
