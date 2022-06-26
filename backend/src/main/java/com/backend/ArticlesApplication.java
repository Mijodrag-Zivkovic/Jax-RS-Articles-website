package com.backend;

import com.backend.repositories.article.ArticleRepository;
import com.backend.repositories.article.MySqlArticleRepository;
import com.backend.repositories.category.CategoryRepository;
import com.backend.repositories.category.MySqlCategoryRepository;
import com.backend.repositories.comment.CommentRepository;
import com.backend.repositories.comment.MySqlCommentRepository;
import com.backend.repositories.tag.MySqlTagRepository;
import com.backend.repositories.tag.TagRepository;
import com.backend.repositories.user.MySqlUserRepository;
import com.backend.repositories.user.UserRepository;
import com.backend.services.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class ArticlesApplication extends ResourceConfig {
    public ArticlesApplication() {

        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);


        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlUserRepository.class).to(UserRepository.class).in(Singleton.class);
                this.bind(MySqlCategoryRepository.class).to(CategoryRepository.class).in(Singleton.class);
                this.bind(MySqlArticleRepository.class).to(ArticleRepository.class).in(Singleton.class);
                this.bind(MySqlTagRepository.class).to(TagRepository.class).in(Singleton.class);
                this.bind(MySqlCommentRepository.class).to(CommentRepository.class).in(Singleton.class);
                //this.bind(InMemoryUserRepository.class).to(UserRepository.class).in(Singleton.class);

                //this.bindAsContract(SubjectService.class);
                this.bindAsContract(UserService.class);
                this.bindAsContract(CategoryService.class);
                this.bindAsContract(ArticleService.class);
                this.bindAsContract(TagService.class);
                this.bindAsContract(CommentService.class);
            }
        };
        register(binder);


        // Ucitavamo resurse
        packages("com.backend");
    }
}