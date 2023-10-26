package com.dogoo.miniblogs.service;

import com.dogoo.miniblogs.api.BlogsApiDelegate;
import com.dogoo.miniblogs.entity.BlogEntity;
import com.dogoo.miniblogs.mapper.BlogMapper;
import com.dogoo.miniblogs.model.Blog;
import com.dogoo.miniblogs.model.BlogReq;
import com.dogoo.miniblogs.repository.BlogEntityRepository;
import com.dogoo.miniblogs.repository.IBlogRepository;
import com.dogoo.miniblogs.validator.AuthorValidator;
import com.dogoo.miniblogs.validator.BlogValidator;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements BlogsApiDelegate {
    IBlogRepository blogRepository;

    private final BlogMapper blogMapper;
    private final BlogValidator blogValidator;
    private final AuthorValidator authorValidator;

    private final MongoTemplate mongoTemplate;
    private final BlogEntityRepository blogEntityRepository;

    public BlogService(IBlogRepository blogRepository, BlogMapper blogMapper, BlogValidator blogValidator, AuthorValidator authorValidator, MongoTemplate mongoTemplate, BlogEntityRepository blogEntityRepository) {
        this.blogRepository = blogRepository;
        this.blogMapper = blogMapper;
        this.blogValidator = blogValidator;
        this.authorValidator = authorValidator;
        this.mongoTemplate = mongoTemplate;
        this.blogEntityRepository = blogEntityRepository;
    }

    @Override
    public ResponseEntity<List<Blog>> getAllBlogs(Integer limit, Integer offset) {
        List<BlogEntity> blogEntities = blogEntityRepository.getAllByLimitAndOffset(limit, offset);
        List<Blog> blogs = blogMapper.mapBlogListFromBlogEntityList(blogEntities);
        return ResponseEntity.ok(blogs);
    }

    @Override
    public ResponseEntity<Blog> getBlogById(String id) {
        blogValidator.validateBlogExist(id);

        Optional<BlogEntity> blogEntityOptional = blogRepository.findById(id);

        if (blogEntityOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Blog blog = blogMapper.mapBlogFromBlogEntity(blogEntityOptional.get());
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

        Optional<BlogEntity> blogEntityOptional = blogRepository.findById(id);
        if (blogEntityOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Blog blog = blogMapper.mapBlogFromBlogEntity(blogEntityOptional.get());
        blogRepository.deleteById(id);
        return ResponseEntity.ok(blog);
    }

    @Override
    public ResponseEntity<List<Blog>> getBlogsByFilter(String key, Boolean approve) {
        List<BlogEntity> blogs;
        blogs = blogRepository.findAll();
        if (key.equalsIgnoreCase("") && approve == null) {
            return ResponseEntity.ok(blogMapper.mapBlogListFromBlogEntityList(blogs));
        } else if(key.equalsIgnoreCase("")  && approve != null) {
            blogs.removeIf(blogEntity -> blogEntity.isApproved() != approve);
        } else {
            TextCriteria criteria = TextCriteria
                    .forDefaultLanguage()
                    .matchingPhrase(key);
            Query query = TextQuery.queryText(criteria).sortByScore();
            blogs = mongoTemplate.find(query, BlogEntity.class);
        }
        return ResponseEntity.ok(blogMapper.mapBlogListFromBlogEntityList(blogs));
    }

    @Override
    public ResponseEntity<Blog> approveBlog(String id) {
        Optional<BlogEntity> blogEntityOptional = blogRepository.findById(id);
        if (blogEntityOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BlogEntity blogEntity = blogEntityOptional.get();
        blogEntity.setApproved(true);
        blogRepository.save(blogEntity);
        return ResponseEntity.ok(blogMapper.mapBlogFromBlogEntity(blogEntity));
    }



}
