package org.rbo.util.validate;

import org.rbo.util.validate.impl.LoginValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by vitalii.levash on 20.04.2016.
 */
@Documented
@Constraint(validatedBy = LoginValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {

    String message() default "{Login}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
