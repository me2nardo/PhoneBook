package org.rbo.util.validate.impl;

import org.rbo.util.validate.Login;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by vitalii.levash on 20.04.2016.
 */
public class LoginValidator implements ConstraintValidator<Login, String> {

    @Override
    public void initialize(Login login) {

    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        if(login == null){
            return false;
        }
        if (login.matches("\\w+")) {
            return true;
        }else{
            return false;
        }

    }
}
