package com.uc.ecommerce.model.mapper;

import com.uc.ecommerce.model.dto.product.ProductResponse;
import com.uc.ecommerce.model.entity.product.Product;
import com.uc.ecommerce.model.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper extends BaseMapper<ProductResponse, Product> {
}
