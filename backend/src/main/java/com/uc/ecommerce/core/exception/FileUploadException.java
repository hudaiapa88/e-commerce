package com.uc.ecommerce.core.exception;

import com.uc.ecommerce.core.exception.base.BaseException;
import lombok.Data;

public class FileUploadException extends BaseException {
    public FileUploadException(String message) {
        super(message);
    }

}
