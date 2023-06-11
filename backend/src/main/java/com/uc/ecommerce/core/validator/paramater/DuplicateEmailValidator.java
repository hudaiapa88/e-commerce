package com.uc.ecommerce.core.validator.paramater;

import com.uc.ecommerce.service.imp.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DuplicateEmailValidator implements ConstraintValidator<DuplicateEmail, String> {
    private final AccountService accountService;

    @Override
    public void initialize(DuplicateEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !accountService.existByEmail(value);
    }
}