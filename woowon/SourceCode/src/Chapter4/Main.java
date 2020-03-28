package Chapter4;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER)
        );

        // 칼로리 300 넘는 3가지 음식 이름 출력
        List<String> threeHighCaloricDishNames =
                menu.stream()
                    .filter(dish -> dish.getCalories() > 300)
                    .map(Dish::getName)
                    .limit(3)
                    .collect(toList());

        System.out.println(threeHighCaloricDishNames);
    }
}
