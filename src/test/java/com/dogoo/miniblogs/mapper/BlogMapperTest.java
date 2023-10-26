package com.dogoo.miniblogs.mapper;

import com.dogoo.miniblogs.entity.BlogEntity;
import com.dogoo.miniblogs.mock.BlogData;
import com.dogoo.miniblogs.model.Blog;
import com.dogoo.miniblogs.model.BlogApproveReq;
import com.dogoo.miniblogs.model.BlogReq;
import com.dogoo.miniblogs.repository.IBlogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BlogMapperTest {
    @InjectMocks
    BlogMapper mapper;

    @Mock
    IBlogRepository blogRepository;

    @Test
    public void ensureMapBlogEntityFromBlogReqForAdd() {
        BlogReq blogReq = BlogData.mockBlogReq();

        BlogEntity blogEntity = mapper.mapBlogEntityFromBlogReq(blogReq);

        assertEquals(blogEntity.getAuthorId(), blogReq.getAuthorId());
        assertEquals(blogEntity.getSummary(), blogReq.getSummary());
        assertEquals(blogEntity.getTitle(), blogReq.getTitle());
        assertEquals(blogEntity.getSource(), blogReq.getSource());
        assertEquals(blogEntity.getPublicDate(), blogReq.getPublicDate());
        assertEquals(blogEntity.getImage(), blogReq.getImage());
        assertEquals(blogEntity.getCategories(), blogReq.getCategories());
        assertEquals(blogEntity.getContent(), blogReq.getContent());

    }

    @Test
    public void ensureMapBlogEntityFromBlogReqForUpdate() {
        BlogReq blogReq = BlogData.mockBlogReq();
        BlogEntity blogEntity = BlogData.mockBlogEntity();

        when(blogRepository.findById(BlogData.ID)).thenReturn(Optional.of(blogEntity));

        BlogEntity entity = mapper.mapBlogEntityFromBlogReq(BlogData.ID, blogReq);

        assertEquals(entity.getAuthorId(), blogReq.getAuthorId());
        assertEquals(entity.getSummary(), blogReq.getSummary());
        assertEquals(entity.getTitle(), blogReq.getTitle());
        assertEquals(entity.getSource(), blogReq.getSource());
        assertEquals(entity.getPublicDate(), blogReq.getPublicDate());
        assertEquals(entity.getImage(), blogReq.getImage());
        assertEquals(entity.getCategories(), blogReq.getCategories());
        assertEquals(entity.getContent(), blogReq.getContent());

        assertEquals(entity.getCreatedDate(), blogEntity.getCreatedDate());
        assertEquals(entity.getUpdatedDate(), blogEntity.getUpdatedDate());
        assertEquals(entity.isApproved(), blogEntity.isApproved());
    }

    @Test
    public void ensureMapBlogEntityFromBlogApproveReq() {
        BlogEntity blogEntity = BlogData.mockBlogEntity();
        when(blogRepository.findById(BlogData.ID)).thenReturn(Optional.of(blogEntity));

        BlogApproveReq blogApproveReq = BlogData.mockBlogApproveReq();
        BlogEntity entity = mapper.mapBlogEntityFromBlogApproveReq(BlogData.ID, blogApproveReq);

        assertEquals(entity.getAuthorId(), blogEntity.getAuthorId());
        assertEquals(entity.getSummary(), blogEntity.getSummary());
        assertEquals(entity.getTitle(), blogEntity.getTitle());
        assertEquals(entity.getSource(), blogEntity.getSource());
        assertEquals(entity.getPublicDate(), blogEntity.getPublicDate());
        assertEquals(entity.getImage(), blogEntity.getImage());
        assertEquals(entity.getCategories(), blogEntity.getCategories());
        assertEquals(entity.getContent(), blogEntity.getContent());

        assertEquals(entity.getCreatedDate(), blogEntity.getCreatedDate());
        assertEquals(entity.getUpdatedDate(), blogEntity.getUpdatedDate());
        //assertTrue(entity.isApproved());
    }

    @Test
    public void ensureMapBlogListFromBlogEntityList() {
        List<BlogEntity> blogEntities = BlogData.mockBlogEntityList();
        List<Blog> blogs = mapper.mapBlogListFromBlogEntityList(blogEntities);

        assertBlogList(blogs, blogEntities);
    }

    @Test
    public void ensureMapBlogFromBlogEntity() {
        BlogEntity blogEntity = BlogData.mockBlogEntity();

        Blog blog = mapper.mapBlogFromBlogEntity(blogEntity);

        assertBlog(blog, blogEntity);
    }

    private void assertBlog(Blog actual, BlogEntity input) {
        assertEquals(actual.getId(), input.getId());
        assertEquals(actual.getTitle(), input.getTitle());
        assertEquals(actual.getImage(), input.getImage());
        assertEquals(actual.getSummary(), input.getSummary());
        assertEquals(actual.getContent(), input.getContent());
        assertEquals(actual.getSource(), input.getSource());
        assertEquals(actual.getCategories(), input.getCategories());
        assertEquals(actual.getPublicDate(), input.getPublicDate());
        assertEquals(actual.getCreateDate(), input.getCreatedDate());
        assertEquals(actual.getUpdateDate(), input.getUpdatedDate());
        assertEquals(actual.getApproved(), input.isApproved());
    }

    private void assertBlogList(List<Blog> actual, List<BlogEntity> input) {
        for (int i = 0; i < actual.size(); i++) {
            assertBlog(actual.get(i), input.get(i));
        }

    }
}
