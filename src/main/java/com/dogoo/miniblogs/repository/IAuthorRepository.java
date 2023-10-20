package com.dogoo.miniblogs.repository;

import com.dogoo.miniblogs.entity.AuthorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepository extends MongoRepository<AuthorEntity, String> {
}
