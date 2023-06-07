package com.uc.ecommerce.core.exception.validator;

import com.uc.ecommerce.core.exception.base.BaseException;
import lombok.Getter;


public class UserDisabledException extends BaseException {
    public UserDisabledException(String message) {
        super(message);
    }
}
