package coelho.natalia.tacotable.controllers;

import java.util.ArrayList;

import coelho.natalia.tacotable.models.Food;
import coelho.natalia.tacotable.services.FoodDAO;

public class FoodController {
    private FoodDAO _foodDao;

    public FoodController(FoodDAO foodDAO) {
        this._foodDao = foodDAO;
    }

    public ArrayList<Food> GetFoods() {
        return _foodDao.GetFoods();
    }

    public ArrayList<Food> GetFoods(String foodName) {
        return _foodDao.GetFoods(foodName);
    }
}
