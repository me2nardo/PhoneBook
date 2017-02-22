package org.rbo.dao;

import org.rbo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author vitalii.levash
 */
public interface UserDao extends JpaRepository<User,Integer> {

    User findByUserName(String username);
}
