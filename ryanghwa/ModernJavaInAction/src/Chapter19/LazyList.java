package Chapter19;

import java.util.function.Supplier;

public class LazyList<T> implements MyList<T> {
    final T head;
    final Supplier<MyList<T>> tail;
    public LazyList(T head, Supplier<MyList<T>> tail){
        this.head = head;
        this.tail = tail;
    }
    public T head(){
        return head;
    }
    public MyList<T> tail(){
        return tail.get();
    }
    public boolean isEmpty(){
        return false;
    }
    public static LazyList<Integer>from (int n){
        return  new LazyList<>(n,()->from(n+1));
    }
}
