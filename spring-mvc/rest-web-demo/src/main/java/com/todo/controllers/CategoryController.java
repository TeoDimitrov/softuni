package com.todo.controllers;

import com.todo.entities.Category;
import com.todo.models.CategoryViewModel;
import com.todo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<CategoryViewModel>> getCategories(){
        List<CategoryViewModel> categories =
                this.categoryService.getAllCategories();

        if(categories == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        ResponseEntity<List<CategoryViewModel>> responseEntity
                = new ResponseEntity(categories, HttpStatus.OK);

        return responseEntity;
    }
}
