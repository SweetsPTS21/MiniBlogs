package com.dogoo.miniblogs.validator;

import com.dogoo.miniblogs.exception.BadRequestException;
import com.dogoo.miniblogs.exception.NotFoundException;
import com.dogoo.miniblogs.model.BlogApproveReq;
import com.dogoo.miniblogs.model.BlogReq;
import com.dogoo.miniblogs.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlogValidator {
    private static final String BLOG_DOES_NOT_EXIST = "Blog does not exist";
    private static final String NAME_REQUIRED = "Blog title is required";
    private static final String APPROVE_REQUIRED = "Approve status is required";
    IBlogRepository blogRepository;

    @Autowired
    public BlogValidator(IBlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public void validateBlogExist(String id) {
        if (blogRepository.existsById(id)) {return;}
        throw new NotFoundException(BLOG_DOES_NOT_EXIST);
    }

    public void validateAddBlog(BlogReq request) {
        checkRequiredField(request.getTitle(), NAME_REQUIRED);
    }

    public void validateUpdateBlog(String id, BlogReq request) {
        validateBlogExist(id);
        checkRequiredField(request.getTitle(), NAME_REQUIRED);
    }

    public void validateApprovedBlog(String id, BlogApproveReq request) {
        validateBlogExist(id);
        checkRequiredField(request.getApproved().toString(), APPROVE_REQUIRED);
    }

    private void checkRequiredField(String value, String errorMsg) {
        if (null == value || value.trim().isEmpty()) {
            throw new BadRequestException(errorMsg);
        }
    }
    
}
