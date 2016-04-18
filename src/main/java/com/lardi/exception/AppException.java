package com.lardi.exception;

/**
 * @author vitalii.levash
 */
public class AppException extends RuntimeException {

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