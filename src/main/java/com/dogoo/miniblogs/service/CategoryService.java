package com.dogoo.miniblogs.service;

import com.dogoo.miniblogs.api.CategoriesApiDelegate;
import com.dogoo.miniblogs.mapper.CategoryMapper;
import com.dogoo.miniblogs.model.Blog;
import com.dogoo.miniblogs.model.Category;
import com.dogoo.miniblogs.repository.ICategoryRepository;
import com.dogoo.miniblogs.validator.CategoryValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoriesApiDelegate {

    ICategoryRepository categoryRepository;
    private final CategoryValidator categoryValidator;
    private final CategoryMapper categoryMapper;

    public CategoryService(ICategoryRepository categoryRepository, CategoryValidator categoryValidator, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryValidator = categoryValidator;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryMapper.mapAuthorListFromAuthorEntityList(categoryRepository.findAll());
        return ResponseEntity.ok(categories);
    }

    @Override
    public ResponseEntity<Category> createCategory(Category category) {
        categoryValidator.validateAddCategory(category);
        Category categoryResponse = categoryMapper.mapCategoryFromCategoryEntity(categoryRepository.save(categoryMapper.mapCategoryEntityFromCategory(category)));
        return ResponseEntity.ok(categoryResponse);
    }

    @Override
    public ResponseEntity<Category> updateCategory(String id, Category category) {
        categoryValidator.validateUpdateCategory(id, category);
        Category categoryResponse = categoryMapper.mapCategoryFromCategoryEntity(categoryRepository.save(categoryMapper.mapCategoryEntityFromCategory(id, category)));
        return ResponseEntity.ok(categoryResponse);
    }

    @Override
    public ResponseEntity<Category> deleteCategory(String id) {
        categoryValidator.validateCategoryExist(id);
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
