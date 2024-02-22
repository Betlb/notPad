package com.notdefteri.uygulama.service;

import com.notdefteri.uygulama.model.Note;
import com.notdefteri.uygulama.model.NotepadUser;
import com.notdefteri.uygulama.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class NoteService {

    final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createNote(Note initialNote) {
        return noteRepository.save(initialNote);
    }

    public Note editNote(Long noteId, Note newNote) {
        Note updatedNotedb = noteRepository.findById(noteId).orElseThrow(() -> new IllegalStateException("The note is not exist."));
        //if(noteId!=newNote.getNoteId()){
        updatedNotedb.setNoteId(newNote.getNoteId());
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        updatedNotedb.setNoteIdUpdateTime(currentTimeStamp);
        updatedNotedb.setContent(newNote.getContent());
        updatedNotedb.setTitle(newNote.getTitle());

        return noteRepository.save(updatedNotedb);
    }


    public Note getNoteById(Long noteId) {
        return noteRepository.findById(noteId).orElseThrow(() -> new IllegalStateException("Böyle bir kullanıcı yok!!"));
    }


    public String deleteNote(Long id) {
         noteRepository.deleteById(id);
         return  "The note is deleted.";
    }
}
