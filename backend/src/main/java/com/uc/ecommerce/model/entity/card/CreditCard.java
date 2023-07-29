package com.uc.ecommerce.model.entity.card;

import com.uc.ecommerce.model.dto.card.CreateCreditCardRequest;
import com.uc.ecommerce.model.dto.card.UpdateCreditCardRequest;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.model.entity.base.AbstractTimestampEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CreditCard extends AbstractTimestampEntity {
    private String no;
    private String date;
    private String cvv2;
    @ManyToOne
    private User user;
    public static CreditCard create(CreateCreditCardRequest createCreditCardRequest,User user){
        CreditCard card= new CreditCard();
        card.setNo(createCreditCardRequest.getNo());
        card.setDate(createCreditCardRequest.getDate());
        card.setCvv2(createCreditCardRequest.getCvv2());
        card.setUser(user);
        return card;
    }
    public CreditCard update(UpdateCreditCardRequest updateCreditCardRequest){
        this.setCvv2(updateCreditCardRequest.getCvv2());
        this.setNo(updateCreditCardRequest.getNo());
        this.setDate(updateCreditCardRequest.getDate());
        return this;
    }
}
