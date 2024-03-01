package com.notdefteri.uygulama.modelView;

import com.notdefteri.uygulama.model.Category;
import com.notdefteri.uygulama.model.NotepadUser;

import java.sql.Timestamp;

public class NoteView {
    private Long noteId;
    private Long notepadUserId;
    private Long categoryId;
    private Timestamp noteCreateTime;
    private Timestamp noteUpdateTime;
    private String title;
    private String content;

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Long getNotepadUserId() {
        return notepadUserId;
    }

    public void setNotepadUserId(Long notepadUserId) {
        this.notepadUserId = notepadUserId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
