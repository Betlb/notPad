package com.notdefteri.uygulama.service;


import com.notdefteri.uygulama.model.Note;
import com.notdefteri.uygulama.model.NotepadUser;
import com.notdefteri.uygulama.modelView.NotepadUserView;
import com.notdefteri.uygulama.repository.NotepadUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NotepadUserService {
    final NotepadUserRepository notepadUserRepository;


    public NotepadUserService(NotepadUserRepository notepadUserRepository) {
        this.notepadUserRepository = notepadUserRepository;
    }

    public NotepadUserView login(NotepadUser user) {
        List<NotepadUser> lstUser = notepadUserRepository.findNotepadUserByUserNameAndPassword(user.getUserName(),user.getPassword());

        if (!lstUser.isEmpty()){
            NotepadUserView nuv = new NotepadUserView();
            nuv.setUserId(lstUser.get(0).getUserId());
            nuv.setUserName(lstUser.get(0).getUserName());
            nuv.setEmail(lstUser.get(0).getEmail());
            return nuv;
        }
            return null;
    }

    public NotepadUserView addNewUser(NotepadUser notepadUser) {

        List<NotepadUser> lstUser = notepadUserRepository.findNotepadUserByUserNameAndEmail(notepadUser.getUserName(),notepadUser.getEmail());
        if (lstUser.isEmpty()){
            NotepadUser notepadUser1 = new NotepadUser(notepadUser.getUserName(),notepadUser.getPassword(),notepadUser.getEmail());
            notepadUserRepository.save(notepadUser1);
            NotepadUserView nuv = new NotepadUserView();//burda lstUser yazınca 500 error veriyor notepadUser1 yazınca duzeldi ama neden?
            nuv.setUserId(notepadUser1.getUserId());
            nuv.setUserName(notepadUser1.getUserName());
            nuv.setEmail(notepadUser1.getEmail());
            return nuv;
        }
        return null;

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
    public NotepadUserView loginWithId(Long id) {
        NotepadUser nu = notepadUserRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User is not found!!"));
        NotepadUserView nuv = new NotepadUserView();
        nuv.setUserId(nu.getUserId());
        nuv.setEmail(nu.getEmail());
        nuv.setNotes(nu.getNotes());
        nuv.setUserName(nu.getUserName());
        return nuv;
    }
}
