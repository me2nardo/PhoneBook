package com.lardi.exception;

/**
 * Created by vitalii.levash on 18.04.2016.
 */
public class AppException extends Exception {

    public AppException(final String message) {
        super(message);
    }

    public AppException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AppException(final Throwable cause) {
        super(cause);
    }
}