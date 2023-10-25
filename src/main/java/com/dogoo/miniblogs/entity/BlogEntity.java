package com.dogoo.miniblogs.entity;

import com.dogoo.miniblogs.model.BlogContent;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "blogs")
@Indexed
public class BlogEntity {
    @Id
    private String id;
    @TextIndexed
    private String title;
    @TextIndexed
    private String summary;
    private String image;
    private String publicDate;
    private String source;
    private List<BlogContent> content;
    private String author;
    private String createdDate;
    private String updatedDate;
    private List<String> categories;
    private String authorId;
    private boolean approved;

    public BlogEntity() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getImage() {
        return image;
    }

    public String getPublicDate() {
        return publicDate;
    }

    public String getSource() {
        return source;
    }

    public List<BlogContent> getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getAuthorId() {
        return authorId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public void setContent(List<BlogContent> content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }


    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
