package com.uc.ecommerce.service;


import com.uc.ecommerce.core.exception.EntityNotFoundException;
import com.uc.ecommerce.core.i18n.Translator;
import com.uc.ecommerce.model.dto.category.CategoryResponse;
import com.uc.ecommerce.model.dto.category.CreateCategoryRequest;
import com.uc.ecommerce.model.dto.category.UpdateCategoryRequest;
import com.uc.ecommerce.model.entity.category.Category;
import com.uc.ecommerce.model.mapper.CategoryResponseMapper;
import com.uc.ecommerce.repository.CategoryRepository;
import com.uc.ecommerce.service.abstracts.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service

@RequiredArgsConstructor
public class CategoryManager implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryResponseMapper categoryResponseMapper;
    @Override
    public List<CategoryResponse> getAll() {
        return categoryResponseMapper.entityListToDtoList(categoryRepository.findAll());
    }
    @Transactional
    @Override
    public CategoryResponse saveParent(CreateCategoryRequest createCategoryRequest) {
        return categoryResponseMapper.entityToDto(categoryRepository.save(Category.createParent(createCategoryRequest)));
    }
    @Transactional
    @Override
    public CategoryResponse saveSub(Long parentId, CreateCategoryRequest createCategoryRequest) {
        Category parentCategory=findById(parentId);
        return categoryResponseMapper.entityToDto(categoryRepository.save(Category.createSub(parentCategory, createCategoryRequest) ));
    }
    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @Override
    public List<CategoryResponse> getAllParent() {
        return categoryResponseMapper.entityListToDtoList(findAllParent());
    }
    @Override
    public List<CategoryResponse> getByParentId(Long parentId) {
        return categoryResponseMapper.entityListToDtoList(categoryRepository.findByParent_Id(parentId));
    }
    @Transactional
    @Override
    public CategoryResponse update(Long id, UpdateCategoryRequest updateCategoryRequest) {
        Category category=findById(id);
        return categoryResponseMapper.entityToDto(categoryRepository.save(category.update(updateCategoryRequest)));
    }
    @Override
    public void delete(Long id) {
        findById(id);
        categoryRepository.deleteById(id);
    }
    @Override

    public Category findByIdAndParentNull(Long categoryId) {
        return categoryRepository.findByIdAndParentNull(categoryId).orElseThrow(()->new EntityNotFoundException(Translator.toLocale("category.EntityNotFoundException")));
    }
    @Override
    public List<Category> findAllParent() {
        return categoryRepository.findByParentNull();
    }
    @Override
    public Optional<Category> findByTitleContainingIgnoreCaseAndParentNull(String lesson) {
        return categoryRepository.findByTitleContainingIgnoreCaseAndParentNull(lesson);
    }
    @Override
    public List<Category> findByIdInAndParentNull(List<Long> categoryIds) {
        return categoryRepository.findByIdInAndParentNull(categoryIds);
    }
}
