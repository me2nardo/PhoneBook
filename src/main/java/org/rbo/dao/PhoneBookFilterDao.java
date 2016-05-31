package org.rbo.dao;

import org.rbo.dto.FilterCriteria;
import org.rbo.model.PhoneBook;

import java.util.List;

/**
 * Created by leo on 22.04.2016.
 */
public interface PhoneBookFilterDao {
    List<PhoneBook> filterPhoneItems(FilterCriteria filterCriteria);
}
