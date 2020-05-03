package Chapter15;

// 통신할 구독자를 인수로 받는다.
// Subscriber는 onNext라는 정보를 전달할 단순 메서드를 포함하여 구현자가 마음대로 구현할 수 있다.
public interface Subscriber<T> {
    void onNext(T t);
}
