package com.example.crudnotas.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class NoteDAO {
    SQLiteDatabase database;

    public NoteDAO(Context context) {
        this.database = context.openOrCreateDatabase("dbNotes", context.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS Note(id Integer PRIMARY KEY AUTOINCREMENT, " +
                "title varchar," +
                "text varchar )");
    }

    public Note insertNote(Note note){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", note.getTitle());
        contentValues.put("text", note.getText());
        int i = (int) database.insert("Note", null, contentValues);

        note.setId(i);

        return note;
    }
}
