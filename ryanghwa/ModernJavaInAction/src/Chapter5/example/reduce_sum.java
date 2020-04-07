package Chapter5.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class reduce_sum {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1,2,3,4,5);

        //for each 루프
        int sum = 0;
        for(int x : numbers){
            sum += x;
        }
        System.out.println(sum);


        //reduce
        sum = numbers.stream().reduce(0,(a,b)-> a+b);
        System.out.println(sum);

        //숫자 범위
        IntStream eventNumbers = IntStream.rangeClosed(1,100)
            .filter(n ->n%2 ==0);

        System.out.println(eventNumbers.count());

    }
}
