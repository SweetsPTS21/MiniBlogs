package com.dogoo.miniblogs.api;

import com.dogoo.miniblogs.model.Blog;
import com.dogoo.miniblogs.model.Category;
import com.dogoo.miniblogs.service.CategoryService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CategoryController implements CategoriesApi{
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @Override
    public ResponseEntity<Category> createCategory(Category category) {
        return categoryService.createCategory(category);
    }

    @Override
    public ResponseEntity<Category> updateCategory(String id, Category category) {
        return categoryService.updateCategory(id, category);
    }

    @Override
    public ResponseEntity<Category> deleteCategory(String id) {
        return categoryService.deleteCategory(id);
    }
}
