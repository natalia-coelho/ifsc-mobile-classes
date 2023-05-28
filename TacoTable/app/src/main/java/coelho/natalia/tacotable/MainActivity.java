package coelho.natalia.tacotable;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import coelho.natalia.tacotable.controllers.FoodController;
import coelho.natalia.tacotable.models.Food;
import coelho.natalia.tacotable.services.FoodDAO;
import coelho.natalia.tacotable.services.TacoTableSQLiteHelper;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenha uma instância do DatabaseHelper
        TacoTableSQLiteHelper dbHelper = new TacoTableSQLiteHelper(this);

        // Obtenha uma conexão de banco de dados gravável
        database = dbHelper.getWritableDatabase();

        FoodDAO foodDao = new FoodDAO(database);
        FoodController foodController = new FoodController(foodDao);

        ArrayList<Food> foodList = foodController.GetFoods();

        for (Food food : foodList) {
            Log.i("MainActivity", food.getFoodName() + food.getCategory() + food.getId());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Feche a conexão com o banco de dados quando não for mais necessária
        database.close();
    }
}