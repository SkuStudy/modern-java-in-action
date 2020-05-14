package Chapter17.Flow리액티브;

import java.util.concurrent.Flow;

// Subscriber에게 TempInfo 스트림을 전송하는 Subscription
public class TempSubscription implements Flow.Subscription {
    private final Flow.Subscriber<? super TempInfo> subscriber;
    private final String town;

    public TempSubscription(Flow.Subscriber<? super TempInfo> subscriber, String town) {
        this.subscriber = subscriber;
        this.town = town;
    }

    @Override
    public void request(long n) {
        for (long i = 0L; i < n; i++) {
            try{
                subscriber.onNext(TempInfo.fetch(town)); // 현재 온도를 Subscriber로 전달
            } catch (Exception e){
                subscriber.onError(e);
                break;
            }
        }
    }

    @Override
    public void cancel() {
        subscriber.onComplete(); // 구독이 취소되면 완료 신호를 Subscriber로 전달
    }
}
