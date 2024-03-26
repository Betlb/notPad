package com.notdefteri.uygulama.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "note", schema = "public")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_note_id_seq")
    @SequenceGenerator(name = "note_note_id_seq", sequenceName = "note_note_id_seq", allocationSize = 1)
    @Column(name = "note_id", nullable = false, unique = true)
    private Long noteId;


    // Ignore serialization of the user property to break the recursion cok iyi
    @Column(name = "notepad_user_id")
    private Long notepadUserId;
//Onemli

    @Column(name = "category_id")
    private Long categoryId;
//Onemli
    @Column(name = "note_createtime", nullable = false)
    private Timestamp noteCreateTime;

    @Column(name = "note_updatetime")
    private Timestamp noteUpdateTime;


    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "content", length = 1000)
    private String content;


    //private String secureId;


    public Note() {
    }

    public Note(Long notepadUserId, Long categoryId, String title, String content) {
        Timestamp currentTime = Timestamp.from(Instant.now());
        this.notepadUserId = notepadUserId;
        this.categoryId = categoryId;
        this.noteCreateTime = currentTime;
        this.noteUpdateTime = currentTime;
        this.title = title;
        this.content = content;
    }

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

    public Long getUserId() {
        return categoryId;
    }

    public void setUserId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Timestamp getNoteCreateTime() {
        return noteCreateTime;
    }

    public void  setNoteCreateTime(Timestamp noteCreateTime) {
        this.noteCreateTime = noteCreateTime;
    }

    public Timestamp getNoteUpdateTime() {
        return noteUpdateTime;
    }

    public void setNoteUpdateTime(Timestamp noteUpdateTime) {
        this.noteUpdateTime = noteUpdateTime;
    }

    public Timestamp getNoteIdCreateTime() {
        return noteCreateTime;
    }

    public void setNoteIdCreateTime(Timestamp noteIdCreateTime) {
        this.noteCreateTime = noteIdCreateTime;
    }


    public Timestamp getNoteIdUpdateTime() {
        return noteUpdateTime;
    }

    public void setNoteIdUpdateTime(Timestamp noteIdUpdateTime) {
        this.noteUpdateTime = noteIdUpdateTime;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    /*
    public void setSecureId(String secureId) {
        this.secureId = secureId;
    }

    public String getSecureId() {
        return secureId;
    }*/


    /*public String toString() {
        StringBuilder jsonBuilder = new StringBuilder("{");
        jsonBuilder.append("\"noteId\":").append(noteId).append(",");
        jsonBuilder.append("\"title\":\"").append(title).append("\",");
        jsonBuilder.append("\"content\":\"").append(content).append("\"");
        jsonBuilder.append("}");

        return jsonBuilder.toString();
    }*/
}