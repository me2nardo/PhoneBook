package com.lardi.dao;

import com.lardi.Application;
import com.lardi.model.PhoneBook;
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

import java.util.List;

/**
 * Created by vitalii.levash on 18.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
@Transactional
@Rollback
public class PhoneBookTestDao {
    @Autowired
    private PhoneBookDao phoneBookDao;
    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    public void addPhoneItem() throws Exception{
        User user = userDao.findOne(1);
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.setEmail("mr.roo@gmail.com");
        phoneBook.setAddress("Kiev");
        phoneBook.setLastname("Smith");
        phoneBook.setName("John");
        phoneBook.setSurname("Smith");
        phoneBook.setMobPhone("+38097047703");

        phoneBook.setUser(user);

        phoneBookDao.save(phoneBook);
        PhoneBook uphoneBook = phoneBookDao.findByMail("mr.roo@gmail.com");

        Assert.assertEquals(phoneBook.getId(),uphoneBook.getId());
    }

    @Test
    public void getPhoneItem() throws Exception{
        PhoneBook phoneBook = phoneBookDao.findOne(1);
        Assert.assertEquals(phoneBook.getEmail(),"alfa@alfa.com");
    }

    @Test
    public void getPhoneItems() throws  Exception{
       List<PhoneBook> phoneBook = phoneBookDao.findAll();
       Assert.assertTrue(phoneBook.size()>0);
       System.out.println("Size:"+phoneBook.size());
    }
}
