package com.lardi.controller.handler;

import com.lardi.exception.AppException;
import com.lardi.exception.Auth.impl.LoginExistsException;
import com.lardi.exception.Auth.impl.UserNotExistsException;
import com.lardi.exception.web.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author vitalii.levash
 */
@ControllerAdvice(basePackages = {"com.lardi.controller"})
public class GlobalControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundHandle(){
        return "404";
    }

    @ExceptionHandler(LoginExistsException.class)
    public String loginExistsHandler(LoginExistsException e, ModelMap model){
        model.addAttribute("info",e.getMessage());
        return "userform";
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String deniedHandler(AccessDeniedException e){
        return "403";
    }
    @ExceptionHandler(BadCredentialsException.class)
    public String badCredentialHandler(BadCredentialsException e){
        return "login";
    }
    @ExceptionHandler({UsernameNotFoundException.class, org.springframework.security.core.AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unauthorizedHandler(org.springframework.security.core.AuthenticationException e){
        return "login";
    }

}
