package Chapter3.example.생성자참조;

import Chapter3.example.Apple.Apple;
import Chapter3.example.Apple.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;


public class main {
    public static void main(String[] args) {
        //디폴트 생성자 Apple 생성
        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();

        Supplier<Apple> c2 = () -> new Apple();
        Apple a2 = c1.get();

        //weight를 시그니쳐로 갖는 생성자
        Function<Integer, Apple> c3 = Apple::new;
        Apple a3 = c3.apply(110);

        Function<Integer, Apple> c4 = (weight) -> new Apple(weight);
        Apple a4 = c4.apply(110);

        List<Integer> weights = Arrays.asList(7,3,4,10);
        List<Apple> apples = map(weights, Apple::new);

        BiFunction<Integer, Color, Apple> c5 = Apple::new;
        Apple a5 = c5.apply(110, Color.GREEN);
    }

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f){
        List<Apple> result = new ArrayList<>();
        for(Integer i : list){
            result.add(f.apply(i));
        }
        return result;
    }


}
