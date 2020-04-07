package Chapter6.example;

import Chapter3.example.function패키지.LambdasPredicate;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

public class CollectorHarness {
    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;
        for ( int i = 0; i<10; i++){
            long start = System.nanoTime();
            partitionPrimes(1_100_000);
            long duration = (System.nanoTime()-start) / 1_000_000;
            if(duration<fastest) fastest = duration;

        }
        System.out.println("Fastest execution done in "+ fastest + "msecs");
    }
    public static Map<Boolean, List<Integer>> partitionPrimes(int n){
        return IntStream.rangeClosed(2,n).boxed().collect(partitioningBy(candidate-> isPrime(candidate)));
    }

    private static boolean isPrime(Integer candidate) {
        int candidateRoot = (int)Math.sqrt((double)candidate);
        return IntStream.rangeClosed(2,candidate)
                .noneMatch(i -> candidate %i==0);
    }

    public static boolean isPrime(List<Integer> primes, int candidate){
        int candidateRoot = (int)Math.sqrt((double)candidate);
        return takeWhile(primes, i-> i<=candidateRoot)
                .stream()
                .noneMatch(i -> candidate %i==0);
    }
    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p){
        int i =0;
        for(A item:list){
            if(!p.test(item)){
                return list.subList(0,i);
            }
            i++;
        }
        return list;
    }
}
