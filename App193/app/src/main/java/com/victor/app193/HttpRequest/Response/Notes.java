package com.victor.app193.HttpRequest.Response;

public class Notes {

    int id;
    String title, message, date;

    public Notes(int id, String tittle, String message, String date) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return title;
    }

    public String getMensaje() {
        return message;
    }

    public String getDate() {
        return date;
    }
}
