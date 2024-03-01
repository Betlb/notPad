package com.notdefteri.uygulama.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "notepad_user", schema = "public")
public class NotepadUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_user_id_seq")
    @SequenceGenerator(name = "user_user_id_seq", sequenceName = "user_user_id_seq", allocationSize = 1)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "user_createtime")
    private Timestamp userCreateTime;


    @Column(name = "user_updatetime")
    private Timestamp userUpdateTime;

    @Column(name = "user_name", length = 100)
    private String userName;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Column(name = "email", length = 50, nullable = false,unique = true)
    private String email;


    //private String secureId;




    // Constructors, Getters, and Setters...


    public NotepadUser() {
    }


    public NotepadUser(String userName, String password,String email) {
        this.userName = userName;
        this.password = password;
        Timestamp currentTime = Timestamp.from(Instant.now());
        this.userCreateTime = currentTime;
        this.userUpdateTime = currentTime;
        this.email=email;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }



    public Timestamp getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Timestamp userCreateTime) {
        this.userCreateTime = userCreateTime;
    }


    public Timestamp getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Timestamp userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    /*
    public String getSecureId() {
        return secureId;
    }

    public void setSecureId(String secureId) {
        this.secureId = secureId;
    }
*/

//    public String toString() {
//        StringBuilder jsonBuilder = new StringBuilder("{");
//        jsonBuilder.append("\"userId\":").append(userId).append(",");
//        jsonBuilder.append("\"userCreateTime\":\"").append(userCreateTime).append("\",");
//        jsonBuilder.append("\"updateUserId\":").append(updateUserId).append(",");
//        jsonBuilder.append("\"userUpdateTime\":\"").append(userUpdateTime).append("\",");
//        jsonBuilder.append("\"userName\":\"").append(userName).append("\",");
//        jsonBuilder.append("\"password\":\"").append(password).append("\",");
//        jsonBuilder.append("\"notes\":[");
//
//        for (int i = 0; i < notes.size(); i++) {
//            jsonBuilder.append(notes.get(i).toString());
//            if (i < notes.size() - 1) {
//                jsonBuilder.append(",");
//            }
//        }
//
//        jsonBuilder.append("]}");
//
//        return jsonBuilder.toString();
//    }
}
