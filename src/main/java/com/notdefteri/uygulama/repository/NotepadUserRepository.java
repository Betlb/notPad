package com.notdefteri.uygulama.repository;


import com.notdefteri.uygulama.model.NotepadUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NotepadUserRepository extends JpaRepository<NotepadUser, Long> {
    List<NotepadUser> findNotepadUserByUserNameAndPassword(String user_name, String password);

}
