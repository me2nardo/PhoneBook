package org.rbo.controller;

import org.rbo.exception.Auth.impl.LoginExistsException;
import org.rbo.exception.Auth.impl.UserNotExistsException;
import org.rbo.exception.web.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author vitalii.levash
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundHandle(){
        return "404";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView exception(Exception exception, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("info", "some info");
        return modelAndView;
    }

    @ExceptionHandler(LoginExistsException.class)
    public String loginExistsHandler(LoginExistsException e, ModelMap model){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("info",e.getMessage());
        return "error";
    }

    @ExceptionHandler(UserNotExistsException.class)
    public ResponseEntity handleUserNotExists(UserNotExistsException e){
        return  ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());

    }

}
