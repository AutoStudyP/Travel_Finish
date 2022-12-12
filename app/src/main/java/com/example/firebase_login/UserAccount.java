package com.example.firebase_login;
//데이터 베이스 email(id)와 password(pwd)를 연동하기 위해 클래스 이용
public class UserAccount {
    private String emailid;
    private String password;
    private String idToken;
    public UserAccount(){

    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }


    public String getEmailid() {
        return emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
