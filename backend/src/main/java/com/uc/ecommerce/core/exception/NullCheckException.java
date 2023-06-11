package com.uc.ecommerce.core.exception;


import com.uc.ecommerce.core.exception.base.BaseException;
import lombok.AllArgsConstructor;



public class NullCheckException extends BaseException {
    public NullCheckException(String message) {
        super(message);
    }
}
