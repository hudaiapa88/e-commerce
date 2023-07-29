package com.uc.ecommerce.model.dto.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateProductRequest {
    @NotEmpty(message = "{constraint.common.Empty.message}")
    @Size(max = 255,message = "{constraint.common.Size.message}")
    private String title;
    private Long categoryId;
    @Positive(message = "{constraint.common.Positive.message}")
    private BigDecimal price;
    @Positive(message = "{constraint.common.Positive.message}")
    private Integer quantity;
}
