package com.uc.ecommerce.service;

import com.uc.ecommerce.core.exception.EntityNotFoundException;
import com.uc.ecommerce.core.i18n.Translator;
import com.uc.ecommerce.core.rsql.jpa.JpaRsqlVisitor;
import com.uc.ecommerce.core.rsql.parser.RSQLParser;
import com.uc.ecommerce.core.rsql.parser.ast.Node;
import com.uc.ecommerce.model.dto.product.ProductResponse;
import com.uc.ecommerce.model.dto.product.CreateProductRequest;
import com.uc.ecommerce.model.dto.product.UpdateProductRequest;
import com.uc.ecommerce.model.entity.category.Category;
import com.uc.ecommerce.model.entity.product.Product;
import com.uc.ecommerce.model.mapper.ProductResponseMapper;
import com.uc.ecommerce.repository.ProductRepository;
import com.uc.ecommerce.service.imp.CategoryService;
import com.uc.ecommerce.service.imp.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;
    private final ProductResponseMapper productResponseMapper;
    private final CategoryService categoryService;

    @Transactional
    @Override
    public ProductResponse save(CreateProductRequest createProductRequest) {
        Category category= categoryService.findById(createProductRequest.getCategoryId());
        return productResponseMapper.entityToDto(productRepository.save(Product.create(createProductRequest,category)));
    }

    @Transactional
    @Override
    public ProductResponse update(Long id, UpdateProductRequest updateProductRequest) {
        Product product = findById(id);
        Category category= categoryService.findById(updateProductRequest.getCategoryId());
        return productResponseMapper.entityToDto(productRepository.save(product.update(updateProductRequest,category)));
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Translator.toLocale("product.EntityNotFoundException")));
    }

    @Transactional
    @Override
    public ProductResponse delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
        return productResponseMapper.entityToDto(product);
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
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }

    @Override
    public Page<ProductResponse> getFilter(String title, List<Long> categoryIds, Pageable pageable) {
        Pageable customPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().isEmpty() ? Sort.by("createdDateTime").descending() : pageable.getSort());
        Page<Product> page = null;
        if (title.equals("") && !Optional.ofNullable(categoryIds).isPresent()) {
            page = productRepository.findAll(customPageable);
        }
        else if(!Optional.ofNullable(categoryIds).isPresent()){
            page =   productRepository.findByTitleContaining(title, customPageable);
        }
        else {
            page =   productRepository.findByTitleContainingAndCategory_IdIn(title, categoryIds, customPageable);
        }

        List<ProductResponse> dtoList = productResponseMapper.entityListToDtoList(page.getContent());
        Page<ProductResponse> dtoPage = new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
        return dtoPage;
    }

    @Override
    public List<ProductResponse> getByQuery(String query) {
        Node rootNode = new RSQLParser().parse(query);
        Specification<Product> spec = rootNode.accept(new JpaRsqlVisitor<>());
        return productResponseMapper.entityListToDtoList(productRepository.findAll(spec));
    }
}
