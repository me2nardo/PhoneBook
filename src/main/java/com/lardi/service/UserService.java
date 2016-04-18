package com.lardi.service;

import com.lardi.exception.Auth.impl.LoginExistsException;
import com.lardi.exception.Auth.impl.UserNotExistsException;
import com.lardi.model.User;

import java.util.List;

/**
 * Created by vitalii.levash on 18.04.2016.
 */
public interface UserService {
    void addUser(User user) throws LoginExistsException;
    void delete(final int id)throws UserNotExistsException;
    User getById(final int id) throws UserNotExistsException;
    List<User> getUserList();
}
