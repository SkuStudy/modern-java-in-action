package Chapter5.example;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class generate_ex {
    public static void main(String[] args) {
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {

                int oldPreviou= this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPreviou;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }

}