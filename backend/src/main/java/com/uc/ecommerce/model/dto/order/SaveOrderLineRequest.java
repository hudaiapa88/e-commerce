package com.uc.ecommerce.model.dto.order;

import com.uc.ecommerce.model.entity.order.Order;
import com.uc.ecommerce.model.entity.product.Product;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SaveOrderLineRequest {
    private Long productId;
    @NotEmpty(message = "{constraint.common.Empty.message}")
    private Integer quantity;
}
