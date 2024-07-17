package com.uc.ecommerce.service.abstracts;

import com.uc.ecommerce.model.dto.category.CategoryResponse;
import com.uc.ecommerce.model.dto.category.CreateCategoryRequest;
import com.uc.ecommerce.model.dto.category.UpdateCategoryRequest;
import com.uc.ecommerce.model.entity.category.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<CategoryResponse> getAll() ;

     CategoryResponse saveParent(CreateCategoryRequest createCategoryRequest) ;

     CategoryResponse saveSub(Long parentId, CreateCategoryRequest createCategoryRequest) ;

     Category findById(Long id);

     List<CategoryResponse> getAllParent();

     List<CategoryResponse> getByParentId(Long parentId) ;

     CategoryResponse update(Long id, UpdateCategoryRequest updateCategoryRequest) ;

     void delete(Long id);
     Category findByIdAndParentNull(Long categoryId) ;

     List<Category> findAllParent() ;

     Optional<Category> findByTitleContainingIgnoreCaseAndParentNull(String lesson) ;

    List<Category> findByIdInAndParentNull(List<Long> categoryIds) ;
}
