package Etc.맵과필터속도차이;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main_맵과필터 {
    public static void main(String[] args) {

        Stream<Integer> stream1 = Stream.iterate(0, n -> n + 1).limit(1000);
        System.out.println("");
        System.out.println("스트림 결과가 같은 경우일 때 각 속도 차이는?");
        long start1 = System.nanoTime();
        List<Integer> list1 = stream1
                .map(i -> i * 5)
                .filter(i -> i % 2 == 0)
                .collect(toList());
        long end1 = System.nanoTime();
        System.out.println(list1);
        System.out.println("1. 실행 시간 : " + (end1 - start1) / 1000000000.0 + "초");

        Stream<Integer> stream2 = Stream.iterate(0, n -> n + 1).limit(1000);
        long start2 = System.nanoTime();
        List<Integer> list2 = stream2
                .filter(i -> i % 2 == 0)
                .map(i -> i * 5)
                .collect(toList());
        long end2 = System.nanoTime();
        System.out.println(list2);
        System.out.println("2. 실행 시간 : " + (end2 - start2) / 1000000000.0 + "초");
        System.out.println("두 결과는 같은 가? " + list1.equals(list2));

        System.out.println("\nlimit의 순서는 중요할까?");
        long start3 = System.nanoTime();
        List<Integer> list3 = Stream.iterate(0, n -> n + 1)
                .limit(1000)
                .filter(i -> i % 2 == 0)
                .map(i -> i * 5)
                .collect(toList());
        long end3 = System.nanoTime();
        System.out.println(list3);
        System.out.println("3. 실행 시간 : " + (end3 - start3) / 1000000000.0 + "초");

        long start4 = System.nanoTime();
        List<Integer> list4 = Stream.iterate(0, n -> n + 1)
                .filter(i -> i % 2 == 0)
                .map(i -> i * 5)
                .limit(1000)
                .collect(toList());
        long end4 = System.nanoTime();
        System.out.println(list4);
        System.out.println("4. 실행 시간 : " + (end4 - start4) / 1000000000.0 + "초");
        System.out.println("두 결과는 같은 가? " + list3.equals(list4));
    }
}
