package com.uc.ecommerce.core.validator.paramater;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DuplicateEmailValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DuplicateEmail {

    String message() default "duplicate email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
