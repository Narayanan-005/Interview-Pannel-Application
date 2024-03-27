package com.zsgs.interviewpannel.model;

public class Credential {
    private String userName;
    private String passWord;
    private char role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public char getRole() {
        return role;
    }

    public void setRole(char role) {
        this.role = role;
    }
}
