package com.uc.ecommerce.core.validator.paramater;

import com.uc.ecommerce.service.imp.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DuplicateUsernameValidator implements ConstraintValidator<DuplicateUsername, String> {
    private final AccountService accountService;

    @Override
    public void initialize(DuplicateUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !accountService.existByUsername(value);
    }
}