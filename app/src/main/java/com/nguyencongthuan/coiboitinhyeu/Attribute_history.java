package com.nguyencongthuan.coiboitinhyeu;

public class Attribute_history {
    private String myName;
    private String yourName;
    private String result;

    public Attribute_history(String myName, String yourName, String result) {
        this.myName = myName;
        this.yourName = yourName;
        this.result = result;
    }

    public String getMyName() {
        return myName;
    }

    public String getYourName() {
        return yourName;
    }

    public String getResult() {
        return result;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
