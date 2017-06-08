package com.todo.serviceImpl;

import com.todo.entities.Category;
import com.todo.models.CategoryViewModel;
import com.todo.repositories.CategoryRepository;
import com.todo.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryViewModel> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAllCategories();
        List<CategoryViewModel> categoryViewModels = new ArrayList<>();
        for (Category category : categories) {
            CategoryViewModel model = this.modelMapper.map(category, CategoryViewModel.class);
            categoryViewModels.add(model);
        }

        return categoryViewModels;
    }

    @Override
    public Category findOneByName(String name) {
        return this.categoryRepository.findOneByName(name);
    }
}
