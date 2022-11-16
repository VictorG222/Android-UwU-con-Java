package com.victor.app193.HttpRequest.Response;

public class Notes {

    int id;
    String titulo, mensaje, date;

    public Notes(int id, String titulo, String mensaje, String date) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getDate() {
        return date;
    }
}
