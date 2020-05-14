package Chapter18;

import java.util.function.Function;
import java.util.function.Supplier;

public class 함수형프로그래밍 {
    public static void main(String[] args) {

        // 함수를 바로 파라미터로 넣어주는 방식
        int result1 = f4(() -> 2, () -> 1, (value) -> value * value);
        System.out.println(result1); // 9


        // 함수를 변수에 할당이 가능
        Supplier<Integer> f1 = () -> 2;
        Supplier<Integer> f2 = () -> 1;
        Function<Integer, Integer> f3 = (value) -> value * value;
        int result2 = f4(f1, f2, f3);
        System.out.println(result2); // 9

    }

    public static Integer f4(Supplier<Integer> f1, Supplier<Integer> f2, Function<Integer, Integer> f3){
        return f3.apply(f1.get() + f2.get());
    }

    // 함수를 return으로 반환 할 수 있다.
    public static Supplier<Integer> returnFunction(){
        return () -> 10;
    }
}
