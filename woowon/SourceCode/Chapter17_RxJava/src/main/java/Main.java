import io.reactivex.Observable;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        Observable<TempInfo> observable = getTemperature("New York");
//        Observable<TempInfo> observable = getCelsiusTemperature("New York");
        Observable<TempInfo> observable = getCelsiusTemperatures("New York", "Chicago", "San Francisco");

        // 결과를 확인하고 프로그램을 종료시키기 위해
        // observable.subscribe() 사용하면 아무것도 출력이 안됨
        // observable이 데몬 스레드에서 실행되기 때문에 main 프로그램은 실행할 코드가 없어 바로 종료되고 데몬 스레드도 함께 종료된다.
        observable.blockingSubscribe(new TempObserver());
    }

    public static Observable<TempInfo> getTemperature(String town) {
        return Observable.create(emitter ->
                    Observable.interval(1, TimeUnit.SECONDS)
                        .subscribe(i -> {
                            if (!emitter.isDisposed()) {  // 소비된 Observer가 아직 폐기 되지 않았으면 어떤 작업을 수행
                                if (i >= 5) {
                                    emitter.onComplete(); //5개가 넘으면 complete
                                } else {
                                    try {
                                        emitter.onNext(TempInfo.fetch(town)); // 아니면 온도를 Observer에 보고
                                    } catch (Exception e) {
                                        emitter.onError(e); // 에러가 발생하면 Observer에 알림
                                    }
                                }
                            }
                        }));
    }

    // 예제 17-15 화씨를 섭씨로 변환하는 메서드
    public static Observable<TempInfo> getCelsiusTemperature (String town){
        return getTemperature(town).map(temp -> new TempInfo(temp.getTown(), (temp.getTemp() - 32) * 5 / 9));
    }

    // 퀴즈 17-2 영하 온도만 거르기
    public static Observable<TempInfo> getNagativeTemperature (String town){
        return getTemperature(town).filter(temp -> temp.getTemp() < 0);
    }

    // 예제 17-16 한 개 이상 도시의 온도 보고를 합친다.
    public static Observable<TempInfo> getCelsiusTemperatures(String... towns){
        return Observable.merge(Arrays.stream(towns)
                                        .map(Main::getCelsiusTemperature)
                                        .collect(Collectors.toList())
        );
    }
}
