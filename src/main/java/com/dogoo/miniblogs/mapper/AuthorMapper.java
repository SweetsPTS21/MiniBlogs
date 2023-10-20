package com.dogoo.miniblogs.mapper;

import com.dogoo.miniblogs.entity.AuthorEntity;
import com.dogoo.miniblogs.entity.AuthorEntity;
import com.dogoo.miniblogs.model.Author;
import com.dogoo.miniblogs.model.Author;
import com.dogoo.miniblogs.repository.IAuthorRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {
    IAuthorRepository authorRepository;

    public AuthorMapper(IAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorEntity mapAuthorEntityFromAuthor(Author from) {
        AuthorEntity to = new AuthorEntity();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setAvatar(from.getAvatar());
        to.setEmail(from.getEmail());
        to.setBio(from.getBio());
        return to;
    }

    public AuthorEntity mapAuthorEntityFromAuthor(String id, Author from) {
        AuthorEntity to = authorRepository.findById(id).get();
        to.setName(from.getName());
        to.setAvatar(from.getAvatar());
        to.setEmail(from.getEmail());
        to.setBio(from.getBio());
        return to;
    }

    public Author mapAuthorFromAuthorEntity(AuthorEntity from) {
        Author to = new Author();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setAvatar(from.getAvatar());
        to.setEmail(from.getEmail());
        to.setBio(from.getBio());
        return to;
    }

    public List<Author> mapAuthorListFromAuthorEntityList(List<AuthorEntity> from) {
        return from.stream().map(this::mapAuthorFromAuthorEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
