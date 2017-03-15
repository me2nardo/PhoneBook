package org.rbo.exception.Auth;

/**
 * Created by leo on 28.02.17.
 */
public class UserExistsException extends RuntimeException{

    public UserExistsException(String name, String email) {
        super(String.format("User exists %s,%s",name,email));
    }
}
