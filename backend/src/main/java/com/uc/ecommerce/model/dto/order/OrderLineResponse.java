package com.uc.ecommerce.model.dto.order;

import com.uc.ecommerce.model.dto.base.BaseDto;
import com.uc.ecommerce.model.dto.product.ProductResponse;
import com.uc.ecommerce.model.entity.order.Order;
import com.uc.ecommerce.model.entity.product.Product;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderLineResponse extends BaseDto {

    private ProductResponse product;
    private Integer quantity;
    private BigDecimal totalPrice;
}
