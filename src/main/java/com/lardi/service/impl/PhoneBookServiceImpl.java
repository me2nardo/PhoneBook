package com.lardi.service.impl;

import com.lardi.dao.PhoneBookDao;
import com.lardi.dto.FilterCriteria;
import com.lardi.model.PhoneBook;
import com.lardi.service.PhoneBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



/**
 * Created by vitalii.levash on 19.04.2016.
 */
@Service
public class PhoneBookServiceImpl implements PhoneBookService {
    @Autowired
    private PhoneBookDao phoneBookDao;

    @Override
    @Transactional
    public void addPhoneItem(PhoneBook phoneBook) {
       phoneBookDao.saveAndFlush(phoneBook);
    }

    @Override
    public List<PhoneBook> getUserPhoneList(int user_id) {
        return phoneBookDao.findByUser(user_id);
    }

    @Override
    public void deletePhoneItem(final int user_id) {
      phoneBookDao.delete(user_id);
    }

    @Override
    public PhoneBook getById(int id) {
        return phoneBookDao.getOne(id);
    }

    @Override
    public List<PhoneBook> findPhones(FilterCriteria filterCriteria) {
        return null;
    }
}
