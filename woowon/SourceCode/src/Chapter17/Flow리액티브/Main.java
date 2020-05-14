package Chapter17.Flow리액티브;

import java.util.concurrent.Flow;

public class Main {
    public static void main(String[] args) {
//        getTemperatures("New York").subscribe(new TempSubscriber());
        getCesiusTemperatures("New York").subscribe(new TempSubscriber());
    }

    public static Flow.Publisher<TempInfo> getCesiusTemperatures(String town){
        // 람다식이 Publisher로 변환됨
        return subscriber -> {
            TempProcessor processor = new TempProcessor();
            processor.subscribe(subscriber);
            processor.onSubscribe(new TempSubscription(processor, town));
        };
    }

//    프로세스 사용 안 했을 때
//    public static Flow.Publisher<TempInfo> getTemperatures (String town){
//        return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
//    }
}
