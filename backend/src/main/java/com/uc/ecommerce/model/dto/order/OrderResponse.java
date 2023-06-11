package com.uc.ecommerce.model.dto.order;

import com.uc.ecommerce.model.dto.account.UserResponse;
import com.uc.ecommerce.model.dto.base.TimestampBaseDto;
import com.uc.ecommerce.model.embedded.Address;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderResponse extends TimestampBaseDto {
    private BigDecimal totalPrice;
    private List<OrderLineResponse> orderLines;
    private UserResponse user;
    private Address address;
}
