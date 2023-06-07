package com.uc.ecommerce.core.validator;

import com.uc.ecommerce.core.exception.validator.ValidatorException;
import lombok.AllArgsConstructor;
import lombok.Data;

public class DuplicateMailException extends ValidatorException {

    public DuplicateMailException(String message) {
        super(message);
    }

}
