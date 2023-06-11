package com.uc.ecommerce.model.dto.card;

import com.uc.ecommerce.model.dto.base.TimestampBaseDto;
import com.uc.ecommerce.model.entity.account.User;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;

@Getter
@Setter
public class CreditCardResponse extends TimestampBaseDto {

    private String no;
    private String date;
    private String cvv2;
}
