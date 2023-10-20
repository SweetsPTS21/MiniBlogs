package com.dogoo.miniblogs.api;

import com.dogoo.miniblogs.model.Author;
import com.dogoo.miniblogs.service.AuthorService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AuthorController implements AuthorsApi {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public ResponseEntity<List<Author>> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @Override
    public ResponseEntity<Author> getAuthorById(String id) {
        return authorService.getAuthorById(id);
    }

    @Override
    public ResponseEntity<Author> updateAuthor(String id, Author author) {
        return authorService.updateAuthor(id, author);
    }

    @Override
    public ResponseEntity<Author> deleteAuthor(String id) {
        return authorService.deleteAuthor(id);
    }
}
