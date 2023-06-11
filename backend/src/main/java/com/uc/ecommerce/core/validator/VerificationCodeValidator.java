package com.uc.ecommerce.core.validator;

import com.uc.ecommerce.core.exception.WrongCodeException;
import com.uc.ecommerce.core.exception.validator.ValidatorException;
import com.uc.ecommerce.model.entity.account.Account;
import org.springframework.stereotype.Component;

@Component
public class VerificationCodeValidator{

    public void validate(Account account, String code) throws ValidatorException {
        if(!account.getVerificationCode().equals(code)){
            throw new WrongCodeException("Doğrulama kodu yanlış.");
        }
    }
}
