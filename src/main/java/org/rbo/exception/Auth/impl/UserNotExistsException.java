package org.rbo.exception.Auth.impl;

import org.rbo.exception.Auth.AuthException;

/**
 * Created by vitalii.levash on 18.04.2016.
 */
public class UserNotExistsException extends AuthException {
    public UserNotExistsException(){
        super("ID for user not exists");}
    public UserNotExistsException(final int login){
        super("ID for user not exists"+login);
    }
}
