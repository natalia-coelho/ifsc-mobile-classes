package com.example.crudnotas.view;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.crudnotas.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private EditText editTextId, editTextName, editTextNote;
    private Button insertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = this.openOrCreateDatabase("mainDatabase", getBaseContext().MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS Student" +
                        "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, note TEXT NOT NULL)");

        editTextId = findViewById(R.id.editTextName);
        editTextName = findViewById(R.id.editTextName);
        editTextNote = findViewById(R.id.editTextNote);

        findViewById(R.id.insertButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertNote();
                showStudent();
            }
        });

        insertNote();
    }

    public void insertNote(){
        ContentValues values = new ContentValues();
        values.put("name", editTextId.getText().toString());
        values.put("note", editTextId.getText().toString());

        this.database.insert("Student", null, values);
    }

    public void showStudent(){
        Cursor cursor = database.rawQuery("select id, name, note from Student", null);
        cursor.moveToFirst();
        ArrayList<String> itens = new ArrayList<>();
        do{
            @SuppressLint("Range") String s = cursor.getString(cursor.getColumnIndex("name"));
            itens.add(s);
        } while (cursor.moveToNext());
        ListView list;
        list = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens);

            list.setAdapter(adapter);
    }

    public void createNote(View v){
        Intent intent = new Intent(this, ActivityShowNotes.class);
        intent.putExtra("id_nota", 0);
        startActivity(intent);
    }
}