package com.notdefteri.uygulama.controller;

import com.notdefteri.uygulama.model.Note;
import com.notdefteri.uygulama.service.NoteService;
import org.springframework.web.bind.annotation.*;


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
    @PostMapping("/editNote")
    public Note editNote(@RequestParam Long note_id, @RequestParam Note updatedNote){
        return noteService.editNote(note_id,updatedNote);
    }

    @DeleteMapping("/deleteNote")
    public String deleteNote(@RequestParam Long id){
        return noteService.deleteNote(id);
    }





}
