package com.lardi.util.validate.impl;

import com.lardi.util.validate.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author vitalii.levash
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone phone) {

    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        if (phone.length()==0) return true; //Empty for mob;
        if (phone.matches("^\\+38\\(\\d{3}\\)\\d{7}")){
            return true;
        }else {
            return false;
        }
    }
}
