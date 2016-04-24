package com.lardi.service;

import com.lardi.dto.FilterCriteria;
import com.lardi.model.PhoneBook;

import java.util.List;

/**
 * @author vitalii.levash
 */
public interface PhoneBookService {
    void addPhoneItem(PhoneBook phoneBook);
    List<PhoneBook> getUserPhoneList(int user_id);
    void deletePhoneItem(final int user_id);
    PhoneBook getById(final int id);
    PhoneBook getByEmail(final String email);
    List<PhoneBook> findPhones(FilterCriteria filterCriteria);
}
