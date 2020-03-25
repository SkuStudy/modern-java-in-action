package Chapter3.example.function패키지;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdasPredicate {
    public static void main(String[] args) {
        List<String> listOfStrings = Arrays.asList("1","2","","3");
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);

        for(String s:nonEmpty){
            System.out.println(s);
        }
    }

    @FunctionalInterface
    public interface Predicate<T>{
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> results = new ArrayList<>();
        for(T t: list){
            if(p.test(t)){
                results.add(t);
            }
        }
        return results;
    }


}
