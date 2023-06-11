package com.uc.ecommerce.core.validator.paramater;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = DuplicateUsernameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DuplicateUsername {

    String message() default "duplicate username";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
