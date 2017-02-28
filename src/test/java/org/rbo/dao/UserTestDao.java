package org.rbo.dao;

import org.junit.Assert;
import org.rbo.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author vitalii.levash
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTestDao {

   @Autowired
    private UserDao userDao;

    @Test
    public void testGetLogin() throws Exception{
        User user = userDao.findByUsername("leonard");
        Assert.assertTrue(user.getUsername().equals("leonard"));

    }
}
