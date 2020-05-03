package Chapter15;

import java.util.ArrayList;
import java.util.List;

// 값을 표현하는 셀 구현
// 셀은 publisher 이며 동시에 subscriber이다.
public class SimpleCell implements Publisher<Integer>, Subscriber<Integer>{
    private int value = 0;
    private String name;
    private List<Subscriber> subscribers = new ArrayList<>();

    public SimpleCell(String name){
        this.name = name;
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscribers.add(subscriber);
    }

    private void notifyAllSubscriber() {
        subscribers.forEach(subscriber -> subscriber.onNext(this.value));
    }

    @Override
    public void onNext(Integer newValue) {
        this.value = newValue;
        System.out.println(this.name + ":" + this.value);
        notifyAllSubscriber(); // 값이 갱신되었음을 알린다.
    }
}
