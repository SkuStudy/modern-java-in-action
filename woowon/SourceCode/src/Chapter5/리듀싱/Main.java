package Chapter5.리듀싱;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(4, 5, 3, 9);

        // 리듀싱 - 요소의 합
        int sum = numbers.stream().reduce(0, (a, b) -> a + b); // 21
//        int sum = numbers.stream().reduce(0, Integer::sum); // 21
        System.out.println("요소의 합: "+sum);

        // 최대값과 최솟값
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println("최대값 구하기: " + max.get());

        // 스트림 개수 구하기
        Optional<Integer> count = numbers.stream().map(x->1).reduce(Integer::sum);
//        int count = numbers.stream().count();
        System.out.println("스트림 개수 구하기: "+count.get());
    }
}
