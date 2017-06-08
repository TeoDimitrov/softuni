package com.todo.services;

import com.todo.entities.Category;
import com.todo.models.CategoryViewModel;

import java.util.List;

public interface CategoryService {

    List<CategoryViewModel> getAllCategories();

    Category findOneByName(String name);
}
