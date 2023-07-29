package com.uc.ecommerce.model.dto.order;

import com.uc.ecommerce.model.dto.card.CreateCreditCardRequest;
import com.uc.ecommerce.model.embedded.Address;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {
    private List<CreateOrderLineRequest> orderLines;
    @Valid
    private Address address;
    @Valid
    private CreateCreditCardRequest createCreditCardRequest;
}
