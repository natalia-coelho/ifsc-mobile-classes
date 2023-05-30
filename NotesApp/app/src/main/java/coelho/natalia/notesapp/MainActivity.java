package coelho.natalia.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import coelho.natalia.notesapp.controllers.NotesController;
import coelho.natalia.notesapp.models.Note;
import coelho.natalia.notesapp.services.InMemoryNoteService;
import coelho.natalia.notesapp.services.SQLiteNoteService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotesDatabaseOpenHelper notesDbHelper = new NotesDatabaseOpenHelper(this);
        SQLiteDatabase notesDatabase = notesDbHelper.getWritableDatabase();

//        InMemoryNoteService noteService = new InMemoryNoteService();
        SQLiteNoteService noteService = new SQLiteNoteService(notesDatabase);
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

        notesController.CreateNote("maçã");

        notes = notesController.ListNotes();

        Log.d("MainActivity", "Listing all notes...");

        for (Note note : notes) {
            Log.i("MainActivity", note.toString());
        }

        notes = notesController.SearchNotes("banana");

        Log.d("MainActivity", "Searching for banana...");

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