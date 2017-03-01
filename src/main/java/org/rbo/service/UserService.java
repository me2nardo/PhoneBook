package org.rbo.service;

import org.rbo.model.User;

import java.util.List;

/**
 * Created by leo on 28.02.17.
 */
public interface UserService {
    User registerUser(User user);
    void updateUser(User user);
    void deleteUser(String username);
    List<User> findAllUsers();
    User findUserById(long id);
}
