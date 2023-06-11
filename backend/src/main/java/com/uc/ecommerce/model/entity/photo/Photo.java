package com.uc.ecommerce.model.entity.photo;

import com.uc.ecommerce.model.entity.base.AbstractTimestampEntity;
import com.uc.ecommerce.model.entity.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
@Entity
public class Photo extends AbstractTimestampEntity {
    private String title;
    @Lob
    private Blob data;
    @OneToOne
    private Product product;
}
