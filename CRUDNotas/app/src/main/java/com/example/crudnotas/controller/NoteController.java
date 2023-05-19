package com.example.crudnotas.controller;

import android.content.Context;

import com.example.crudnotas.model.Note;
import com.example.crudnotas.model.NoteDAO;

public class NoteController {
    Context nContext;
    NoteDAO noteDAO;
    public NoteController(Context context) {
        nContext = context;
        noteDAO = new NoteDAO(context);
    }

    public Note registerNote(Note note){
        noteDAO.insertNote(note);
    }
}
