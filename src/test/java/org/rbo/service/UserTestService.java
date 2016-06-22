package org.rbo.service;


import org.rbo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by vitalii.levash on 19.04.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/data-service.sql")
public class UserTestService {
    @Autowired
    private UserService userService;

    @Test
    public void testServiceGetLogin() throws Exception {
        User user = userService.findByLogin("leonard");
        Assert.assertEquals(user.getLogin(),"leonard");
    }
}
