package org.rbo.service;

import org.rbo.model.PhoneBook;

import java.util.List;

/**
 * Created by leo on 01.06.17.
 */
public interface PhoneBookService {

    void createItem(PhoneBook phoneBook);
    void updateItem(PhoneBook phoneBook);
    PhoneBook findById(long id);
    void delete(long id);
    List<PhoneBook> findAll();

}
