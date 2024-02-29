package com.notdefteri.uygulama.modelView;

import com.notdefteri.uygulama.model.Note;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CategoryView {
    private Long categoryId;
    private List<Note> notes = new ArrayList<>();
    private Timestamp categoryCreateTime;
    private Timestamp categoryUpdateTime;
    private String category_name;

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
