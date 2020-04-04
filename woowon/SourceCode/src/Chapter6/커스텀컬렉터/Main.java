package Chapter6.커스텀컬렉터;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Map<Boolean, List<Integer>> result = new HashMap<>();
        result = partitionPrimesWithCustomCollector(10);
        System.out.println(result);
    }

    // n까지 수를 소수, 비소수로 나누는 메서드
    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n){
        return IntStream.rangeClosed(2, n)
                        .boxed()
                        .collect(new PrimeNumbersCollector());
    }

    // 소수로 나누어 떨어지는지 확인해서 대상의 범위를 더 좁힐 수 있는 방법
    public static boolean isPrime(List<Integer> primes, int candidate){
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i<= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);  //candidate가 소수에 의해 전부 나누어 떨어지지 않으면 true(소수), 하나라도 나눠지면 false (비소수)
    }

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p){
        int i = 0;
        for (A item : list){
            if(!p.test(item)){
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }
}
