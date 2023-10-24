package com.dogoo.miniblogs.api;

import com.dogoo.miniblogs.model.Blog;
import com.dogoo.miniblogs.model.BlogReq;
import com.dogoo.miniblogs.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3002", allowedHeaders = "*")
@RequestMapping("/api/v2")
@RestController
public class BlogControllerv2 {
    private final BlogService blogService;

    public BlogControllerv2(BlogService blogService) {
        this.blogService = blogService;
    }

//    @GetMapping("/blogs")
//    public ResponseEntity<List<Blog>> getAllBlogs() {
//        return blogService.getAllBlogs();
//    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable String id) {
        return blogService.getBlogById(id);
    }

    @PutMapping("/blogs/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable String id,@RequestBody BlogReq blogReq) {
        return blogService.updateBlog(id, blogReq);
    }
    @PostMapping("/blogs")
    public ResponseEntity<Blog> createBlog(@RequestBody BlogReq blogReq) {
        return blogService.createBlog(blogReq);
    }

    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable String id) {
        return blogService.deleteBlog(id);
    }
}
