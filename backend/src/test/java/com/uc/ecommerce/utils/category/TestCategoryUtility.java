package com.uc.ecommerce.utils.category;

import com.uc.ecommerce.model.entity.category.Category;
import com.uc.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestCategoryUtility {
    @Autowired
    CategoryRepository categoryRepository;

    public Category createAndGetCategory(){
        Category category=new Category();
        category.setTitle("Ev-Eşya");
        return categoryRepository.save(category);
    }

    public Category createAndGetSubCategory(){
        Category category= new Category();
        category.setTitle("Ev terliği");
        category.setParent(createAndGetCategory());
        return category;
    }
}
