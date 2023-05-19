package com.example.crudnotas.model;

public class Note {
    Integer id;
    String title;
    String text;

    public Note(Integer id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public Note(String title, String text) {
        this.title = title;
        this.text = text;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
