package com.dogoo.miniblogs.validator;

import com.dogoo.miniblogs.exception.BadRequestException;
import com.dogoo.miniblogs.exception.NotFoundException;
import com.dogoo.miniblogs.model.Author;
import com.dogoo.miniblogs.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorValidator {
    private static final String AUTHOR_DOES_NOT_EXIST = "Author does not exist";
    private static final String NAME_REQUIRED = "Author name is required";
    IAuthorRepository authorRepository;

    @Autowired
    public AuthorValidator(IAuthorRepository blogRepository) {
        this.authorRepository = blogRepository;
    }

    public void validateAuthorExist(String id) {
        if (authorRepository.existsById(id)) {return;}
        throw new NotFoundException(AUTHOR_DOES_NOT_EXIST);
    }


    public void validateUpdateAuthor(String id, Author request) {
        validateAuthorExist(id);
        checkRequiredField(request.getName(), NAME_REQUIRED);
    }

    private void checkRequiredField(String value, String errorMsg) {
        if (null == value || value.trim().isEmpty()) {
            throw new BadRequestException(errorMsg);
        }
    }

    public void validateAddAuthor(Author author) {
    }
}