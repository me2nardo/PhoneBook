package org.rbo.dao;

import org.rbo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

/**
 * @author vitalii.levash
 */
public interface UserDao extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
}
