package coelho.natalia.tacotable.services;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TacoTableSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "taco-table6.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SCRIPTS_FOLDER = "taco-table-scripts";

    private Context context;

    public TacoTableSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        AssetManager assetManager = context.getAssets();

        try {
            String[] scriptFiles = assetManager.list(SCRIPTS_FOLDER);

            if (scriptFiles == null)
                return;

            for (String scriptFile : scriptFiles) {
                InputStream inputStream = assetManager.open(SCRIPTS_FOLDER + "/" + scriptFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                db.execSQL(stringBuilder.toString());

                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
