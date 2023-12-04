package com.uc.ecommerce.model.mapper;

import com.uc.ecommerce.model.dto.category.CategoryResponse;
import com.uc.ecommerce.model.dto.product.ProductResponse;
import com.uc.ecommerce.model.entity.category.Category;
import com.uc.ecommerce.model.entity.product.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-04T16:22:50+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class ProductResponseMapperImpl implements ProductResponseMapper {

    @Override
    public ProductResponse entityToDto(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();

        productResponse.setId( entity.getId() );
        productResponse.setCreatedDateTime( entity.getCreatedDateTime() );
        productResponse.setUpdatedDateTime( entity.getUpdatedDateTime() );
        productResponse.setTitle( entity.getTitle() );
        productResponse.setCategory( categoryToCategoryResponse( entity.getCategory() ) );
        productResponse.setPrice( entity.getPrice() );
        productResponse.setQuantity( entity.getQuantity() );

        return productResponse;
    }

    @Override
    public List<ProductResponse> entityListToDtoList(List<Product> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProductResponse> list = new ArrayList<ProductResponse>( entityList.size() );
        for ( Product product : entityList ) {
            list.add( entityToDto( product ) );
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
