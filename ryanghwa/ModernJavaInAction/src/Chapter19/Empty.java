package Chapter19;

public class Empty<T> implements MyList<T> {
    public T head(){
        throw new UnsupportedOperationException();
    }
    public MyList<T> tail(){
        throw new UnsupportedOperationException();
    }
}
