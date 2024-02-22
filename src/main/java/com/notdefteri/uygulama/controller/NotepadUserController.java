package com.notdefteri.uygulama.controller;

import com.notdefteri.uygulama.model.Note;
import com.notdefteri.uygulama.model.NotepadUser;
import com.notdefteri.uygulama.service.NoteService;
import com.notdefteri.uygulama.service.NotepadUserService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("notepadUser")
public class NotepadUserController {

    final NotepadUserService notepadUserService;



    public NotepadUserController(NotepadUserService notepadUserService, NoteService noteService) {
        this.notepadUserService = notepadUserService;
    }



    /*@GetMapping("/loginNotepad")
    public ResponseEntity<NotepadUser> login(@RequestParam String user_name, @RequestParam String pass) {
        return ResponseEntity.ok(notepadUserService.login(user_name,pass));
    }*/
    @GetMapping("/loginNotepad")
    public NotepadUser login(@RequestParam String user_name, @RequestParam String pass) {
        return notepadUserService.login(user_name,pass);
    }

    @GetMapping("/loginWithId")
    public NotepadUser loginWithId(@RequestParam Long id){
        return notepadUserService.loginWithId(id);
    }

    @PostMapping("/addNewUser")
    public NotepadUser addNewUser(@RequestBody NotepadUser notepadUser) {
        return notepadUserService.addNewUser(notepadUser);
    }

   /* @GetMapping("/getUsers")
    public String getUsers(){
        return notepadUserService.getUsers().toString();
    }*/

    @GetMapping("/getUserAllNotes")
    public List<Note> getUserAllNotes(@RequestParam Long id) throws NoSuchAlgorithmException {
        return notepadUserService.getUserAllNotes(id);

    }





}
