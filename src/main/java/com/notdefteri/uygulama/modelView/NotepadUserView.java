package com.notdefteri.uygulama.modelView;

import com.notdefteri.uygulama.model.Note;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NotepadUserView {
    private Long userId;
    private Timestamp userCreateTime;
    private Timestamp userUpdateTime; //bunu hic kullanmadim?!
    private String userName;
    private String password;
    private String email;
    private List<Note> notes = new ArrayList<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
