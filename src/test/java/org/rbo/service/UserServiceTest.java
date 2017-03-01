package org.rbo.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rbo.Application;
import org.rbo.dao.UserDao;
import org.rbo.exception.Auth.impl.UserNotExistsException;
import org.rbo.model.Authority;
import org.rbo.model.User;
import org.rbo.service.dto.UserDto;
import org.rbo.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.is;

/**
 * Test for UserService
 * @author vitalii.levash
 * @see org.rbo.service.UserService
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    /**
     * Test register new user with default authority
     */
    @Test
    public void registerUserTest() throws Exception{

        UserDto userDto = new UserDto();
        userDto.setName("sName");
        userDto.setLastName("sLastName");
        userDto.setUsername("sUserName");
        userDto.setFirstName("sFirstName");
        userDto.setPassword("sPassword");
        userDto.setEmail("soma@demo.ua");


        User user = UserMapper.INSTANCE.toUser(userDto);

        User returnUser = userService.registerUser(user);

        Assert.assertThat(returnUser.getUsername(),is("sUserName"));

        Optional<User> checkUser = userDao.findOneByUsername("sUserName");
        Assert.assertTrue(checkUser.isPresent());

        User authoritiesCheck = userDao.findOneWithAuthoritiesByUsername("sUserName").get();

        long count = authoritiesCheck.getAuthority().stream().count();

        Assert.assertThat(count,is(1L));
    }

    /**
     * Test register user with custom authorities
     */
    @Test
    public void registerUserWithAuthorities() throws Exception{
        UserDto userDto = new UserDto();
        userDto.setName("sNameDefault");
        userDto.setLastName("sLastName");
        userDto.setUsername("sDefaultUserName");
        userDto.setFirstName("sFirstName");
        userDto.setPassword("sPassword");
        userDto.setEmail("somaDefault@demo.ua");

        Set<String> authorities = new HashSet<>();
        authorities.add("ROLE_ADMIN");
        authorities.add("ROLE_USER");

        userDto.setAuthorities(authorities);

        User user = UserMapper.INSTANCE.toUser(userDto);
        user.setAuthority(UserMapper.INSTANCE.authoritiesFromStrings(authorities));

        User userCheck = userService.registerUser(user);

        Assert.assertThat(userCheck.getUsername(),is("sDefaultUserName"));

        User authoritiesCheck = userDao.findOneWithAuthoritiesByUsername("sDefaultUserName").get();

        long count = authoritiesCheck.getAuthority().stream().count();

        Assert.assertThat(count,is(2L));
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
