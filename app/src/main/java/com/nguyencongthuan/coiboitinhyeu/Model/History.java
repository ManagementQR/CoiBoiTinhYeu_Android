package com.nguyencongthuan.coiboitinhyeu.Model;

public class History {
     private int id;
     private String username;
     private String fullname;
     private String infor;
     private String result;

    public History(String username, String fullname, String infor, String result) {
        this.username = username;
        this.fullname = fullname;
        this.infor = infor;
        this.result = result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
