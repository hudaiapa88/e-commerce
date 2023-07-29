package com.uc.ecommerce.model.dto.card;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;


@Getter
@Setter
public class CreateCreditCardRequest {
    @CreditCardNumber(message ="{constraint.card.no.CreditCardNumber.message}")
    private String no;
    private String date;
    @Pattern(regexp = "^[0-9]*$",message = "{constraint.card.cvv2.Pattern.message}")
    @Size(min=3,max = 3,message ="{constraint.card.cvv2.Size.message}" )
    private String cvv2;
    private Long userId;
}
