package com.example.crudnotas.view;

import android.os.Bundle;
import com.example.crudnotas.R;
import com.example.crudnotas.controller.NoteController;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ActivityShowNotes extends AppCompatActivity {
    NoteController controller;
    EditText edTitle, edText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity_show_notes);
        controller = new NoteController(getApplicationContext());
        edTitle = findViewById(R.id.edTitle);
        edText = findViewById(R.id.edText);

    }

    public void saveNote(View v){
        controller.registerNote(v.);
    }
}