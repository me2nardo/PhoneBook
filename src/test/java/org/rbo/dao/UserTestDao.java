package org.rbo.dao;

import org.rbo.model.PhoneBook;
import org.rbo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author vitalii.levash
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTestDao {

    private final String USER2TEST="leonard";

   @Autowired
    private UserDao userDao;

    @Test
    public void testGetLogin() throws Exception{
        User user = userDao.findByLogin(USER2TEST);
        List<PhoneBook> list =user.getPhoneBookList();
        Assert.assertEquals(user.getLogin(),USER2TEST);
        Assert.assertTrue(list.size()>0);
    }
}
