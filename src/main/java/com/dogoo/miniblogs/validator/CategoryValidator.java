package com.dogoo.miniblogs.validator;

import com.dogoo.miniblogs.exception.BadRequestException;
import com.dogoo.miniblogs.exception.NotFoundException;
import com.dogoo.miniblogs.model.Category;
import com.dogoo.miniblogs.repository.ICategoryRepository;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidator {
    private static final String CATEGORY_DOES_NOT_EXIST = "Category does not exist";
    private static final String NAME_REQUIRED = "Category name is required";
    ICategoryRepository categoryRepository;

    @Autowired
    public CategoryValidator(ICategoryRepository blogRepository) {
        this.categoryRepository = blogRepository;
    }

    public void validateCategoryExist(String id) {
        if (categoryRepository.existsById(id)) {return;}
        throw new NotFoundException(CATEGORY_DOES_NOT_EXIST);
    }


    public void validateUpdateCategory(String id, Category request) {
        validateCategoryExist(id);
        checkRequiredField(request.getName(), NAME_REQUIRED);
    }

    private void checkRequiredField(String value, String errorMsg) {
        if (null == value || value.trim().isEmpty()) {
            throw new BadRequestException(errorMsg);
        }
    }

    public void validateAddCategory(Category author) {
    }
}
