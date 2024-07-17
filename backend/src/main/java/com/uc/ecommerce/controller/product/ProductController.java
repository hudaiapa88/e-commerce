package com.uc.ecommerce.controller.product;

import com.uc.ecommerce.core.security.annotation.OnlyAdmin;
import com.uc.ecommerce.model.dto.product.ProductResponse;
import com.uc.ecommerce.model.dto.product.CreateProductRequest;
import com.uc.ecommerce.model.dto.product.UpdateProductRequest;
import com.uc.ecommerce.service.abstracts.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @OnlyAdmin
    @Caching(evict= { @CacheEvict(value= "products", allEntries= true) })
    @PostMapping
    public ProductResponse save( @Valid @RequestBody CreateProductRequest createProductRequest){
        return productService.save(createProductRequest);
    }
    @OnlyAdmin
    @PutMapping("/{id}")
    @Caching(
            put= { @CachePut(value= "product", key= "#id") },
            evict= { @CacheEvict(value= "products", allEntries= true) }
    )
    public ProductResponse update(@PathVariable Long id, @Valid  @RequestBody   UpdateProductRequest updateProductRequest){
        return productService.update(id,updateProductRequest);
    }
    @OnlyAdmin
    @DeleteMapping("/{id}")
    @Caching(
            evict= {
                    @CacheEvict(value= "product", key= "#id"),
                    @CacheEvict(value= "products", allEntries= true)
            }
    )
    public ProductResponse delete(@PathVariable Long id){
        return productService.delete(id);
    }
    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id){
        return productService.getById(id);
    }
    @GetMapping("/filter")
    public Page<ProductResponse> getFilter(@RequestParam(required = false,defaultValue = "") String title, @RequestParam(required = false) List<Long> categoryIds, Pageable pageable){
        return productService.getFilter(title,categoryIds,pageable);
    }

    @OnlyAdmin
    @Cacheable(value = "products", key = "#query", unless= "#result.size() == 0")
    @GetMapping("/rsql")
    public List<ProductResponse> getByQuery(@RequestParam String query){
        return productService.getByQuery(query);
    }

    @GetMapping("/pageable")
    @Cacheable(value = "products", key = "#page.pageNumber", unless= "#result.size() == 0")
    public Page<ProductResponse> getAll(Pageable pageable){
        return productService.getAll(pageable);
    }
    @GetMapping()
    @Cacheable(value= "products", unless= "#result.size() == 0")
    public List<ProductResponse> getAll(){
        return productService.getAll();
    }
}
