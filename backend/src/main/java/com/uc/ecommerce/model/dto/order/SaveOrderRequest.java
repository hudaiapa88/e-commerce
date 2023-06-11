package com.uc.ecommerce.model.dto.order;

import com.uc.ecommerce.model.dto.card.SaveCreditCardRequest;
import com.uc.ecommerce.model.embedded.Address;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.model.entity.card.CreditCard;
import com.uc.ecommerce.model.entity.order.OrderLine;
import jakarta.persistence.Embedded;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class SaveOrderRequest {
    private List<SaveOrderLineRequest> orderLines;
    @Valid
    private Address address;
    @Valid
    private SaveCreditCardRequest saveCreditCardRequest;
}
