package org.rbo.service;

import org.rbo.model.PhoneBook;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author vitalii.levash
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/data-service.sql")
public class PhoneBookServiceTest {
    @Autowired
    private PhoneBookService phoneBookService;

    @Test
    public void getPhoneListTestService() throws Exception{
        List<PhoneBook> list = phoneBookService.getUserPhoneList(1);
        Assert.assertTrue(list.size()>0);
    }
}
