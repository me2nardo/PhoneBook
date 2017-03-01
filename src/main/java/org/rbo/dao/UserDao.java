package org.rbo.dao;

import org.rbo.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

/**
 * <p>Spring Data JPA repository for the User entity.</p>
 * @author vitalii.levash
 */
public interface UserDao extends JpaRepository<User,Long> {

    /**
     * Find user by his username.
     * @param username requested username from user entity
     * @return Optional User
     */
    Optional<User> findOneByUsername(String username);

    /**
     * Find user by his username with Authorities
     * @param username requested username from user entity
     * @return Optional User
     */
    @EntityGraph(attributePaths = "authority")
    Optional<User> findOneWithAuthoritiesByUsername(String username);

    /**
     * Find user by his username and email. Using for registration.
     * @param username requested username from user entity
     * @param email requested email from user entity
     * @return Optional User. Check for already registered.
     */
    Optional<User> findOneByUsernameOrEmail(String username,String email);
}
