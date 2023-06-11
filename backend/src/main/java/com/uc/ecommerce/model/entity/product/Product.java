package com.uc.ecommerce.model.entity.product;

import com.uc.ecommerce.model.entity.base.AbstractTimestampEntity;
import com.uc.ecommerce.model.entity.category.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Product extends AbstractTimestampEntity {
    private String title;
    @ManyToOne
    private Category category;
    private BigDecimal price;
    private Integer quantity;
}
