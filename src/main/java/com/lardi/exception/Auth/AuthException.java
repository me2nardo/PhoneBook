package com.lardi.exception.Auth;

import com.lardi.exception.AppException;

/**
 * Created by vitalii.levash on 18.04.2016.
 */
public class AuthException extends AppException {
    public AuthException(final String message) {
        super(message);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }
}
