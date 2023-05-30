package coelho.natalia.notesapp.services;

import java.util.List;
import java.util.UUID;

import coelho.natalia.notesapp.models.Note;

public interface INoteService {
    public Note CreateNote(String text);
    public List<Note> ListNotes();
    public Note GetNote(UUID noteId);
    public Note EditNote (UUID noteId, String text);
    public void DeleteNote(UUID noteId);
}
