package com.lardi.dao;

import com.lardi.model.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *@author vitalii.levash
 */
public interface PhoneBookDao extends JpaRepository<PhoneBook,Integer> {
    @Query("SELECT b FROM PhoneBook b WHERE b.email=:uemail")
    PhoneBook findByMail(@Param("uemail") String email);
}
