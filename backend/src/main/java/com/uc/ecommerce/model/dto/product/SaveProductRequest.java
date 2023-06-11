package com.uc.ecommerce.model.dto.product;

import com.uc.ecommerce.model.entity.category.Category;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SaveProductRequest {
    @NotEmpty(message = "{constraint.common.Empty.message}")
    @Size(max = 255,message = "{constraint.common.Size.message}")
    private String title;
    private Long categoryId;
    @NotEmpty(message = "{constraint.common.Empty.message}")
    @Positive(message = "{constraint.common.Positive.message}")
    private BigDecimal price;
    @NotEmpty(message = "{constraint.common.Empty.message}")
    @Positive(message = "{constraint.common.Positive.message}")
    private Integer quantity;
}
