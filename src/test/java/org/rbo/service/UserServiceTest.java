package org.rbo.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rbo.Application;
import org.rbo.dao.UserDao;
import org.rbo.model.User;
import org.rbo.service.dto.UserDto;
import org.rbo.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Optional;

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
    }
}
