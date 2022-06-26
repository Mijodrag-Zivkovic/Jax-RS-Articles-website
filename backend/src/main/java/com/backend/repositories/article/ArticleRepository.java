package com.backend.repositories.article;

import com.backend.entities.Article;

import java.util.List;

public interface ArticleRepository {
    List<Article> allArticles();

    Article addArticle(Article article);

    Article findArticle(Integer id);

    Article findArticle(String title);

    void deleteArticle(Integer id);

    Article updateArticle(Integer id, Article article);

    List<Article> articlesByCategoryId(Integer id);

    List<Article> articlesByTagId(Integer id);

    List<Article> pagination(Integer left, Integer right);

    Integer count();
}
