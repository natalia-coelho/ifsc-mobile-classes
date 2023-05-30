package coelho.natalia.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import coelho.natalia.notesapp.controllers.NotesController;
import coelho.natalia.notesapp.models.Note;
import coelho.natalia.notesapp.services.InMemoryNoteService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InMemoryNoteService noteService = new InMemoryNoteService();
        NotesController notesController = new NotesController(noteService);

        List<Note> notes = notesController.ListNotes();

        Log.d("MainActivity", "Listing all notes...");

        for (Note note : notes) {
            Log.i("MainActivity", note.toString());
        }

        Note bananaNote = notesController.CreateNote("banana");

        Log.d("MainActivity", "Created note!");

        notes = notesController.ListNotes();

        Log.d("MainActivity", "Listing all notes...");

        for (Note note : notes) {
            Log.i("MainActivity", note.toString());
        }

        notesController.EditNote(bananaNote.getId(), "banana editada");

        Log.d("MainActivity", "Note edited!");

        notes = notesController.ListNotes();

        Log.d("MainActivity", "Listing all notes...");

        for (Note note : notes) {
            Log.i("MainActivity", note.toString());
        }

        Log.d("MainActivity", "Deleting note...");

        notesController.DeleteNote(bananaNote.getId());

        notes = notesController.ListNotes();

        Log.d("MainActivity", "Listing all notes...");

        for (Note note : notes) {
            Log.i("MainActivity", note.toString());
        }
    }
}