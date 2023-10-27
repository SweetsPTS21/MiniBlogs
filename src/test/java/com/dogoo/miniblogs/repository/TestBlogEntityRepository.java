package com.dogoo.miniblogs.repository;

import com.dogoo.miniblogs.entity.BlogEntity;
import com.dogoo.miniblogs.mock.BlogData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestBlogEntityRepository {

    @InjectMocks
    BlogEntityRepository blogEntityRepository;

    @Mock
    MongoTemplate mongoTemplate;

    @Test
    public void testGetAllByLimitAndOffset() {
        Query query = new Query().skip(1).limit(1);
        when(mongoTemplate.find(query, BlogEntity.class)).thenReturn(BlogData.mockBlogEntityList());
        blogEntityRepository.getAllByLimitAndOffset(1, 1);
    }
}
