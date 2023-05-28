package coelho.natalia.tacotable;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import coelho.natalia.tacotable.controllers.FoodController;
import coelho.natalia.tacotable.models.Food;
import coelho.natalia.tacotable.services.FoodDAO;
import coelho.natalia.tacotable.services.TacoTableSQLiteHelper;
import coelho.natalia.tacotable.views.FoodArrayAdapter;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase _database;
    private ListView _foodListView;
    private FoodArrayAdapter _foodArrayAdapter;
    private EditText _searchEditText;
    private Button _searchButton;
    private FoodController _foodController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TacoTableSQLiteHelper dbHelper = new TacoTableSQLiteHelper(this);
        _foodArrayAdapter = new FoodArrayAdapter(this, new ArrayList<Food>());
        this._database = dbHelper.getWritableDatabase();

        _foodListView = findViewById(R.id.listView);
        _foodListView.setAdapter(_foodArrayAdapter);

        _searchEditText = findViewById(R.id.searchEditText);
        _searchButton = findViewById(R.id.searchButton);

        FoodDAO foodDao = new FoodDAO(this._database);
        this._foodController = new FoodController(foodDao);

        _searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFood();
            }
        });

        loadAllFoods();
    }

    private void loadAllFoods() {
        _foodArrayAdapter.addAll(this._foodController.GetFoods());
    }

    private void searchFood() {
        String searchQuery = this._searchEditText.getText().toString().trim();

        if(TextUtils.isEmpty((searchQuery))) {
            loadAllFoods();
            return;
        }

        _foodArrayAdapter.clear();
        _foodArrayAdapter.addAll(this._foodController.GetFoods(searchQuery));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this._database.close();
    }
}