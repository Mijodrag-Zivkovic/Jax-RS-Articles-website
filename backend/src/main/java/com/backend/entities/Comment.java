package com.backend.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Comment {

    private Integer id;
    @NotNull(message = "Name field is required")
    @NotEmpty(message = "Name field is required")
    private String name;
    @NotNull(message = "Content field is required")
    @NotEmpty(message = "Content field is required")
    private String content;
    private String dateTime;
    private Integer articleId;

    public Comment() {
    }

    public Comment(Integer id, String name, String content, String dateTime, Integer articleId) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.dateTime = dateTime;
        this.articleId = articleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
