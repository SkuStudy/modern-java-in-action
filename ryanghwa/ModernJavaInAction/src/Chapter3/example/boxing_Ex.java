package Chapter3.example;

import java.util.function.Predicate;

public class boxing_Ex {
    public static void main(String[] args) {

        IntPredicate eventNumbers = (int i)-> i%2 ==0;
        System.out.println(eventNumbers.test(1000));

        //int -> Integer 박싱이 된다.
        Predicate<Integer> oddNumbers = (Integer i)-> i%2 !=0;
        System.out.println(oddNumbers.test(1000));
    }
    public interface IntPredicate{
        boolean test(int t);
    }
}
