package com.lardi.service;

import com.lardi.model.PhoneBook;

import java.util.List;

/**
 * @author vitalii.levash
 */
public interface PhoneBookService {
    void addPhoneItem(PhoneBook phoneBook);
    List<PhoneBook> getUserPhoneList(int user_id);
    void deletePhoneItem(final int user_id);
    void editPhoneItem(PhoneBook phoneBook);
}
