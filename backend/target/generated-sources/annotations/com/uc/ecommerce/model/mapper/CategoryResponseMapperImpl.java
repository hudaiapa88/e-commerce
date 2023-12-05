package com.uc.ecommerce.model.mapper;

import com.uc.ecommerce.model.dto.category.CategoryResponse;
import com.uc.ecommerce.model.entity.category.Category;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-05T22:10:57+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class CategoryResponseMapperImpl implements CategoryResponseMapper {

    @Override
    public CategoryResponse entityToDto(Category entity) {
        if ( entity == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId( entity.getId() );
        categoryResponse.setTitle( entity.getTitle() );
        categoryResponse.setParent( categoryToCategoryResponse( entity.getParent() ) );

        return categoryResponse;
    }

    @Override
    public List<CategoryResponse> entityListToDtoList(List<Category> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CategoryResponse> list = new ArrayList<CategoryResponse>( entityList.size() );
        for ( Category category : entityList ) {
            list.add( entityToDto( category ) );
        }

        return list;
    }

    protected CategoryResponse categoryToCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId( category.getId() );
        categoryResponse.setTitle( category.getTitle() );
        categoryResponse.setParent( categoryToCategoryResponse( category.getParent() ) );

        return categoryResponse;
    }
}
