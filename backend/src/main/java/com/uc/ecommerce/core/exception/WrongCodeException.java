package com.uc.ecommerce.core.exception;

import com.uc.ecommerce.core.exception.base.BaseException;

public class WrongCodeException extends BaseException {
    public WrongCodeException(String message) {
        super(message);
    }
}
