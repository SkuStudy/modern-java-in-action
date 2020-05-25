package Chapter19;

public class MyLinkdeList<T> implements MyList<T> {
    private final T head;
    private final MyList<T> tail;
    public MyLinkdeList(T head, MyList<T> tail){
        this.head = head;
        this.tail = tail;
    }
    public T head(){
        return head;
    }
    public MyList<T>tail(){
        return tail;
    }
    public boolean isEmpty(){
        return false;
    }
}
