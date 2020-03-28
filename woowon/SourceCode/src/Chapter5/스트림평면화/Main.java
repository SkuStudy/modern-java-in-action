package Chapter5.스트림평면화;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        // 두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트를 반환
        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);
        List<int[]> pairs = number1.stream()
                                   .flatMap(i -> number2.stream()
                                                    .map(j -> new int[]{i, j})
                                   )
                                   .collect(toList());

        // 2차원 배열 stream 열고 출력하는 법
        pairs.stream()
                .map(Arrays::toString)
                .forEach(pair -> System.out.print(pair+", "));

        /*
        // 오답 - 반환 값이 List<Stream<int[]>>임
        List<int[]> pairs = number1.stream()
                .map(i -> number2.stream()
                        .map(j -> new int[]{i, j})
                )
                .collect(toList());
         */
    }
}
