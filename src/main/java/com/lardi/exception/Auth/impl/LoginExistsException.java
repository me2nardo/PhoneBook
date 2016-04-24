package com.lardi.exception.Auth.impl;

import com.lardi.exception.Auth.AuthException;

/**
 * Created by vitalii.levash on 18.04.2016.
 */
public class LoginExistsException extends AuthException {
    public LoginExistsException(){
        super("Login for user exists");
    }
    public LoginExistsException(final String login){
        super(String.format("Login '%s' already exists in DB.", login));
    }
}
