package org.rbo.service.impl;

import org.rbo.dao.PhoneBookDao;
import org.rbo.dto.FilterCriteria;
import org.rbo.model.PhoneBook;
import org.rbo.service.PhoneBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



/**
 * @author vitalii.levash
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
    @Transactional(readOnly = true)
    public List<PhoneBook> getUserPhoneList(int user_id) {
        return phoneBookDao.findByUser(user_id);
    }

    @Override
    public void deletePhoneItem(final int user_id) {
      phoneBookDao.delete(user_id);
    }

    @Override
    @Transactional(readOnly = true)
    public PhoneBook getById(int id) {
        return phoneBookDao.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PhoneBook> findPhones(FilterCriteria filterCriteria) {
        return phoneBookDao.filterPhoneItems(filterCriteria);
    }

    @Override
    public PhoneBook getByEmail(String email) {
        return phoneBookDao.findByMail(email);
    }
}
