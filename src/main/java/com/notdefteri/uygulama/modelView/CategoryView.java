package com.notdefteri.uygulama.modelView;

import com.notdefteri.uygulama.model.Note;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CategoryView {
    private Long categoryId;
    private Timestamp categoryCreateTime;
    private Timestamp categoryUpdateTime;
    private String category_name;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
