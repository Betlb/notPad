package com.notdefteri.uygulama.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category", schema = "public")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_category_id_seq")
    @SequenceGenerator(name = "category_category_id_seq", sequenceName = "category_category_id_seq", allocationSize = 1)
    @Column(name = "category_id", nullable = false, unique = true)
    private Long categoryId;


//Onemli
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY )
    private List<Note> notes = new ArrayList<>();

//Onemli
    @Column(name = "category_createtime")
    private Timestamp categoryCreateTime;



    @Column(name = "category_updatetime")
    private Timestamp categoryUpdateTime;

    @Column(name = "category_name")
    private String category_name;


    public Category() {
    }

    public Category(String category_name) {
        this.category_name = category_name;
        Timestamp currentTime = Timestamp.from(Instant.now());
        this.categoryCreateTime = currentTime;
        this.categoryUpdateTime = currentTime;
    }


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public Timestamp getCategoryCreateTime() {
        return categoryCreateTime;
    }

    public void setCategoryCreateTime(Timestamp categoryCreateTime) {
        this.categoryCreateTime = categoryCreateTime;
    }



    public Timestamp getCategoryIdUpdateTime() {
        return categoryUpdateTime;
    }

    public void setCategoryIdUpdateTime(Timestamp categoryIdUpdateTime) {
        this.categoryUpdateTime = categoryIdUpdateTime;
    }


    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
