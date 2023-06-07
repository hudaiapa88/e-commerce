package com.uc.ecommerce.core.exception;

import com.uc.ecommerce.core.exception.base.BaseException;

public class WrongPasswordException extends BaseException {
    public WrongPasswordException(String message) {
        super(message);
    }
}
