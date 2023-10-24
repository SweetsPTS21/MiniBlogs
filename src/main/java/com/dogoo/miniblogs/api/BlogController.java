package com.dogoo.miniblogs.api;

import com.dogoo.miniblogs.model.Blog;
import com.dogoo.miniblogs.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class BlogController implements BlogsApi{

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }



}
