package com.dogoo.miniblogs.service;

import com.dogoo.miniblogs.api.AuthorsApiDelegate;
import com.dogoo.miniblogs.entity.AuthorEntity;
import com.dogoo.miniblogs.mapper.AuthorMapper;
import com.dogoo.miniblogs.mapper.BlogMapper;
import com.dogoo.miniblogs.model.Author;
import com.dogoo.miniblogs.model.Blog;
import com.dogoo.miniblogs.repository.IAuthorRepository;
import com.dogoo.miniblogs.repository.IBlogRepository;
import com.dogoo.miniblogs.validator.AuthorValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements AuthorsApiDelegate {

    IAuthorRepository authorRepository;
    IBlogRepository blogRepository;
    private final AuthorValidator authorValidator;
    private final AuthorMapper authorMapper;
    private final BlogMapper blogMapper;

    public AuthorService(IAuthorRepository authorRepository, IBlogRepository blogRepository, AuthorValidator authorValidator, AuthorMapper authorMapper, BlogMapper blogMapper) {
        this.authorRepository = authorRepository;
        this.blogRepository = blogRepository;
        this.authorValidator = authorValidator;
        this.authorMapper = authorMapper;
        this.blogMapper = blogMapper;
    }

    @Override
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorMapper.mapAuthorListFromAuthorEntityList(authorRepository.findAll());
        return ResponseEntity.ok(authors);
    }

    @Override
    public ResponseEntity<Author> getAuthorById(String id) {
        authorValidator.validateAuthorExist(id);
        Author author = authorMapper.mapAuthorFromAuthorEntity(authorRepository.findById(id).get());
        return ResponseEntity.ok(author);
    }

    @Override
    public ResponseEntity<List<Blog>> getAllBlogsByAuthorId(String id) {
        authorValidator.validateAuthorExist(id);
        List<Blog> blogs = blogMapper.mapBlogListFromBlogEntityList(blogRepository.getBlogEntitiesByAuthorId(id));
        return ResponseEntity.ok(blogs);
    }

    @Override
    public ResponseEntity<Author> createAuthor(Author author) {
        authorValidator.validateAddAuthor(author);
        AuthorEntity authorEntity = authorRepository.save(authorMapper.mapAuthorEntityFromAuthor(author));
        Author authorResponse = authorMapper.mapAuthorFromAuthorEntity(authorEntity);
        return ResponseEntity.ok(authorResponse);
    }

    @Override
    public ResponseEntity<Author> updateAuthor(String id, Author author) {
        authorValidator.validateUpdateAuthor(id, author);
        AuthorEntity authorEntity = authorRepository.save(authorMapper.mapAuthorEntityFromAuthor(id, author));
        Author authorResponse = authorMapper.mapAuthorFromAuthorEntity(authorEntity);
        return ResponseEntity.ok(authorResponse);
    }

    @Override
    public ResponseEntity<Author> deleteAuthor(String id) {
        authorValidator.validateAuthorExist(id);
        authorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
