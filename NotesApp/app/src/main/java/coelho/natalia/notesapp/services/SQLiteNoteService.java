package coelho.natalia.notesapp.services;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import coelho.natalia.notesapp.models.Note;

public class SQLiteNoteService implements INoteService{
    private SQLiteDatabase _database;

    public SQLiteNoteService(SQLiteDatabase database) {
        this._database = database;
    }

    @Override
    public Note CreateNote(String text) {
        Note insertedNote = new Note(text);

        ContentValues values = new ContentValues();
        values.put("id", insertedNote.getId().toString());
        values.put("text", text);
        values.put("created_datetime", insertedNote.getCreatedDateTime().toString());
        values.put("modified_datetime", insertedNote.getModifiedDateTime().toString());

        this._database.insert("notes", null, values);

        return insertedNote;
    }

    @Override
    public List<Note> ListNotes() {
        List<Note> notes = new ArrayList<>();

        Cursor cursor = this._database.query(
                "notes",
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null)
            return null;

        try {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String text = cursor.getString(cursor.getColumnIndex("text"));
                @SuppressLint("Range") String createdDateTime = cursor.getString(cursor.getColumnIndex("created_datetime"));
                @SuppressLint("Range") String modifiedDateTime = cursor.getString(cursor.getColumnIndex("modified_datetime"));

                Note note = new Note(
                        UUID.fromString(id),
                        text,
                        OffsetDateTime.parse(createdDateTime),
                        OffsetDateTime.parse(modifiedDateTime)
                );
                notes.add(note);
            }
        } finally {
            cursor.close();
        }

        return notes;
    }

    @Override
    public Note GetNote(UUID noteId) {
        Note note = null;

        String[] columns = {
                "id",
                "text",
                "created_datetime",
                "modified_datetime"};

        String selection = "id = ?";
        String[] selectionArgs = {noteId.toString()};

        Cursor cursor = this._database.query(
                "notes",
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor == null)
            return null;

        try {
            if (cursor.moveToFirst()) {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String text = cursor.getString(cursor.getColumnIndex("text"));
                @SuppressLint("Range") String createdDateTime = cursor.getString(cursor.getColumnIndex("created_datetime"));
                @SuppressLint("Range") String modifiedDateTime = cursor.getString(cursor.getColumnIndex("modified_datetime"));

                note = new Note(
                        UUID.fromString(id),
                        text,
                        OffsetDateTime.parse(createdDateTime),
                        OffsetDateTime.parse(modifiedDateTime)
                );
            }
        } finally {
            cursor.close();
        }

        return note;
    }

    @Override
    public List<Note> SearchNotes(String text) {
        List<Note> notes = new ArrayList<>();

        String[] columns = {
                "id",
                "text",
                "created_datetime",
                "modified_datetime"};

        String selection = "text LIKE ?";
        String[] selectionArgs = {"%" + text + "%"};

        Cursor cursor = this._database.query(
                "notes",
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor == null)
            return null;

        try {
            if (cursor.moveToFirst()) {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String noteText = cursor.getString(cursor.getColumnIndex("text"));
                @SuppressLint("Range") String createdDateTime = cursor.getString(cursor.getColumnIndex("created_datetime"));
                @SuppressLint("Range") String modifiedDateTime = cursor.getString(cursor.getColumnIndex("modified_datetime"));

                Note note = new Note(
                        UUID.fromString(id),
                        noteText,
                        OffsetDateTime.parse(createdDateTime),
                        OffsetDateTime.parse(modifiedDateTime)
                );

                notes.add(note);
            }
        } finally {
            cursor.close();
        }

        return notes;
    }

    @Override
    public Note EditNote(UUID noteId, String text) {
        Note updatedNote = null;

        ContentValues values = new ContentValues();
        values.put("text", text);
        values.put("modified_datetime", OffsetDateTime.now().toString());

        String selection = "id = ?";
        String[] selectionArgs = {noteId.toString()};

        int rowsAffected = this._database.update("notes", values, selection, selectionArgs);
        if (rowsAffected > 0) {
            updatedNote = GetNote(noteId);
        }

        return updatedNote;
    }

    @Override
    public void DeleteNote(UUID noteId) {
        String selection = "id = ?";
        String[] selectionArgs = {noteId.toString()};

        this._database.delete("notes", selection, selectionArgs);
    }
}