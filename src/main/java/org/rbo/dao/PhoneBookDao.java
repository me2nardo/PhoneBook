package org.rbo.dao;

import org.rbo.model.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *@author vitalii.levash
 */
public interface PhoneBookDao extends JpaRepository<PhoneBook,Integer> {

    PhoneBook findByEmail(@Param("uemail") String email);

    List<PhoneBook> findByUser(@Param("uid") final int user_id);

}
