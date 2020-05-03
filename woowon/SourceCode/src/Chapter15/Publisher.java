package Chapter15;

// c1이나 c2 값이 변했을 때 c3가 두 값을 더하도록 지정하도록 publisher 구현
public interface Publisher<T> {
    void subscribe(Subscriber<? super T> subscriber);
}
