package com.dogoo.miniblogs.repository;

import com.dogoo.miniblogs.entity.BlogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlogRepository extends MongoRepository<BlogEntity, String> {
    @Query("{'authorId': ?0}")
    List<BlogEntity> getBlogEntitiesByAuthorId(String authorId);
    @Query("{'$or': [{'title': {$regex: ?0, $options: 'i'}}, {'id': {$regex: ?1, $options: 'i'}}]}")
    List<BlogEntity> findBlogEntitiesByTitleOrId(String title, String id);
}
