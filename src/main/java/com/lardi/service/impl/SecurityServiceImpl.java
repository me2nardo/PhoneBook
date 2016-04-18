package com.lardi.service.impl;

import com.lardi.model.User;
import com.lardi.service.SecurityService;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by leo on 18.04.2016.
 */
public class SecurityServiceImpl implements SecurityService {

    @Override
    public int getUserId() {
        return getUserDetails().getId();
    }


    @Override
    public String getUserLogin() {
        return getUserDetails().getLogin();
    }

    @Override
    public User getUserDetails() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
