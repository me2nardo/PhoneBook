package org.rbo.service;

import org.rbo.Application;
import org.rbo.model.PhoneBook;
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
 * Created by vitalii.levash on 19.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
@Transactional
@Rollback
public class PhoneBookServiceTest {
    @Autowired
    private PhoneBookService phoneBookService;

    @Test
    public void getPhoneListTestService() throws Exception{
        List<PhoneBook> list = phoneBookService.getUserPhoneList(1);
        Assert.assertTrue(list.size()>0);
    }
}
