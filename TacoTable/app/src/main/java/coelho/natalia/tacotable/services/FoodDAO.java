package coelho.natalia.tacotable.services;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import coelho.natalia.tacotable.models.Food;

public class FoodDAO {
    private TacoTableSQLiteHelper _databaseHelper;
    private SQLiteDatabase _foodDatabase;
    private static final String ID_COLUMN = "id";
    private static final String CATEGORY_COLUMN = "Caterogia";
    private static final String NAME_COLUMN = "Alimento";

    public FoodDAO(SQLiteDatabase foodDatabase) {
        this._foodDatabase = foodDatabase;
    }

    public ArrayList<Food> GetFoods() {
        ArrayList<Food> foodList = new ArrayList();

        Cursor cursor = this._foodDatabase.rawQuery("SELECT id, Caterogia, Alimento FROM taco_4___edicao", null);
        cursor.moveToFirst();

        try {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(ID_COLUMN));
                @SuppressLint("Range") String category = cursor.getString(cursor.getColumnIndex(CATEGORY_COLUMN));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(NAME_COLUMN));
                
                foodList.add(new Food(id, category, name));
            } while (cursor.moveToNext());
        } finally {
            cursor.close();
        }

        return foodList;
    }

    public ArrayList<Food> GetFoods(String foodName) {
        ArrayList foodList = new ArrayList();

        // select no banco where name like foodName

        // converter resultados para arraylist de foods

        return foodList;
    }
}