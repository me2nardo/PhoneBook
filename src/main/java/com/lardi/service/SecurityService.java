package com.lardi.service;

import com.lardi.model.User;

/**
 * @author vitalii.levash
 */
public interface SecurityService {
    int getUserId();
    String getUserLogin();
    User getUserDetails();
}
