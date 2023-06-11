package com.uc.ecommerce.service;

import com.uc.ecommerce.core.exception.PaymentFailedException;
import com.uc.ecommerce.model.entity.card.CreditCard;
import com.uc.ecommerce.service.imp.BankService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankManager implements BankService {
    @Override
    public Boolean pay(CreditCard creditCard, BigDecimal amount) {
        Boolean result = Boolean.TRUE;
        if(result==Boolean.FALSE){
            throw new PaymentFailedException("Ödeme yapılamadı.");
        }
        return result;
    }
}
