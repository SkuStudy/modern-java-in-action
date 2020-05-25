package Chapter19;

public interface MyList<T> {
    T head();
    MyList<T> tail();
    default boolean isEmpty(){
        return true;
    }
}
