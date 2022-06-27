package com.example.gbwhastapp.models;


public class MessagesModule {
    String uId,message, mesaageId;
    long timestamp;

    public MessagesModule(String uId, String message) {
        this.uId = uId;
        this.message = message;
    }

    public MessagesModule(String uId, String message, long timestamp) {
        this.uId = uId;
        this.message = message;
        this.timestamp = timestamp;
    }
    public MessagesModule(){}

    public String getMesaageId(String key) {
        return mesaageId;
    }

    public void setMesaageId(String mesaageId) {
        this.mesaageId = mesaageId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMesaageId() {
        return mesaageId;
    }
}
