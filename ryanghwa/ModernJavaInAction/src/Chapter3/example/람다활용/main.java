package Chapter3.example.람다활용;

import Chapter2.익명클래스예제.Apple.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static Chapter2.익명클래스예제.Apple.Color.GREEN;
import static Chapter2.익명클래스예제.Apple.Color.RED;
import static java.util.Comparator.comparing;

public class main {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,GREEN),new Apple(155, GREEN), new Apple(120,RED));

        // 코드 전달
        //inventory.sort(new AppleComparator());

        //익명 클래스사용
//        inventory.sort(new Comparator<Apple>() {
//            @Override
//            public int compare(Apple a1, Apple a2) {
//                return a1.getWeight() - a2.getWeight();
//            }
//        });

        //람다표현식 사용
        //1
//        inventory.sort((Apple a1, Apple a2)->a1.getWeight()-a2.getWeight());
        //2
//        inventory.sort((a1,a2)->a1.getWeight()-a2.getWeight());
        //3
//        inventory.sort(comparing(apple->apple.getWeight()));
        //4
//        inventory.sort(comparing(apple -> apple.getWeight()));

        //메서드 참조
//        inventory.sort(comparing(Apple::getWeight));

        //내림차순 정렬
        inventory.sort(comparing(Apple::getWeight).reversed());

        for(Apple apple : inventory){
            System.out.println(apple.getWeight());
        }

    }


    static class AppleComparator implements Comparator<Apple> {

        @Override
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight() - a2.getWeight();
        }

    }
}
