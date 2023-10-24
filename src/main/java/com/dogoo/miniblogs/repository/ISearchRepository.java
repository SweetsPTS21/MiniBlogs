package com.dogoo.miniblogs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface ISearchRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {

    List<T> searchBy(String text, int limit, String... fields);
}
