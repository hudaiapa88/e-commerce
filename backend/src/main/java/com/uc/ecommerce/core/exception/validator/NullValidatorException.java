package com.uc.ecommerce.core.exception.validator;


import com.uc.ecommerce.core.exception.base.BaseException;

public class NullValidatorException extends BaseException {
    public NullValidatorException(String text) {
        super(text);
    }
}
