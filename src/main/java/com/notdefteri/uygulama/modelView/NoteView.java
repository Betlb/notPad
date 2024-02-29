package com.notdefteri.uygulama.modelView;

import com.notdefteri.uygulama.model.Category;
import com.notdefteri.uygulama.model.NotepadUser;

import java.sql.Timestamp;

public class NoteView {
    private Long noteId;
    private NotepadUser notepad_user;
    private Category category;
    private Timestamp noteCreateTime;
    private Timestamp noteUpdateTime;
    private String title;
    private String content;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
