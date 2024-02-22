package com.notdefteri.uygulama.service;

import com.notdefteri.uygulama.CommonUtil;
import com.notdefteri.uygulama.model.Note;
import com.notdefteri.uygulama.model.NotepadUser;
import com.notdefteri.uygulama.repository.NotepadUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NotepadUserService {
    final NotepadUserRepository notepadUserRepository;


    public NotepadUserService(NotepadUserRepository notepadUserRepository) {
        this.notepadUserRepository = notepadUserRepository;
    }

    public NotepadUser login(String user_name,String pass) {
        List<NotepadUser> lstUser = notepadUserRepository.findNotepadUserByUserNameAndPassword(user_name,pass);

        if (lstUser.size() > 0){
            return lstUser.get(0);
        }
            return null;
    }

    public NotepadUser addNewUser(NotepadUser notepadUser) {
        return notepadUserRepository.save(notepadUser);
    }


    //bunu deneme için yaptim bi amaci yok.
    public List<NotepadUser> getUsers() {
        List<NotepadUser> lstUser = notepadUserRepository.findAll();
        return lstUser;
    }


    @Transactional
    public List<Note> getUserAllNotes(Long id) {
        NotepadUser user = notepadUserRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User is not found!!"));
       /* String  secureId = CommonUtil.generateSHA256(user.getUserId().toString());*/
        List<Note> notes = user.getNotes();


        if (notes != null)
            return notes;
        else return new ArrayList<>();//for handle null notes situation

    }

    //bu secure olayi icin deneddim bir şey ama önemli değil.
    public NotepadUser loginWithId(Long id) {
        return notepadUserRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User is not found!!"));
    }
}
