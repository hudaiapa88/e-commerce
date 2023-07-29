package com.uc.ecommerce.model.entity.category;


import com.uc.ecommerce.model.dto.category.CreateCategoryRequest;
import com.uc.ecommerce.model.dto.category.UpdateCategoryRequest;
import com.uc.ecommerce.model.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Category extends BaseEntity {
    private String title;
    @ManyToOne
    private Category parent;

    public static Category createParent(CreateCategoryRequest createCategoryRequest) {
        Category category= new Category();
        category.setTitle(createCategoryRequest.getTitle());
        return category;
    }

    public static Category createSub(Category parentCategory, CreateCategoryRequest createCategoryRequest) {
        Category category= new Category();
        category.setTitle(createCategoryRequest.getTitle());
        category.setParent(parentCategory);
        return category;
    }

    public Category update(UpdateCategoryRequest updateCategoryRequest) {
        this.setTitle(updateCategoryRequest.getTitle());
        return this;
    }
}
