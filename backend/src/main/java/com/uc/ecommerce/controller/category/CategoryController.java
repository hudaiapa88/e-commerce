package com.uc.ecommerce.controller.category;



import com.uc.ecommerce.core.security.annotation.PermitAll;
import com.uc.ecommerce.model.dto.category.CategoryResponse;
import com.uc.ecommerce.model.dto.category.SaveCategoryRequest;
import com.uc.ecommerce.model.dto.category.UpdateCategoryRequest;
import com.uc.ecommerce.service.CategoryManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
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
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse saveParent(@RequestBody @Valid SaveCategoryRequest saveCategoryRequest) {
        return categoryManger.saveParent(saveCategoryRequest);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/parent/{parentId}/sub")
    public CategoryResponse saveSubCategory(@PathVariable Long parentId, @RequestBody @Valid  SaveCategoryRequest saveCategoryRequest) {
        return categoryManger.saveSub(parentId, saveCategoryRequest);
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
    public CategoryResponse update(@PathVariable Long id, @RequestBody @Valid  UpdateCategoryRequest updateCategoryRequest){
        return categoryManger.update(id,updateCategoryRequest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        categoryManger.delete(id);
    }

}
