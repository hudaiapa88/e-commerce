package com.uc.ecommerce.service.imp;

import com.uc.ecommerce.model.dto.product.ProductResponse;
import com.uc.ecommerce.model.dto.product.CreateProductRequest;
import com.uc.ecommerce.model.dto.product.UpdateProductRequest;
import com.uc.ecommerce.model.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
     ProductResponse save(CreateProductRequest createProductRequest);

     ProductResponse update(Long id, UpdateProductRequest updateProductRequest);

     Product findById(Long id);

     ProductResponse delete(Long id);

     ProductResponse getById(Long id);

     Page<ProductResponse> getAll(Pageable pageable);

     List<ProductResponse> getAll();

    void outOfStock(Product product, Integer quantity);

    Page<ProductResponse> getFilter(String title, List<Long> categoryIds, Pageable pageable);


    List<ProductResponse> getByQuery(String query);
}
