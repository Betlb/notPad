package com.notdefteri.uygulama.service;

import com.notdefteri.uygulama.model.Note;
import com.notdefteri.uygulama.model.NotepadUser;
import com.notdefteri.uygulama.modelView.NoteView;
import com.notdefteri.uygulama.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createNote(Note initialNote) {
        return noteRepository.save(new Note(initialNote.getNotepadUserId(),initialNote.getCategoryId(),initialNote.getTitle(),initialNote.getContent()));
    }

    public Note editNote(Long noteId, Note newNote) {
        Note updatedNotedb = noteRepository.findById(noteId).orElseThrow(() -> new IllegalStateException("The note is not exist."));
        //if(noteId!=newNote.getNoteId()){
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        updatedNotedb.setNoteIdUpdateTime(currentTimeStamp);
        updatedNotedb.setContent(newNote.getContent());
        updatedNotedb.setTitle(newNote.getTitle());
        updatedNotedb.setCategoryId(newNote.getCategoryId());

        return noteRepository.save(updatedNotedb);
    }


    public Note getNoteById(Long noteId) {
        return noteRepository.findById(noteId).orElseThrow(() -> new IllegalStateException("Böyle bir kullanıcı yok!!"));
    }


    public String deleteNote(Long id) {
         noteRepository.deleteById(id);
         return  "The note is deleted.";
    }


    public List<NoteView> getNotesByCategoryAndUser(Long userId, Long categorId) {
        List<Note> lstNote = noteRepository.findNotesByNotepadUserIdAndCategoryId(userId,categorId);

        if(lstNote.isEmpty())
            return new ArrayList<>();

        List<NoteView> nvList = new ArrayList<>();
        for(int i=0;i<lstNote.size();i++){
            NoteView nv = new NoteView();
            nv.setCategoryId(categorId);
            nv.setNoteId(lstNote.get(i).getNoteId());
            nv.setContent(lstNote.get(i).getContent());
            nv.setTitle(lstNote.get(i).getTitle());
            nv.setNotepadUserId(lstNote.get(i).getNotepadUserId());
            nvList.add(nv);
        }

        return nvList;
    }
}
