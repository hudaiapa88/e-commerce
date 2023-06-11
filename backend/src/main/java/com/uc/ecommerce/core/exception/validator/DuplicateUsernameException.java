package com.uc.ecommerce.core.exception.validator;

import com.uc.ecommerce.core.exception.base.BaseException;
import lombok.Getter;

@Getter
public class DuplicateUsernameException extends BaseException {
    public DuplicateUsernameException(String message) {
        super(message);
    }
}
