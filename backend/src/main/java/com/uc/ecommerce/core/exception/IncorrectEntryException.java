package com.uc.ecommerce.core.exception;

import com.uc.ecommerce.core.exception.base.BaseException;
import lombok.Getter;

@Getter
public class IncorrectEntryException extends BaseException {
    public IncorrectEntryException(String message) {
        super(message);
    }
}
