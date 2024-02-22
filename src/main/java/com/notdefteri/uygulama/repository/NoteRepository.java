package com.notdefteri.uygulama.repository;

import com.notdefteri.uygulama.model.Note;
import com.notdefteri.uygulama.model.NotepadUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

}
