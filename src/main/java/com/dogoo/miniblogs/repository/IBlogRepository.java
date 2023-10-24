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
    @Query("{'$and': [{'title': {$regex: ?0, $options: 'i'}}, {'approved': ?1 }]}")
    List<BlogEntity> findBlogEntitiesByTitleAndApproved(String title, boolean approved);

}
