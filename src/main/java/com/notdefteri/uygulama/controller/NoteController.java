package com.notdefteri.uygulama.controller;

import com.notdefteri.uygulama.model.Note;
import com.notdefteri.uygulama.modelView.NoteView;
import com.notdefteri.uygulama.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("/note")
public class NoteController {

    final NoteService noteService;


    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping("/getNote")
    public Note getNoteById(@RequestParam Long note_id){
        return noteService.getNoteById(note_id);
    }

    @PostMapping("/createNote")
    public Note createNote(@RequestBody Note initialNote){
        return noteService.createNote(initialNote);

    }
    @PutMapping("/editNote")
    public Note editNote(@RequestParam Long note_id, @RequestBody Note updatedNote){
        return noteService.editNote(note_id,updatedNote);
    }

    @DeleteMapping("/deleteNote")
    public String deleteNote(@RequestParam Long id){
        return noteService.deleteNote(id);
    }

    @GetMapping("getUserNotesWithCategories")
    public List<NoteView> getNotesByCategoryAndUser(@RequestParam Long userId,@RequestParam Long categoryId){
        return noteService.getNotesByCategoryAndUser(userId,categoryId);
    }



}
