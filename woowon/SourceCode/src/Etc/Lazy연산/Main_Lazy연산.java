package Etc.Lazy연산;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Main_Lazy연산 {
    public static void main(String[] args) {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("filter 먼저");
        System.out.println(
                list.stream()
                        .filter(i -> {
                            System.out.println("검사 값: " + i + " 연산: i < 6");
                            return i < 6;
                        })
                        .filter(i -> {
                            System.out.println("검사 값: " + i + " 연산: i%2 == 0");
                            return i % 2 == 0;
                        })
                        .map(i -> {
                            System.out.println("검사 값: " + i + " 연산: i = i*10");
                            return i * 10;
                        })
                        .collect(toList())
        );

        System.out.println("map 먼저");
        System.out.println(
                list.stream()
                        .map(i -> {
                            System.out.println("검사 값: " + i + " 연산: i = i*10");
                            return i * 10;
                        })
                        .filter(i -> {
                            System.out.println("검사 값: " + i + " 연산: i < 6");
                            return i < 6;
                        })
                        .filter(i -> {
                            System.out.println("검사 값: " + i + " 연산: i%2 == 0");
                            return i % 2 == 0;
                        })
                        .collect(toList())
        );
    }
}
