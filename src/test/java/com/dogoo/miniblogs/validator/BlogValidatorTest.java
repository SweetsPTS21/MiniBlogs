package com.dogoo.miniblogs.validator;

import com.dogoo.miniblogs.exception.BadRequestException;
import com.dogoo.miniblogs.exception.NotFoundException;
import com.dogoo.miniblogs.mock.BlogData;
import com.dogoo.miniblogs.model.BlogReq;
import com.dogoo.miniblogs.repository.IBlogRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BlogValidatorTest {
    @InjectMocks
    BlogValidator validator;

    @Mock
    IBlogRepository repository;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void validateUpdateBlog() {
        when(repository.existsById(BlogData.ID)).thenReturn(true);

        validator.validateUpdateBlog(BlogData.ID, BlogData.mockBlogReq());
    }

    @Test
    public void validateUpdateBlogShowExceptionWhenWrongId() {
        expected.expect(NotFoundException.class);
        expected.expectMessage("Blog does not exist");

        when(repository.existsById(BlogData.ID)).thenReturn(false);

        validator.validateUpdateBlog(BlogData.ID, BlogData.mockBlogReq());
    }

    @Test
    public void validateAddBlog() {
        validator.validateAddBlog(BlogData.mockBlogReq());
    }

    @Test
    public void validateAddBlogShowExceptionWhenRequiredFieldIsNull() {
        expected.expect(BadRequestException.class);
        expected.expectMessage("Blog title is required");

        BlogReq propertyReq = BlogData.mockBlogReq();
        propertyReq.setTitle("");

        validator.validateAddBlog(propertyReq);
    }

    @Test
    public void validateApprovedBlog() {
        when(repository.existsById(BlogData.ID)).thenReturn(true);

        validator.validateApprovedBlog(BlogData.ID, BlogData.mockBlogApproveReq());
    }

}