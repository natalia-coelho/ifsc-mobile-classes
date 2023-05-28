package coelho.natalia.tacotable.models;

public class Food {
    private int id;
    private String category;
    private String foodName;

    public Food (int id, String category, String name) {
        this.id = id;
        this.category = category;
        this.foodName = name;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getFoodName() {
        return foodName;
    }
}
