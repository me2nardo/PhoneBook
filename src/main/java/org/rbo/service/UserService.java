package org.rbo.service;

import org.rbo.model.User;

import java.util.List;

/**
 * Created by vitalii.levash on 18.04.2016.
 */
public interface UserService {
    void addUser(User user) ;
    void delete(final int id);
    User getById(final int id);
    List<User> getUserList();
    User findByLogin(final String user);
}
