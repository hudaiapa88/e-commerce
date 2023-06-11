package com.uc.ecommerce.core.exception;

import com.uc.ecommerce.core.exception.base.BaseException;

public class PaymentFailedException extends BaseException {
    public PaymentFailedException(String message) {
        super(message);
    }
}
