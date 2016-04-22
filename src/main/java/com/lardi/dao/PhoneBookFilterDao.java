package com.lardi.dao;

import com.lardi.dto.FilterCriteria;
import com.lardi.model.PhoneBook;

import java.util.List;

/**
 * Created by leo on 22.04.2016.
 */
public interface PhoneBookFilterDao {
    List<PhoneBook> filterPhoneItems(FilterCriteria filterCriteria);
}
