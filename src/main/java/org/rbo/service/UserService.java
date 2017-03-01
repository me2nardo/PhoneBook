package org.rbo.service;

import org.rbo.model.User;

import java.util.List;

/**
 * @author vitalii.levash
 */
public interface UserService {
    User registerUser(User user);
    void updateUser(User user);
    void deleteUser(String username);
    List<User> findAllUsers();
    User findUserById(long id);
    User findUserByName(String username);
}
