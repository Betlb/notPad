package com.notdefteri.uygulama.controller;

import com.notdefteri.uygulama.model.Category;
import com.notdefteri.uygulama.model.Note;
import com.notdefteri.uygulama.service.CategoryService;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")

public class CategoryController {
    final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAllCategories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    /*@GetMapping("/getAllNotesOfCategory")
    public List<Note> getAllNotesOfCategory(@RequestParam Long id){
        return categoryService.getAllNotesOfCategory(id);
    }*/
    //bunu artik note sınıfından yapıyorum.Çünkü note list tutmsk mantıksız.
    @PostMapping("/addCategory")
    public Category addCategory(@RequestParam String categoryName){
        return categoryService.addCategory(categoryName);
    }
    @DeleteMapping("/deleteCategory")
    public void deleteCategory(@RequestParam Long id){
        categoryService.deleteCategory(id);
    }

}
