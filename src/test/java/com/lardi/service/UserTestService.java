package com.lardi.service;

import com.lardi.Application;
import com.lardi.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vitalii.levash on 19.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
@Transactional
@Rollback
public class UserTestService {
    @Autowired
    private UserService userService;

    @Test
    public void testServiceGetLogin() throws Exception {
        User user = userService.findByLogin("leo");
        Assert.assertEquals(user.getLogin(),"leo");
    }
}
