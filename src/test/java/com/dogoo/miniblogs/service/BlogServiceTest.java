package com.dogoo.miniblogs.service;

import com.dogoo.miniblogs.mapper.BlogMapper;
import com.dogoo.miniblogs.mock.BlogData;
import com.dogoo.miniblogs.repository.BlogEntityRepository;
import com.dogoo.miniblogs.repository.IBlogRepository;
import com.dogoo.miniblogs.validator.BlogValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BlogServiceTest {
    @InjectMocks
    BlogService service;

    @Mock
    IBlogRepository repository;

    @Mock
    BlogEntityRepository blogEntityRepository;

    @Mock
    BlogMapper mapper;

    @Mock
    BlogValidator blogValidator;


    @Mock
    MongoTemplate mongoTemplate;

//    @Mock
//    BlogSearch propertySearch;
    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void createBlog() {
        service.createBlog(BlogData.mockBlogReq());
    }

    @Test
    public void updateBlog() {
        service.updateBlog(BlogData.ID, BlogData.mockBlogReq());
    }

    @Test
    public void testApproveBlogShouldReturnNotFoundWhenBlogIdNotFound() {
        when(repository.findById(BlogData.ID)).thenReturn(Optional.empty());
        assertEquals(404, service.approveBlog(BlogData.ID).getStatusCodeValue());
        service.approveBlog(BlogData.ID);
    }

    @Test
    public void testApproveBlogShouldReturnOKWhenBlogIsExist() {
        when(repository.findById(BlogData.TRUE_ID)).thenReturn(Optional.of(BlogData.mockBlogEntity()));
        assertEquals(200, service.approveBlog(BlogData.TRUE_ID).getStatusCodeValue());
        service.approveBlog(BlogData.ID);
    }

    @Test
    public void searchBlog() {
        service.getBlogsByFilter("MOCK_TERM", true);
    }

    @Test
    public void testGetAllBlogs() {
        when(blogEntityRepository.getAllByLimitAndOffset(5, 0)).thenReturn(BlogData.mockBlogEntityList());
        service.getAllBlogs(5, 0);
    }

    @Test
    public void testGetBlogByIdShouldReturnNotFoundWhenBlogIdNotFound() {
        when(repository.findById(BlogData.ID)).thenReturn(Optional.empty());
        assertEquals(404, service.getBlogById(BlogData.ID).getStatusCodeValue());
        service.getBlogById(BlogData.ID);
    }

    @Test
    public void testGetBlogByIdShouldReturnOKWhenBlogIsExist() {
        when(repository.findById(BlogData.TRUE_ID)).thenReturn(Optional.of(BlogData.mockBlogEntity()));
        assertEquals(200, service.getBlogById(BlogData.TRUE_ID).getStatusCodeValue());
        service.getBlogById(BlogData.ID);
    }

    @Test
    public void testDeleteBlogShouldReturnNotFoundWhenBlogIdNotFound() {
        when(repository.findById(BlogData.ID)).thenReturn(Optional.empty());
        assertEquals(404, service.deleteBlog(BlogData.ID).getStatusCodeValue());
        service.deleteBlog(BlogData.ID);
    }

//    @Test
//    public void testDeleteBlogShouldReturnOKWhenBlogIsExist() {
//        when(repository.findById(BlogData.TRUE_ID)).thenReturn(Optional.of(BlogData.mockBlogEntity()));
//        assertEquals(200, service.deleteBlog(BlogData.TRUE_ID).getStatusCodeValue());
//        service.deleteBlog(BlogData.ID);
//    }
}
