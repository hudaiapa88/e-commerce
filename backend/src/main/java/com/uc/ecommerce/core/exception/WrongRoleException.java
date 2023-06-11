package com.uc.ecommerce.core.exception;


import com.uc.ecommerce.core.exception.base.BaseException;

public class WrongRoleException extends BaseException {
    public WrongRoleException(String message) {
        super(message);
    }
}
