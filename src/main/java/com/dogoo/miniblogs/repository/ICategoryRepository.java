package com.dogoo.miniblogs.repository;

import com.dogoo.miniblogs.entity.CategoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends MongoRepository<CategoryEntity, String> {
}
