package coelho.natalia.notesapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notes_database.db";
    private static final int DATABASE_VERSION = 1;

    public NotesDatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableQuery = "CREATE TABLE notes (" +
                "id TEXT PRIMARY KEY," +
                "text TEXT," +
                "created_datetime TEXT," +
                "modified_datetime TEXT" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}