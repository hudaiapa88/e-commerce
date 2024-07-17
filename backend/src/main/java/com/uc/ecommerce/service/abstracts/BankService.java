package com.uc.ecommerce.service.abstracts;

import com.uc.ecommerce.model.entity.card.CreditCard;

import java.math.BigDecimal;

public interface BankService {
     Boolean pay(CreditCard creditCard, BigDecimal amont);
}
