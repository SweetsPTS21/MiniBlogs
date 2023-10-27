package com.dogoo.miniblogs.mapper;

import com.dogoo.miniblogs.entity.BlogEntity;
import com.dogoo.miniblogs.model.Blog;
import com.dogoo.miniblogs.model.BlogApproveReq;
import com.dogoo.miniblogs.model.BlogContent;
import com.dogoo.miniblogs.model.BlogReq;
import com.dogoo.miniblogs.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
@Component
public class BlogMapper {
    IBlogRepository todoRepository;

    @Autowired
    public BlogMapper(IBlogRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    //Convert BlogEntity from BlogReq to save to database
    //Using when create new blog
    public BlogEntity mapBlogEntityFromBlogReq(BlogReq from) {
        BlogEntity to = new BlogEntity();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatDateTime = date.format(formatter);

        to.setAuthor(from.getAuthor());
        to.setSummary(from.getSummary());
        to.setTitle(from.getTitle());
        to.setSource(from.getSource());
        to.setPublicDate(from.getPublicDate());
        to.setImage(from.getImage());
        to.setCategories(from.getCategories());
        to.setContent(from.getContent());
        to.setCreatedDate(formatDateTime);
        to.setUpdatedDate("2021-01-01");
        to.setApproved(false);
        return to;
    }

    //Convert BlogEntity from BlogReq to save to database
    //Using when update blog
    public BlogEntity mapBlogEntityFromBlogReq(String id, BlogReq from) {
        BlogEntity to = todoRepository.findById(id).get();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatDateTime = date.format(formatter);

        to.setId(id);
        to.setAuthorId(from.getAuthor());
        to.setSummary(from.getSummary());
        to.setTitle(from.getTitle());
        to.setSource(from.getSource());
        to.setPublicDate(from.getPublicDate());
        to.setImage(from.getImage());
        to.setCategories(from.getCategories());
        to.setContent(from.getContent());
        to.setUpdatedDate(formatDateTime);
        return to;
    }

    public BlogEntity mapBlogEntityFromBlogApproveReq(String id, BlogApproveReq from) {
        BlogEntity to = todoRepository.findById(id).get();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatDateTime = date.format(formatter);

        to.setId(id);
        to.setAuthorId(from.getAuthor());
        to.setApproved(from.getApproved());
        to.setUpdatedDate(formatDateTime);
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
        to.setCreateDate(from.getCreatedDate());
        to.setUpdateDate(from.getUpdatedDate());
        to.setAuthor(from.getAuthor());
        to.setApproved(from.isApproved());
        return to;
    }

    public List<Blog> mapBlogListFromBlogEntityList(List<BlogEntity> from) {
        return from.stream().map(this::mapBlogFromBlogEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
