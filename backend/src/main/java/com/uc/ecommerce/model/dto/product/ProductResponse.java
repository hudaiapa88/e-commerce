package com.uc.ecommerce.model.dto.product;

import com.uc.ecommerce.model.dto.category.CategoryResponse;
import com.uc.ecommerce.model.entity.category.Category;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {
    private String title;
    private CategoryResponse category;
    private BigDecimal price;
    private Integer quantity;
}
