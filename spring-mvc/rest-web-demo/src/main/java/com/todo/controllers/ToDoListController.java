package com.todo.controllers;

import com.todo.models.ToDoItemBindingModel;
import com.todo.models.ToDoItemViewModel;
import com.todo.services.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ToDoListController {

    @Autowired
    private ToDoItemService toDoItemService;

    @PostMapping("")
    public ResponseEntity<ToDoItemViewModel> saveItem(@RequestBody ToDoItemBindingModel toDoItemBindingModel){
        ToDoItemViewModel toDoItemViewModel = this.toDoItemService.saveItem(toDoItemBindingModel);
        return new ResponseEntity(toDoItemViewModel, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ToDoItemViewModel>> getItemsByCategory(@PathVariable long categoryId){
        List<ToDoItemViewModel> toDoItemViewModels = this.toDoItemService.getAllItemsByCategoryId(categoryId);
        return new ResponseEntity<List<ToDoItemViewModel>>(toDoItemViewModels, HttpStatus.OK);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity update(@PathVariable long itemId, @RequestBody ToDoItemBindingModel toDoItemBindingModel){
        this.toDoItemService.updateItem(itemId, toDoItemBindingModel);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity delete(@PathVariable long itemId){
        this.toDoItemService.deleteById(itemId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ToDoItemViewModel>> search(@RequestParam String searchWord, @RequestParam long categoryId){
        List<ToDoItemViewModel> toDoItemViewModels = this.toDoItemService.searchByWordAndCategory(searchWord, categoryId);
        return new ResponseEntity(toDoItemViewModels, HttpStatus.OK);
    }
}
