package com.lardi.dao;

import com.lardi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author vitalii.levash
 */
public interface UserDao extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u WHERE u.login=:ulogin")
    User findByLogin(@Param("ulogin") String login);
}
