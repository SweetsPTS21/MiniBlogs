package com.dogoo.miniblogs.service;

import com.dogoo.miniblogs.api.BlogsApiDelegate;
import com.dogoo.miniblogs.entity.BlogEntity;
import com.dogoo.miniblogs.mapper.BlogMapper;
import com.dogoo.miniblogs.model.Blog;
import com.dogoo.miniblogs.model.BlogApproveReq;
import com.dogoo.miniblogs.model.BlogReq;
import com.dogoo.miniblogs.repository.IBlogRepository;
import com.dogoo.miniblogs.validator.AuthorValidator;
import com.dogoo.miniblogs.validator.BlogValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements BlogsApiDelegate {
    IBlogRepository blogRepository;

    private final BlogMapper blogMapper;
    private final BlogValidator blogValidator;
    private final AuthorValidator authorValidator;

    public BlogService(IBlogRepository blogRepository, BlogMapper blogMapper, BlogValidator blogValidator, AuthorValidator authorValidator) {
        this.blogRepository = blogRepository;
        this.blogMapper = blogMapper;
        this.blogValidator = blogValidator;
        this.authorValidator = authorValidator;
    }

    @Override
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogMapper.mapBlogListFromBlogEntityList(blogRepository.findAll());
        return ResponseEntity.ok(blogs);
    }

    @Override
    public ResponseEntity<Blog> getBlogById(String id) {
        blogValidator.validateBlogExist(id);

        Blog blog = blogMapper.mapBlogFromBlogEntity(blogRepository.findById(id).get());
        return ResponseEntity.ok(blog);
    }

    @Override
    public ResponseEntity<Blog> createBlog(BlogReq blogReq) {
        blogValidator.validateAddBlog(blogReq);
        authorValidator.validateAuthorExist(blogReq.getAuthorId());

        BlogEntity blogEntity = blogMapper.mapBlogEntityFromBlogReq(blogReq);
        Blog blog = blogMapper.mapBlogFromBlogEntity(blogRepository.save(blogEntity));
        return ResponseEntity.ok(blog);
    }

    @Override
    public ResponseEntity<Blog> updateBlog(String id, BlogReq blogReq) {
        blogValidator.validateUpdateBlog(id, blogReq);
        authorValidator.validateAuthorExist(blogReq.getAuthorId());

        BlogEntity blogEntity = blogMapper.mapBlogEntityFromBlogReq(id, blogReq);
        Blog blog = blogMapper.mapBlogFromBlogEntity(blogRepository.save(blogEntity));
        return ResponseEntity.ok(blog);
    }

    @Override
    public ResponseEntity<Blog> deleteBlog(String id) {
        blogValidator.validateBlogExist(id);

        Blog blog = blogMapper.mapBlogFromBlogEntity(blogRepository.findById(id).get());
        blogRepository.deleteById(id);
        return ResponseEntity.ok(blog);
    }

    @Override
    public ResponseEntity<List<Blog>> getBlogsByTitle(String key) {
        List<Blog> blogs = blogMapper.mapBlogListFromBlogEntityList(blogRepository.findBlogEntitiesByTitleOrId(key, key));
        return ResponseEntity.ok(blogs);
    }

    @Override
    public ResponseEntity<Blog> approveBlog(String id) {
        BlogEntity blogEntity = blogRepository.findById(id).get();
        blogEntity.setApproved(true);
        blogRepository.save(blogEntity);
        return ResponseEntity.ok(blogMapper.mapBlogFromBlogEntity(blogEntity));
    }

}
