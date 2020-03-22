package chapter1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Chap1_1_동작파라미터화 {

    public static void main(String... args) {
        List<Dish> lowCaloricDishes = init();

        //Java 7
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });

        //Java 8
        lowCaloricDishes.sort(Comparator.comparingInt(Dish::getCalories));
    }

    private static List<Dish> init() {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish d : Dish.menu) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }

        return lowCaloricDishes;
    }

}
