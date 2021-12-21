package com.nguyencongthuan.coiboitinhyeu.Model;


import java.util.Date;

public class User {
    private String username ;
    private String password ;
    private String fullname ;
    private Date doB ;
    private int gender ;

    public User(String username, String password, String fullname, Date doB, int gender) {
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

    public Date getDoB() {
        return doB;
    }

    public void setDoB(Date doB) {
        this.doB = doB;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
