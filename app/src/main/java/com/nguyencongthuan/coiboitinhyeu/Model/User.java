package com.nguyencongthuan.coiboitinhyeu.Model;


import java.util.Date;

public class User {
    private String Username ;
    private String Password ;
    private String Fullname ;
    private Date DoB ;
    private int Gender ;

    public User(String username, String password, String fullname, Date doB, int gender) {
        Username = username;
        Password = password;
        Fullname = fullname;
        DoB = doB;
        Gender = gender;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public Date getDoB() {
        return DoB;
    }

    public void setDoB(Date doB) {
        DoB = doB;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }
}
