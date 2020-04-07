package Chapter4.example.Dish;

public class Dish {
    private int Calories;
    private String Name;
    private Type type;
    private boolean vegetarian;

    public Dish(){

    }
    public Dish(String name, boolean vegetarian, int calories, Type type){
        this.Name = name;
        this.vegetarian = vegetarian;
        this.Calories = calories;
        this.type = type;
    }

    public Dish(int Calories, String Name) {
        this.Calories = Calories;
        this.Name = Name;
    }

    public int getCalories() {
        return Calories;
    }

    public void setCalories(int calories) {
        Calories = calories;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public enum Type {MEAT,OTHER,FISH}
}
