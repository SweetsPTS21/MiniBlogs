package com.dogoo.miniblogs.mapper;

import com.dogoo.miniblogs.entity.AuthorEntity;
import com.dogoo.miniblogs.entity.CategoryEntity;
import com.dogoo.miniblogs.model.Category;
import com.dogoo.miniblogs.model.Category;
import com.dogoo.miniblogs.repository.ICategoryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    ICategoryRepository categoryRepository;

    public CategoryMapper(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity mapCategoryEntityFromCategory(Category from) {
        CategoryEntity to = new CategoryEntity();
        to.setName(from.getName());
        to.setDescription(from.getDescription());
        return to;
    }

    public CategoryEntity mapCategoryEntityFromCategory(String id, Category from) {
        CategoryEntity to = categoryRepository.findById(id).get();
        to.setName(from.getName());
        to.setDescription(from.getDescription());
        return to;
    }

    public Category mapCategoryFromCategoryEntity(CategoryEntity from) {
        Category to = new Category();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setDescription(from.getDescription());
        return to;
    }

    public List<Category> mapAuthorListFromAuthorEntityList(List<CategoryEntity> from) {
        return from.stream().map(this::mapCategoryFromCategoryEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
