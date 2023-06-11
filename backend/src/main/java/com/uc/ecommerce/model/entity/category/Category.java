package com.uc.ecommerce.model.entity.category;


import com.uc.ecommerce.model.dto.category.SaveCategoryRequest;
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

    public static Category createParent(SaveCategoryRequest saveCategoryRequest) {
        Category category= new Category();
        category.setTitle(saveCategoryRequest.getTitle());
        return category;
    }

    public static Category createSub(Category parentCategory, SaveCategoryRequest saveCategoryRequest) {
        Category category= new Category();
        category.setTitle(saveCategoryRequest.getTitle());
        category.setParent(parentCategory);
        return category;
    }

    public Category update(UpdateCategoryRequest updateCategoryRequest) {
        this.setTitle(updateCategoryRequest.getTitle());
        return this;
    }
}
