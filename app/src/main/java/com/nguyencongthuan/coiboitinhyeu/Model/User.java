package com.nguyencongthuan.coiboitinhyeu.Model;


import android.text.Editable;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String username ;
    private String password ;
    private String fullname ;
    private String doB ;
    private int gender ;
    private String ava;

    public User(String username, String password, String fullname, String doB, int gender) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.doB = doB;
        this.gender = gender;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDoB() {
        return doB;
    }

    public void setDoB(String doB) {
        this.doB = doB;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAva() {
        return ava;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }
}
