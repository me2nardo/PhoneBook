package com.lardi.service;

import com.lardi.exception.Auth.impl.LoginExistsException;
import com.lardi.exception.Auth.impl.UserNotExistsException;
import com.lardi.model.User;

import java.util.List;

/**
 * Created by vitalii.levash on 18.04.2016.
 */
public interface UserService {
    void addUser(User user) ;
    void delete(final int id);
    User getById(final int id);
    List<User> getUserList();
}
