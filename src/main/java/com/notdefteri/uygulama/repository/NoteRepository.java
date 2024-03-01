package com.notdefteri.uygulama.repository;

import com.notdefteri.uygulama.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

    List<Note> findNotesByNotepadUserIdAndCategoryId(Long userId,Long categoryId);


}
