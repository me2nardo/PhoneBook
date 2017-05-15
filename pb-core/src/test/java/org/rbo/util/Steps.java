package org.rbo.util;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by leo on 15.05.17.
 */
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Steps {
}
