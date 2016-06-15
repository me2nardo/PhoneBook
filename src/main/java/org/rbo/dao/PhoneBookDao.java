package org.rbo.dao;

import org.rbo.model.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *@author vitalii.levash
 */
public interface PhoneBookDao extends JpaRepository<PhoneBook,Integer>,PhoneBookFilterDao {
    @Query("SELECT b FROM PhoneBook b WHERE b.email=:uemail")
    PhoneBook findByMail(@Param("uemail") String email);

    @Query("SELECT u.phoneBookList FROM User u WHERE u.id=:uid")
    List<PhoneBook> findByUser(@Param("uid") final int user_id);

}
