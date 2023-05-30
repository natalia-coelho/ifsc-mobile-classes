package coelho.natalia.notesapp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import coelho.natalia.notesapp.models.Note;

public class InMemoryNoteService implements INoteService{
    private HashMap<UUID, Note> _noteStorage;

    public InMemoryNoteService() {
        this._noteStorage = new HashMap<UUID, Note>();
    }

    @Override
    public Note CreateNote(String text) {
        Note note = new Note(text);
        _noteStorage.put(note.getId(), note);

        return note;
    }

    @Override
    public List<Note> ListNotes() {
        List<Note> notes = new ArrayList<>(_noteStorage.values());
        return notes;
    }

    @Override
    public Note GetNote(UUID noteId) {
        Note note = _noteStorage.get(noteId);

        return note;
    }

    @Override
    public List<Note> SearchNotes(String text) {
        List<Note> rawNotes = new ArrayList<Note>(_noteStorage.values());
        List<Note> filteredNotes = new ArrayList<Note>();

        for (Note note : rawNotes) {
            if(note.getText().contains(text))
                filteredNotes.add(note);
        }

        return filteredNotes;
    }

    @Override
    public Note EditNote(UUID noteId, String text) {
        Note note = _noteStorage.get(noteId);

        if (note == null)
            return null;

        note.EditNote(text);
        _noteStorage.put(noteId, note);

        return note;
    }


    @Override
    public void DeleteNote(UUID noteId) {
        _noteStorage.remove(noteId);
    }
}
