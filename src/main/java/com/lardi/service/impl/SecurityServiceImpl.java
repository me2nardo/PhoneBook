package com.lardi.service.impl;

import com.lardi.model.User;
import com.lardi.service.SecurityService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by leo on 18.04.2016.
 */
@Service
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
