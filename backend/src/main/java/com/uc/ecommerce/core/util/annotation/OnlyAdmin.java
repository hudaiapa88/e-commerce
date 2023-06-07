package com.uc.ecommerce.core.util.annotation;


import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@IsAuthenticated
@PreAuthorize("hasRole('ADMIN')")
public @interface OnlyAdmin {
}
