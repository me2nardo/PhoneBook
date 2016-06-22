package org.rbo.dao;

import org.rbo.model.PhoneBook;
import org.rbo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rbo.util.RboStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author vitalii.levash
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PhoneBookTestDao {

    @Autowired
    private PhoneBookDao phoneBookDao;
    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    public void addPhoneItem() throws Exception{
        User user = userDao.findByLogin(RboStrings.TESTUSER);

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.setEmail(RboStrings.TESTEMAIL);
        phoneBook.setAddress("Kiev");
        phoneBook.setLastname("Smith");
        phoneBook.setName("John");
        phoneBook.setSurname("Smith");
        phoneBook.setMobPhone("+38(066)1234567");
        phoneBook.setPhone("+38(066)1234567");

        phoneBook.setUser(user);

        phoneBookDao.save(phoneBook);
        PhoneBook uphoneBook = phoneBookDao.findByMail(RboStrings.TESTEMAIL);

        Assert.assertEquals(phoneBook.getId(),uphoneBook.getId());
    }

    @Test
    public void getPhoneItem() throws Exception{
        PhoneBook phoneBook = phoneBookDao.findOne(1);
        Assert.assertEquals(phoneBook.getEmail(),"ajohn@gmail.com");
    }

    @Test
    public void getPhoneItems() throws  Exception{
       List<PhoneBook> phoneBook = phoneBookDao.findAll();
       Assert.assertTrue(phoneBook.size()>0);
       System.out.println("Size:"+phoneBook.size());
    }
    @Test
    public void getByUser()throws Exception{
        List<PhoneBook> list  = phoneBookDao.findByUser(1);
        Assert.assertTrue(list.size()>0);
    }
}
