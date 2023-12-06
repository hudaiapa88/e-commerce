package com.uc.ecommerce.controller.category;



import com.uc.ecommerce.core.security.annotation.OnlyAdmin;
import com.uc.ecommerce.core.security.annotation.PermitAll;
import com.uc.ecommerce.model.dto.category.CategoryResponse;
import com.uc.ecommerce.model.dto.category.CreateCategoryRequest;
import com.uc.ecommerce.model.dto.category.UpdateCategoryRequest;
import com.uc.ecommerce.service.CategoryManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("category")
@RequiredArgsConstructor
@PermitAll
public class CategoryController {

    private final CategoryManager categoryManger;

    @GetMapping
    public List<CategoryResponse> getAll() {
        return categoryManger.getAll();
    }

    @PostMapping("/parent")
    @OnlyAdmin
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse saveParent(@RequestBody @Valid CreateCategoryRequest createCategoryRequest) {
        return categoryManger.saveParent(createCategoryRequest);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @OnlyAdmin
    @PostMapping("/parent/{parentId}/sub")
    public CategoryResponse saveSubCategory(@PathVariable Long parentId, @RequestBody @Valid CreateCategoryRequest createCategoryRequest) {
        return categoryManger.saveSub(parentId, createCategoryRequest);
    }

    @GetMapping("/parent")
    public List<CategoryResponse> getAllParent(){
        return categoryManger.getAllParent();
    }
    @GetMapping("/parent/{parentId}")
    public List<CategoryResponse> getByParentId(@PathVariable Long parentId){
        return categoryManger.getByParentId(parentId);
    }

    @PutMapping("/{id}")
    @OnlyAdmin
    public CategoryResponse update(@PathVariable Long id, @RequestBody @Valid  UpdateCategoryRequest updateCategoryRequest){
        return categoryManger.update(id,updateCategoryRequest);
    }
    @DeleteMapping("/{id}")
    @OnlyAdmin
    public void delete(@PathVariable Long id){
        categoryManger.delete(id);
    }

}
