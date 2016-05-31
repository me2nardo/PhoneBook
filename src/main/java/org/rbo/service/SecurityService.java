package org.rbo.service;

import org.rbo.model.User;

/**
 * @author vitalii.levash
 */
public interface SecurityService {
    int getUserId();
    String getUserLogin();
    User getUserDetails();
}
