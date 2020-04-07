package Chapter4.example;

import Chapter4.example.Dish.Dish;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class main {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 400, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        List<String> threeHighCaloricDishNames = menu.stream().filter(dish -> dish.getCalories() > 300)//중간연산
                .map(Dish::getName)//중간연산
                .limit(3) //중간연산
                .collect(toList()); //최종연산
        System.out.println(threeHighCaloricDishNames);


        /*
        List<Dish> lowCaloricDishes = new ArrayList<>();

        for(Dish dish: menu){
            if(dish.getCalories()<400){
                lowCaloricDishes.add(dish);
            }
        }

        Collections.sort(lowCaloricDishes, new Comparator<Dish>(){
            public int compare(Dish dish1, Dish dish2){
                return Integer.compare(dish1.getCalories(), dish2.getCalories());
            }
        });

        List<String> lowCaloricDishesName = new ArrayList<>();
        for(Dish dish: lowCaloricDishes){
            lowCaloricDishesName.add(dish.getName());
        }

        for(String dish:lowCaloricDishesName){
            System.out.println(dish);
        }*/
    }

}
