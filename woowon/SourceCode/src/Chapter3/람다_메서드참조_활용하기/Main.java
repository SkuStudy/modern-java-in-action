package Chapter3.람다_메서드참조_활용하기;

import Chapter3.Apple.Apple;
import Chapter3.Apple.Color;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class Main {
    public static void main(String[] args) {
        List<Apple> appleList = Arrays.asList(
                new Apple(50, Color.GREEN),
                new Apple(10, Color.BLUE),
                new Apple(200, Color.RED),
                new Apple(100, Color.BLUE)
        );

        // 1단계: 클래스 Comparator 오름차순
        appleList.sort(new AscendingApple());
        printApple(appleList, "클래스 Comparator 오름차순");

        // 2단계: 익명 클래스 내림차순
        appleList.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o2.getWeight() - o1.getWeight();
            }
        });
        printApple(appleList, "익명 클래스 내림차순");

        // 3단계: 람다 표현식 사용 - 자바 컴파일러가 형식을 추론하지 않는 case
        appleList.sort((Apple o1, Apple o2) -> o1.getWeight() - o2.getWeight());
        printApple(appleList, "람다 - 자바 컴파일러가 형식을 추론하지 않는 case");

        // 3단계: 람다 표현식 사용 - 자바 컴파일러가 형식을 추론하는 case
        appleList.sort((o1, o2) -> o1.getWeight() - o2.getWeight());
        printApple(appleList, "람다 - 자바 컴파일러가 형식을 추론하는 case");

        // 람다 메서드 접근 - 기본 오름차순
        appleList.sort(Comparator.comparing(o -> o.getWeight()));
        printApple(appleList, "Comparator 메서드 접근");
        // 또는 import static java.util.Comparator.comparing; 추가 후
        appleList.sort(comparing(o -> o.getWeight()));

        // 4단계: 메서드 참조 - 기본 오름차순
        appleList.sort(comparing(Apple::getWeight));
        printApple(appleList, "메서드 참조 - 기본 오름차순");

        // 메서드 참조 - 내림 차순
        appleList.sort(comparing(Apple::getWeight).reversed());
        printApple(appleList, "메서드 참조 - 내림 차순");

    }

    static class AscendingApple implements Comparator<Apple> {
        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight() - o2.getWeight();
        }
    }

    public static void printApple(List<Apple> appleList, String msg) {
        System.out.println(msg);
        for (Apple apple : appleList) {
            System.out.println(apple.toString());
        }
    }
}
