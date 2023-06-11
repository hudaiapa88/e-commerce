package com.uc.ecommerce.controller.product;

import com.uc.ecommerce.core.security.annotation.IsAuthenticated;
import com.uc.ecommerce.core.security.annotation.OnlyAdmin;
import com.uc.ecommerce.model.dto.product.ProductResponse;
import com.uc.ecommerce.model.dto.product.SaveProductRequest;
import com.uc.ecommerce.model.dto.product.UpdateProductRequest;
import com.uc.ecommerce.service.imp.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
@OnlyAdmin
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ProductResponse save( @Valid @RequestBody SaveProductRequest saveProductRequest){
        return productService.save(saveProductRequest);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id, @Valid  @RequestBody   UpdateProductRequest updateProductRequest){
        return productService.update(id,updateProductRequest);
    }
    @DeleteMapping("/{id}")
    public ProductResponse delete(@PathVariable Long id){
        return productService.delete(id);
    }
    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id){
        return productService.getById(id);
    }
    @GetMapping("/filter")
    public Page<ProductResponse> getFilter(@RequestParam String title,@RequestParam List<Long> categoryIds,Pageable pageable){
        return productService.getFilter(title,categoryIds,pageable);
    }
    @IsAuthenticated
    @GetMapping("/pageable")
    public Page<ProductResponse> getAll(Pageable pageable){
        return productService.getAll(pageable);
    }
    @IsAuthenticated
    @GetMapping()
    public List<ProductResponse> getAll(){
        return productService.getAll();
    }
}
