package com.example.gbwhastapp.models;


public class Users {
    String profilepic;
    String userName;
    String status;


    public String getUserId() {
        return userId;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMassage() {
        return lastMassage;
    }

    public void setLastMassage(String lastMassage) {
        this.lastMassage = lastMassage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Users(String profilepic, String userName, String status, String mail, String password, String userId, String lastMassage) {
        this.profilepic = profilepic;
        this.userName = userName;
        this.status = status;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.lastMassage = lastMassage;
    }

    public Users() {
    }

    String mail;
    String password;
    String userId;
    String lastMassage;

    // singUp Contructor

    public Users(String userName, String mail, String password) {

        this.userName = userName;
        this.mail = mail;
        this.password = password;

    }
}