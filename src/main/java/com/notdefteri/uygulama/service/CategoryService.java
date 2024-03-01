package com.notdefteri.uygulama.service;

import com.notdefteri.uygulama.model.Category;
import com.notdefteri.uygulama.model.Note;
import com.notdefteri.uygulama.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {
    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /*public List<Note> getAllNotesOfCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Category is not found!!"));
        List<Note> notes=category.getNotes();
        if(notes!=null)
            return notes;
        else return new ArrayList<>();
    }*/

    public Category addCategory(String categoryName) {
        return categoryRepository.save(new Category(categoryName));
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Category is not found!!"));;
        categoryRepository.delete(category);
    }
}
