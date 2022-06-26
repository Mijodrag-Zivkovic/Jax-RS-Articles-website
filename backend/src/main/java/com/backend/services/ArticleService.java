package com.backend.services;

import com.backend.entities.Article;
import com.backend.entities.Category;
import com.backend.repositories.article.ArticleRepository;

import javax.inject.Inject;
import java.util.List;

public class ArticleService {

    @Inject
    ArticleRepository articleRepository;

    public List<Article> allArticles() {
        return this.articleRepository.allArticles();
    }

    public Article addArticle(Article article) {
        return this.articleRepository.addArticle(article);
    }

    public Article findArticle(Integer id) {
        return this.articleRepository.findArticle(id);
    }

    public Article findArticle(String title) {
        return this.articleRepository.findArticle(title);
    }

    public void deleteArticle(Integer id) {
        this.articleRepository.deleteArticle(id);
    }


    public Article updateArticle(Integer id, Article article) {
        return this.articleRepository.updateArticle(id,article);
    }

    public List<Article> articlesByCategoryId(Integer id)
    {
        return this.articleRepository.articlesByCategoryId(id);
    }

    public List<Article> articlesByTagId(Integer id) {
        return this.articleRepository.articlesByTagId(id);
    }

    public List<Article> pagination(Integer left, Integer right) {
        return this.articleRepository.pagination(left,right);
    }

    public Integer count() {
        return this.articleRepository.count();
    }
}
