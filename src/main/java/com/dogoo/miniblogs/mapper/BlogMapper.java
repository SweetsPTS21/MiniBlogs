package com.dogoo.miniblogs.mapper;

import com.dogoo.miniblogs.entity.BlogEntity;
import com.dogoo.miniblogs.model.Blog;
import com.dogoo.miniblogs.model.BlogApproveReq;
import com.dogoo.miniblogs.model.BlogContent;
import com.dogoo.miniblogs.model.BlogReq;
import com.dogoo.miniblogs.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;
@Component
public class BlogMapper {
    IBlogRepository todoRepository;

    @Autowired
    public BlogMapper(IBlogRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public BlogEntity mapBlogEntityFromBlogReq(BlogReq from) {
        BlogEntity to = new BlogEntity();
        to.setAuthorId(from.getAuthorId());
        to.setSummary(from.getSummary());
        to.setTitle(from.getTitle());
        to.setSource(from.getSource());
        to.setPublicDate(from.getPublicDate());
        to.setImage(from.getImage());
        to.setCategories(from.getCategories());
        to.setContent(from.getContent());
        to.setCreatedDate("2021-01-01");
        to.setUpdatedDate("2021-01-01");
        to.setApproved(false);
        return to;
    }

    public BlogEntity mapBlogEntityFromBlogReq(String id, BlogReq from) {
        BlogEntity to = todoRepository.findById(id).get();
        to.setId(id);
        to.setAuthorId(from.getAuthorId());
        to.setSummary(from.getSummary());
        to.setTitle(from.getTitle());
        to.setSource(from.getSource());
        to.setPublicDate(from.getPublicDate());
        to.setImage(from.getImage());
        to.setCategories(from.getCategories());
        to.setContent(from.getContent());
        to.setCreatedDate("2021-01-01");
        to.setUpdatedDate("2021-01-01");
        to.setApproved(false);
        return to;
    }

    public BlogEntity mapBlogEntityFromBlogApproveReq(String id, BlogApproveReq from) {
        BlogEntity to = todoRepository.findById(id).get();
        to.setAuthorId(from.getAuthorId());
        to.setId(from.getId());
        to.setApproved(from.getApproved());
        return to;
    }

    public Blog mapBlogFromBlogEntity(BlogEntity from) {
        Blog to = new Blog();
        to.setId(from.getId());
        to.setSummary(from.getSummary());
        to.setTitle(from.getTitle());
        to.setSource(from.getSource());
        to.setPublicDate(from.getPublicDate());
        to.setImage(from.getImage());
        to.setCategories(from.getCategories());
        to.setContent(from.getContent());
        to.setCreateDate("2021-01-01");
        to.setUpdateDate("2021-01-01");
        to.setAuthorId(from.getAuthorId());
        to.setApproved(from.isApproved());
        return to;
    }

    public List<Blog> mapBlogListFromBlogEntityList(List<BlogEntity> from) {
        return from.stream().map(this::mapBlogFromBlogEntity).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<String> mapBlogCategoriesFromBlogEntityList(List<BlogEntity> from) {
        return from.stream().map(this::mapBlogFromBlogEntity).map(Blog::getCategories).flatMap(List::stream).collect(Collectors.toList());
    }
}
