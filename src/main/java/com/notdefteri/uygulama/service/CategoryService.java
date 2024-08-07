package com.notdefteri.uygulama.service;

import com.notdefteri.uygulama.model.Category;
import com.notdefteri.uygulama.model.Note;
import com.notdefteri.uygulama.repository.CategoryRepository;
import com.notdefteri.uygulama.repository.NoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {
    final CategoryRepository categoryRepository;
    final NoteRepository noteRepository;

    public CategoryService(CategoryRepository categoryRepository,NoteRepository noteRepository) {
        this.categoryRepository = categoryRepository;
        this.noteRepository = noteRepository;
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

    public Category addCategory(String categoryName,Long userId) throws CategoryAlreadyExistException{
        List<Category> categoryList = categoryRepository.findCategoryByCategoryName(categoryName);
        if(categoryList!=null && !categoryList.isEmpty()) {
            throw new CategoryAlreadyExistException(HttpStatus.BAD_REQUEST,"Category already exists!");
        }
        else return categoryRepository.save(new Category(categoryName,userId));

    }


    public void deleteCategory(Long userId,Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category is not found!!"));
        List<Note> notes = noteRepository.findNotesByNotepadUserIdAndCategoryIdOrderByNoteCreateTime(userId,categoryId);
        for (Note note : notes) {
            noteRepository.deleteById(note.getNoteId());
        }

        categoryRepository.delete(category);

    }
}
