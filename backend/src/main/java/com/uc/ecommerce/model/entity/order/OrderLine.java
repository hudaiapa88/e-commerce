package com.uc.ecommerce.model.entity.order;

import com.uc.ecommerce.model.entity.base.BaseEntity;
import com.uc.ecommerce.model.entity.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class OrderLine extends BaseEntity {
    @ManyToOne
    private Product product;
    @ManyToOne
    private Order order;
    private Integer quantity;
    private BigDecimal totalPrice;
}
