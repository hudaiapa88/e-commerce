package com.uc.ecommerce.core.validator;


import com.uc.ecommerce.core.exception.validator.ValidatorException;

public interface Validator<T> {
    void validate(T t) throws ValidatorException;
}