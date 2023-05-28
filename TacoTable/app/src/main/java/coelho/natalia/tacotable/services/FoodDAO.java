package coelho.natalia.tacotable.services;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
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

        if(cursor == null) {
            return foodList;
        }

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
        ArrayList<Food> foodList = new ArrayList();

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables("TACO_4___EDICAO");
        String[] columns = {"ID", "CATEROGIA", "ALIMENTO"};
        String selection = "ALIMENTO LIKE ?";
        String[] selectionArgs = {"%" + foodName + "%"};

        Cursor cursor = queryBuilder.query(_foodDatabase, columns, selection, selectionArgs, null, null, null);

        if(cursor == null || !cursor.moveToFirst()) {
            return foodList;
        }

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
}