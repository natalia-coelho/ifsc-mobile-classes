package coelho.natalia.notesapp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import coelho.natalia.notesapp.models.Note;
import coelho.natalia.notesapp.services.INoteService;

public class NotesController {

    private INoteService _noteService;

    public NotesController(INoteService noteService) {
        this._noteService = noteService;
    }

    public Note CreateNote(String text) {
        return _noteService.CreateNote(text);
    }

    public Note GetNote(UUID noteId) {
        return _noteService.GetNote(noteId);
    }

    public List<Note> ListNotes() {
        return _noteService.ListNotes();
    }

    public Note EditNote(UUID noteId, String text) {
        return _noteService.EditNote(noteId, text);
    }

    public void DeleteNote(UUID noteId) {
        _noteService.DeleteNote(noteId);
    }
}
