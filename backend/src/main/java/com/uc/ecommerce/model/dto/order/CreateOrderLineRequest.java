package com.uc.ecommerce.model.dto.order;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderLineRequest {
    private Long productId;
    @NotEmpty(message = "{constraint.common.Empty.message}")
    private Integer quantity;
}
