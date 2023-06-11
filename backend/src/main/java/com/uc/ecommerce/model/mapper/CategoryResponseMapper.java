package com.uc.ecommerce.model.mapper;


import com.uc.ecommerce.model.dto.category.CategoryResponse;
import com.uc.ecommerce.model.entity.category.Category;
import com.uc.ecommerce.model.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper extends BaseMapper<CategoryResponse, Category> {

}
