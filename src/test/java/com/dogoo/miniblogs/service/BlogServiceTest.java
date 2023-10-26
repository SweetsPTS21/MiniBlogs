package com.dogoo.miniblogs.service;

import com.dogoo.miniblogs.mapper.BlogMapper;
import com.dogoo.miniblogs.mock.BlogData;
import com.dogoo.miniblogs.repository.IBlogRepository;
import com.dogoo.miniblogs.validator.AuthorValidator;
import com.dogoo.miniblogs.validator.BlogValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

@RunWith(MockitoJUnitRunner.class)
public class BlogServiceTest {
    @InjectMocks
    BlogService service;

    @Mock
    IBlogRepository repository;

    @Mock
    BlogMapper mapper;

    @Mock
    BlogValidator blogValidator;

    @Mock
    AuthorValidator authorValidator;

    @Mock
    MongoTemplate mongoTemplate;

//    @Mock
//    BlogSearch propertySearch;

    @Test
    public void createBlog() {
        service.createBlog(BlogData.mockBlogReq());
    }

    @Test
    public void updateBlog() {
        service.updateBlog(BlogData.ID, BlogData.mockBlogReq());
    }

    @Test
    public void approveBlog() {
        service.approveBlog(BlogData.ID);
    }

    @Test
    public void searchBlog() {
        service.getBlogsByFilter("MOCK_TERM", true);
    }
}
