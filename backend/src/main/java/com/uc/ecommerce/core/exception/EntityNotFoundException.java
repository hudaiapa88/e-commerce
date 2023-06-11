package com.uc.ecommerce.core.exception;


import com.uc.ecommerce.core.exception.base.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(String message) {
        super(message);
    }


}
