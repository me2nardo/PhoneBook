package org.rbo.dao;

import org.rbo.Application;
import org.rbo.model.PhoneBook;
import org.rbo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;


/**
 * Created by vitalii.levash on 18.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class UserTestDao {

   @Autowired
    private UserDao userDao;

    @Test
    public void testGetLogin() throws Exception{
        User user = userDao.findByLogin("leonard");
        List<PhoneBook> list =user.getPhoneBookList();
        System.out.println(user.getLogin());
        Assert.assertEquals(user.getLogin(),"leonard");
        Assert.assertTrue(list.size()>0);

    }
}
