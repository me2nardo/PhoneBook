package org.rbo.controller;

import org.rbo.exception.Auth.UserNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author vitalii.levash
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(UserNotExistsException.class)
    public ResponseEntity handleUserNotExists(UserNotExistsException e){
        return  ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());

    }

}
