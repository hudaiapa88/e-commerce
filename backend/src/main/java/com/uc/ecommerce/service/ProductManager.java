package com.uc.ecommerce.service;

import com.uc.ecommerce.core.exception.EntityNotFoundException;
import com.uc.ecommerce.core.i18n.Translator;
import com.uc.ecommerce.model.dto.product.ProductResponse;
import com.uc.ecommerce.model.dto.product.SaveProductRequest;
import com.uc.ecommerce.model.dto.product.UpdateProductRequest;
import com.uc.ecommerce.model.entity.product.Product;
import com.uc.ecommerce.model.mapper.ProductResponseMapper;
import com.uc.ecommerce.repository.ProductRepository;
import com.uc.ecommerce.service.imp.CategoryService;
import com.uc.ecommerce.service.imp.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;
    private final ProductResponseMapper productResponseMapper;
    private final CategoryService categoryService;

    @Transactional
    @Override
    public ProductResponse save(SaveProductRequest saveProductRequest) {
        Product product= new Product();
        product.setTitle(saveProductRequest.getTitle());
        product.setQuantity(saveProductRequest.getQuantity());
        product.setPrice(saveProductRequest.getPrice());
        product.setCategory(categoryService.findById(saveProductRequest.getCategoryId()));
        return productResponseMapper.entityToDto(productRepository.save(product));
    }
    @Transactional
    @Override
    public ProductResponse update(Long id, UpdateProductRequest updateProductRequest) {
        Product product= findById(id);
        product.setTitle(updateProductRequest.getTitle());
        product.setQuantity(updateProductRequest.getQuantity());
        product.setPrice(updateProductRequest.getPrice());
        product.setCategory(categoryService.findById(updateProductRequest.getCategoryId()));
        return productResponseMapper.entityToDto(productRepository.save(product));
    }
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Translator.toLocale("product.EntityNotFoundException")));
    }

    @Transactional
    @Override
    public ProductResponse delete(Long id) {
        return null;
    }

    @Override
    public ProductResponse getById(Long id) {
        return productResponseMapper.entityToDto(findById(id));
    }

    @Override
    public Page<ProductResponse> getAll(Pageable pageable) {
        Pageable customPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().isEmpty() ? Sort.by("createdDateTime").descending() : pageable.getSort());
        Page<Product> page = productRepository.findAll(customPageable);
        List<ProductResponse> dtoList = productResponseMapper.entityListToDtoList(page.getContent());
        Page<ProductResponse> dtoPage = new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
        return dtoPage;
    }

    @Override
    public List<ProductResponse> getAll() {
        return productResponseMapper.entityListToDtoList(productRepository.findAll());
    }

    @Override
    public void outOfStock(Product product, Integer quantity) {
        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
    }

    @Override
    public Page<ProductResponse> getFilter(String title, List<Long> categoryIds, Pageable pageable) {
        return productRepository.findByTitleContainingAndCategory_IdIn(title,categoryIds,pageable);
    }
}
