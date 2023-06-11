package com.uc.ecommerce.core.exception.validator;

import com.uc.ecommerce.core.exception.base.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;



public class ValidatorException extends BaseException {
    public ValidatorException(HttpStatus httpStatus, String message) {
        super(message, httpStatus);
    }
    public ValidatorException(String message) {
        super(message);
    }

}
