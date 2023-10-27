package com.dogoo.miniblogs.repository;

import com.dogoo.miniblogs.entity.BlogEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlogRepository extends MongoRepository<BlogEntity, String> {
    @Query("{'approved': ?0 }")
    List<BlogEntity> findBlogEntitiesByApproved(boolean approved);

}
