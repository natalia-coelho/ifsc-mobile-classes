package coelho.natalia.notesapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;

import coelho.natalia.notesapp.R;
import coelho.natalia.notesapp.controllers.NotesController;
import coelho.natalia.notesapp.database.NotesDatabaseOpenHelper;
import coelho.natalia.notesapp.models.Note;
import coelho.natalia.notesapp.services.SQLiteNoteService;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mainLayout;
    private Button buttonAddNote;
    private Button buttonSearch;
    private EditText editTextSearch;
    private ListView listViewNotes;
    private NotesController notesController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainLayout);
        buttonAddNote = findViewById(R.id.buttonAddNote);
        buttonSearch = findViewById(R.id.buttonSearch);
        editTextSearch = findViewById(R.id.editTextSearch);
        listViewNotes = findViewById(R.id.listViewNotes);

        NotesDatabaseOpenHelper notesDbHelper = new NotesDatabaseOpenHelper(this);
        SQLiteDatabase notesDatabase = notesDbHelper.getWritableDatabase();

//        InMemoryNoteService noteService = new InMemoryNoteService();
        SQLiteNoteService noteService = new SQLiteNoteService(notesDatabase);
        this.notesController = new NotesController(noteService);

        notesController.CreateNote("Bananita");

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchNote();
            }
        });

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewNoteScreen();
            }
        });
    }

    private void searchNote() {
        String searchText = editTextSearch.getText().toString();
        List<Note> searchResults = notesController.SearchNotes(searchText);

        for (Note note : searchResults) {
            Log.i("MainActivity", note.toString());
        }
    }

    private void openNewNoteScreen() {
        View newNoteView = getLayoutInflater().inflate(R.layout.activity_new_note, mainLayout, false);
        mainLayout.removeAllViews();
        mainLayout.addView(newNoteView);

        setContentView(R.layout.activity_new_note);

        Button cancelButton = findViewById(R.id.button_cancel);
        Button saveButton = findViewById(R.id.button_save);
        EditText editTextNote = findViewById(R.id.edit_text_note);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Voltar para a tela anterior (MainActivity)
                mainLayout.removeAllViews();
                mainLayout.addView(buttonAddNote);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newNoteText = editTextNote.getText().toString();

                // Lógica para salvar a nova anotação
                // ...
                Log.i("MainActivity", newNoteText);

                // Voltar para a tela anterior (MainActivity)
                mainLayout.removeAllViews();
                mainLayout.addView(buttonAddNote);
            }
        });
    }
}