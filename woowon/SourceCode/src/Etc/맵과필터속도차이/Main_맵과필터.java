package Etc.맵과필터속도차이;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main_맵과필터 {
    public static void main(String[] args) {

        Stream<Integer> stream1 = Stream.iterate(0, n -> n + 1).limit(1000);
        System.out.println(" ");
        System.out.println("결과가 같은 경우에서의 속도 차이는?");
        long start1 = System.nanoTime();
        System.out.println(
                stream1
                        .map(i -> i * 5)
                        .filter(i -> i % 2 == 0)
                        .collect(toList())
        );
        long end1 = System.nanoTime();
        System.out.println("1. 실행 시간 : " + (end1 - start1) / 1000000000.0 + "초");

        Stream<Integer> stream2 = Stream.iterate(0, n -> n + 1).limit(1000);
        long start2 = System.nanoTime();
        System.out.println(
                stream2
                        .filter(i -> i % 2 == 0)
                        .map(i -> i * 5)
                        .collect(toList())
        );
        long end2 = System.nanoTime();
        System.out.println("2. 실행 시간 : " + (end2 - start2) / 1000000000.0 + "초");
    }
}
