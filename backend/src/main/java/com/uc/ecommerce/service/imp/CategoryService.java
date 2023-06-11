package com.uc.ecommerce.service.imp;

import com.uc.ecommerce.core.exception.EntityNotFoundException;
import com.uc.ecommerce.model.dto.category.CategoryResponse;
import com.uc.ecommerce.model.dto.category.SaveCategoryRequest;
import com.uc.ecommerce.model.dto.category.UpdateCategoryRequest;
import com.uc.ecommerce.model.entity.category.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<CategoryResponse> getAll() ;

     CategoryResponse saveParent(SaveCategoryRequest saveCategoryRequest) ;

     CategoryResponse saveSub(Long parentId, SaveCategoryRequest saveCategoryRequest) ;

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
