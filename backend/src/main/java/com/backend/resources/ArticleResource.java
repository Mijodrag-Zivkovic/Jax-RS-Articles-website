package com.backend.resources;

import com.backend.entities.Article;
import com.backend.entities.Category;
import com.backend.entities.User;
import com.backend.services.ArticleService;
import com.backend.services.CategoryService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/articles")
public class ArticleResource {
    @Inject
    ArticleService articleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> all()
    {
        return this.articleService.allArticles();
    }

    @GET
    @Path("/pagination")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> pagination(@QueryParam("left") Integer left, @QueryParam("right") Integer right)
    {
        return this.articleService.pagination(left,right);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Article create(@Valid Article article) {
        return this.articleService.addArticle(article);
    }

    @GET
    @Path("/article")
    @Produces(MediaType.APPLICATION_JSON)
    public Article find(@QueryParam("id") Integer id, @QueryParam("title") String name) {
        if(name==null)
            return this.articleService.findArticle(id);
        else
            return this.articleService.findArticle(name);
    }

    @GET
    @Path("/categoryId")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> articlesByCategoryId(@QueryParam("id") Integer id){
        return this.articleService.articlesByCategoryId(id);
    }

    @GET
    @Path("/tagId")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> articlesByTagId(@QueryParam("id") Integer id){
        return this.articleService.articlesByTagId(id);
    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Article update(@PathParam("id") Integer id, @Valid Article article){return this.articleService.updateArticle(id, article);}

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.articleService.deleteArticle(id);
    }

    @GET
    @Path("/count")
    public Integer count(){
        return this.articleService.count();
    }
}
