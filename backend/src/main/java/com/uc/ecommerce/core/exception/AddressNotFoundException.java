package com.uc.ecommerce.core.exception;

import com.uc.ecommerce.core.exception.base.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;


public class AddressNotFoundException extends BaseException {

    public AddressNotFoundException(String message) {
        super(message);
    }

}
