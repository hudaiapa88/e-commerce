package com.uc.ecommerce.core.exception;


import com.uc.ecommerce.core.exception.base.BaseException;
import org.springframework.http.HttpStatus;


public class UnauthorizedTransactionException extends BaseException {

    public UnauthorizedTransactionException(String message) {
        super(message);
    }
    public UnauthorizedTransactionException(String message, HttpStatus httpStatus) {
        super(message);
    }

}
