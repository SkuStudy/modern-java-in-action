package Chapter2.익명클래스예제;

import Chapter2.익명클래스예제.Apple.Apple;
import Chapter2.익명클래스예제.PreDicate.AppleGreenColorPredicate;
import Chapter2.익명클래스예제.PreDicate.AppleWeightPredicate;

import java.util.Arrays;
import java.util.List;

import static Chapter2.익명클래스예제.Apple.Color.GREEN;
import static Chapter2.익명클래스예제.Apple.Color.RED;

public class Main {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,GREEN),new Apple(155, GREEN), new Apple(120,RED));

        List<Apple> heavyApples = filterApples.filterApples(inventory, new AppleWeightPredicate());
        for(Apple apple: heavyApples){
            System.out.println(apple.getWeight());
        }
        List<Apple> greenApples = filterApples.filterApples(inventory, new AppleGreenColorPredicate());
        for(Apple apple: greenApples){
            System.out.println(apple.getColor());
        }
    }
}
