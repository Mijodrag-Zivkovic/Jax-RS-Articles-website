package com.backend.repositories.category;

import com.backend.entities.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> allCategories();

    Category addCategory(Category category);
    Category findCategory(Integer id);

    Category findCategory(String name);


    void deleteCategory(Integer id);

    Category updateCategory(Integer id, Category category);
}
