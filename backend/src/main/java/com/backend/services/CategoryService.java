package com.backend.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.backend.entities.Category;
import com.backend.repositories.category.CategoryRepository;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    public List<Category> allCategories() {
        return this.categoryRepository.allCategories();
    }

    public Category addCategory(Category category) {
        return this.categoryRepository.addCategory(category);
    }

    public Category findCategory(Integer id) {
        return this.categoryRepository.findCategory(id);
    }
    public Category findCategory(String name) {
        return this.categoryRepository.findCategory(name);
    }

    public void deleteCategory(Integer id) {
        this.categoryRepository.deleteCategory(id);
    }


    public Category updateCategory(Integer id, Category category) {
        return this.categoryRepository.updateCategory(id,category);
    }
}
