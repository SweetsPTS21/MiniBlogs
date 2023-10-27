package com.dogoo.miniblogs.mock;

import com.dogoo.miniblogs.entity.BlogEntity;
import com.dogoo.miniblogs.model.Blog;
import com.dogoo.miniblogs.model.BlogApproveReq;
import com.dogoo.miniblogs.model.BlogContent;
import com.dogoo.miniblogs.model.BlogReq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlogData {
    public static String ID = "600f1c7b9c3b9e0b7c7b3b3b";
    public static String TRUE_ID = "6534da21ecdc45244eabdbb9";
    public static String AUTHOR_ID = "600f1c7b9c3b9e0b7c7b3b3b";
    public static String SUMMARY = "This is summary";
    public static String TITLE = "This is title";
    public static String SOURCE = "This is source";
    public static String AUTHOR = "This is author";
    public static String PUBLIC_DATE = "2021-01-01";
    public static String IMAGE = "This is image";
    public static List<String> CATEGORIES = Arrays.asList("category1", "category2");
    public static List<BlogContent> CONTENT = initContent();
    public static String CREATED_DATE = "2021-01-01";
    public static String UPDATED_DATE = "2021-01-01";
    public static boolean APPROVED = false;

    public static List<BlogContent> initContent() {
        List<BlogContent> content = new ArrayList<>();
        BlogContent blogContent = new BlogContent();
        blogContent.setTitle("This is content");
        blogContent.setDetail("This is detail");

        content.add(blogContent);
        return content;
    }

    public static Blog mockBlog() {
        Blog blog = new Blog();
        blog.setId(ID);
        blog.setAuthor(AUTHOR);
        blog.setSummary(SUMMARY);
        blog.setTitle(TITLE);
        blog.setSource(SOURCE);
        blog.setPublicDate(PUBLIC_DATE);
        blog.setImage(IMAGE);
        blog.setCategories(CATEGORIES);
        blog.setContent(CONTENT);
        blog.setUpdateDate(CREATED_DATE);
        blog.setCreateDate(UPDATED_DATE);
        blog.setApproved(APPROVED);
        return blog;
    }

    public static List<Blog> mockBlogList() {
        List<Blog> blogs = new ArrayList<>();
        blogs.add(mockBlog());
        return blogs;
    }

    public static BlogReq mockBlogReq() {
        BlogReq blogReq = new BlogReq();
        blogReq.setAuthor(AUTHOR);
        blogReq.setSummary(SUMMARY);
        blogReq.setTitle(TITLE);
        blogReq.setSource(SOURCE);
        blogReq.setPublicDate(PUBLIC_DATE);
        blogReq.setImage(IMAGE);
        blogReq.setCategories(CATEGORIES);
        blogReq.setContent(CONTENT);
        return blogReq;
    }

    public static BlogApproveReq mockBlogApproveReq() {
        BlogApproveReq blogApproveReq = new BlogApproveReq();
        blogApproveReq.setAuthor(AUTHOR);
        blogApproveReq.setBlogId(ID);
        blogApproveReq.setApproved(APPROVED);
        return blogApproveReq;
    }

    public static BlogEntity mockBlogEntity() {
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setId(ID);
        blogEntity.setAuthorId(AUTHOR_ID);
        blogEntity.setSummary(SUMMARY);
        blogEntity.setTitle(TITLE);
        blogEntity.setSource(SOURCE);
        blogEntity.setPublicDate(PUBLIC_DATE);
        blogEntity.setImage(IMAGE);
        blogEntity.setCategories(CATEGORIES);
        blogEntity.setContent(CONTENT);
        blogEntity.setCreatedDate(CREATED_DATE);
        blogEntity.setUpdatedDate(UPDATED_DATE);
        blogEntity.setApproved(APPROVED);
        blogEntity.setAuthor(AUTHOR);
        return blogEntity;
    }

    public static List<BlogEntity> mockBlogEntityList() {
        List<BlogEntity> blogEntities = new ArrayList<>();
        blogEntities.add(mockBlogEntity());
        return blogEntities;
    }
}

