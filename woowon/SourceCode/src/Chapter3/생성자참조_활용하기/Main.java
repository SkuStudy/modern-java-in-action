package Chapter3.생성자참조_활용하기;

import Chapter3.Apple.Apple;
import Chapter3.Apple.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {


        Supplier<Apple> c1 = Apple::new; // 메서드 참조
        Apple a1 = c1.get(); // supplier의 get 메서드로 새로운 Chapter3.Apple 객체 생성
        System.out.println("Supplier 메서드 참조");
        System.out.println(a1.toString());

        Function<Integer, Apple> c2 = Apple::new; // 메서드 참조
        Apple a2 = c2.apply(110); // function의 apply 메서드에 weight 인수를 받아 새로운 Chapter3.Apple 객체 생성
        System.out.println("Function 메서드 참조");
        System.out.println(a2.toString());

        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);
        System.out.println("리스트의 무게를 가지고 모두 사과로 만들기");
        for (Apple apple : apples) {
            System.out.println(apple.toString());
        }

        // BiFunction<Integer, Color, Chapter3.Apple> c3 = (color, weight) -> new Chapter3.Apple(color, weight);
        BiFunction<Integer, Color, Apple> c3 = Apple::new;
        Apple a3 = c3.apply(110, Color.GREEN);
        System.out.println("BiFunction 메서드 참조");
        System.out.println(a3.toString());
    }

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer i : list) {
            result.add(f.apply(i));
        }
        return result;
    }
}
