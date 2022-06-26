package com.backend.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Article {


    private Integer id;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String title;
    @NotNull(message = "Content field is required")
    @NotEmpty(message = "Content field is required")
    private String content;
    private String timeOfWriting;
    private Integer numberOfVisits;
    private Integer authorId;
    private Integer categoryId;

    public Article() {
    }

    public Article(Integer id, String title, String content, String timeOfWriting, Integer numberOfVisits, Integer authorId, Integer categoryId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timeOfWriting = timeOfWriting;
        this.numberOfVisits = numberOfVisits;
        this.authorId = authorId;
        this.categoryId = categoryId;
    }

    public String getTimeOfWriting() {
        return timeOfWriting;
    }

    public void setTimeOfWriting(String timeOfWriting) {
        this.timeOfWriting = timeOfWriting;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(Integer numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
