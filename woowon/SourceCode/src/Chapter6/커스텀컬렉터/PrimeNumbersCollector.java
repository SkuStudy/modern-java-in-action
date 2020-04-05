package Chapter6.커스텀컬렉터;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

public class PrimeNumbersCollector implements Collector<Integer,
        Map<Boolean, List<Integer>>,         // 누적자 형식
        Map<Boolean, List<Integer>>> {       // 수집 연산의 결과 형식

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {{
            put(true, new ArrayList<Integer>());
            put(false, new ArrayList<Integer>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get(Main.isPrime(acc.get(true), candidate))             // isPrime 결과에 따라 소수 리스트(true)와 비소수 리스트(false)를 생성
               .add(candidate);                                         // candidate를 알맞은 리스트에 추가
        };
    }

    // 소수, 비소수 구하기에는 알고리즘 자체가 순차적이어서 컬렉터를 실제 병렬로 사용 할 순 없다
    // 구현 안해도 되지만 책에는 학습 목적을 위해 구현
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return null;
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    // 발견한 소수에 의미가 있으므로 UNORDERED X, CONCURRENT X
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
    }
}



