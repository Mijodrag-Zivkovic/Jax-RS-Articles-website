package com.backend.entities;

public class Articles_Tags {

    private Integer id;
    private Integer articleId;
    private Integer tagId;

    public Articles_Tags(Integer id, Integer articleId, Integer tagId) {
        this.id = id;
        this.articleId = articleId;
        this.tagId = tagId;
    }

    public Articles_Tags() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
