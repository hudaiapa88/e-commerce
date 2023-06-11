package com.uc.ecommerce.model.entity.card;

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
}
