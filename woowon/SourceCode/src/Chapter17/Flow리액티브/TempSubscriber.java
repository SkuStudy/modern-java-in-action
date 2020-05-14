package Chapter17.Flow리액티브;

import java.util.concurrent.Flow;

public class TempSubscriber implements Flow.Subscriber<TempInfo> {
    private Flow.Subscription subscription;

    // 구독을 저장하고 첫 번째 요청을 전달
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    // 수신한 온도를 출력하고 다음 정보를 요청
    @Override
    public void onNext(TempInfo item) {
        System.out.println(item);
        subscription.request(1);
    }

    // 에러가 발생하면 에러 메시지 출력
    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Done !");
    }
}
