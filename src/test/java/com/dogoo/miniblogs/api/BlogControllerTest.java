package com.dogoo.miniblogs.api;

import com.dogoo.miniblogs.mock.BlogData;
import com.dogoo.miniblogs.model.Blog;
import com.dogoo.miniblogs.model.BlogApproveReq;
import com.dogoo.miniblogs.model.BlogReq;
import com.dogoo.miniblogs.service.BlogService;
import com.dogoo.miniblogs.validator.BlogValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class BlogControllerTest {
    private final String API_KEY = "MOCK_API_KEY";

    @InjectMocks
    BlogController controller;

    @Mock
    BlogService service;

    @Mock
    BlogValidator validator;

//    @Mock
//    BlogSearchValidator searchValidator;

    @Test
    public void testEndpointCreateBlog() {
        when(service.createBlog(any(BlogReq.class)))
                .thenReturn(ResponseEntity.ok(BlogData.mockBlog()));

        ResponseEntity<Blog> responseEntity =
                controller.createBlog(BlogData.mockBlogReq());

        assertStatus200(responseEntity.getStatusCode());
        assertBlog(responseEntity.getBody());
    }

    @Test
    public void testEndpointUpdateBlog() {
        when(service.updateBlog(anyString(), any(BlogReq.class)))
                .thenReturn(ResponseEntity.ok(BlogData.mockBlog()));

        ResponseEntity<Blog> responseEntity =
                controller.updateBlog(BlogData.ID, BlogData.mockBlogReq());

        assertStatus200(responseEntity.getStatusCode());
        assertBlog(responseEntity.getBody());
    }

    @Test
    public void testEndpointApproveBlog() {
        when(service.approveBlog(anyString()))
                .thenReturn(ResponseEntity.ok(BlogData.mockBlog()));

        ResponseEntity<Blog> responseEntity =
                controller.approveBlog(BlogData.ID);

        assertStatus200(responseEntity.getStatusCode());
        assertBlog(responseEntity.getBody());
    }

    @Test
    public void testEndpointSearchBlog() {
        when(service.getBlogsByFilter(anyString(), any()))
                .thenReturn(ResponseEntity.ok(BlogData.mockBlogList()));

        ResponseEntity<List<Blog>> responseEntity =
                controller.getBlogsByFilter("MOCK_TERM", true);

        assertStatus200(responseEntity.getStatusCode());
        assertProperties(responseEntity.getBody());
    }

    private void assertBlog(Blog actual) {
       assertEquals(actual.getId(), BlogData.ID);
       assertEquals(actual.getAuthorId(), BlogData.AUTHOR_ID);
       assertEquals(actual.getSummary(), BlogData.SUMMARY);
       assertEquals(actual.getTitle(), BlogData.TITLE);
       assertEquals(actual.getSource(), BlogData.SOURCE);
       assertEquals(actual.getPublicDate(), BlogData.PUBLIC_DATE);
       assertEquals(actual.getImage(), BlogData.IMAGE);
       assertEquals(actual.getCategories(), BlogData.CATEGORIES);
       assertEquals(actual.getContent(), BlogData.CONTENT);
       assertEquals(actual.getUpdateDate(), BlogData.UPDATED_DATE);
       assertEquals(actual.getCreateDate(), BlogData.CREATED_DATE);
       assertEquals(actual.getApproved(), BlogData.APPROVED);
    }

    private void assertProperties(List<Blog> actual) {
        assertBlog(actual.get(0));
    }

    private void assertStatus200(HttpStatus actual) {
       assertEquals(HttpStatus.OK, actual);
    }
}