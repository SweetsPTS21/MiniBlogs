package com.dogoo.miniblogs.repository;

import com.dogoo.miniblogs.entity.BlogEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogEntityRepository {
    private final MongoTemplate mongoTemplate;

    public BlogEntityRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<BlogEntity> getAllByLimitAndOffset(int limit, int offset) {
        Query query = new Query().skip(offset).limit(limit);
        return mongoTemplate.find(query, BlogEntity.class);
    }
}