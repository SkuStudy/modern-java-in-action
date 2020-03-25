package Chapter3.example.function패키지;

import java.util.Arrays;
import java.util.List;

public class LambdasConsumer {
    public static void main(String[] args) {
        forEach(Arrays.asList(1,2,3,4,5), (Integer i) -> System.out.println(i));
    }

    @FunctionalInterface
    public interface Consumer<T>{
        void accept(T t);
    }

    public static <T> void forEach(List<T> list, Consumer<T> c){
        for(T t:list){
            c.accept(t);
        }
    }
}
