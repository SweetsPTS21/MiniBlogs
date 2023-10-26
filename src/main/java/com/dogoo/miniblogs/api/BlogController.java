package com.dogoo.miniblogs.api;

import com.dogoo.miniblogs.model.Blog;
import com.dogoo.miniblogs.model.BlogReq;
import com.dogoo.miniblogs.service.BlogService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BlogController implements BlogsApi{

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }


    @Override
    public ResponseEntity<Blog> createBlog(BlogReq blogReq) {
        return blogService.createBlog(blogReq);
    }

    @Override
    public ResponseEntity<Blog> updateBlog(String id, BlogReq blogReq) {
        return blogService.updateBlog(id, blogReq);
    }

    @Override
    public ResponseEntity<Blog> deleteBlog(String id) {
        return blogService.deleteBlog(id);
    }

    @Override
    public ResponseEntity<List<Blog>> getAllBlogs(Integer limit, Integer offset) {
        return blogService.getAllBlogs(limit, offset);
    }

    @Override
    public ResponseEntity<Blog> getBlogById(String id) {
        return blogService.getBlogById(id);
    }

    @Override
    public ResponseEntity<List<Blog>> getBlogsByFilter(String key, Boolean approve) {
        return blogService.getBlogsByFilter(key, approve);
    }

    @Override
    public ResponseEntity<Blog> approveBlog(String id) {
        return blogService.approveBlog(id);
    }
}
