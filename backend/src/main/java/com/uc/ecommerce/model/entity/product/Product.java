package com.uc.ecommerce.model.entity.product;

import com.uc.ecommerce.model.dto.product.CreateProductRequest;
import com.uc.ecommerce.model.dto.product.UpdateProductRequest;
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

    public static Product create(CreateProductRequest createProductRequest,Category category) {
        Product product = new Product();
        product.setTitle(createProductRequest.getTitle());
        product.setQuantity(createProductRequest.getQuantity());
        product.setPrice(createProductRequest.getPrice());
        product.setCategory(category);
        return product;
    }

    public Product update(UpdateProductRequest updateProductRequest,Category category) {
        this.setTitle(updateProductRequest.getTitle());
        this.setQuantity(updateProductRequest.getQuantity());
        this.setPrice(updateProductRequest.getPrice());
        this.setCategory(category);
        return this;
    }
}
