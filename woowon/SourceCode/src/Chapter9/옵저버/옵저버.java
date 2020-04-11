package Chapter9.옵저버;

import Chapter9.옵저버.News.Guardian;
import Chapter9.옵저버.News.LeMonde;
import Chapter9.옵저버.News.NYTimes;

public class 옵저버 {
    public static void main(String[] args) {
        // NYTimes, Guardian, LeMonde -> 트윗에 포함된 다양한 키워드에 다른 동작을 수행할 수 있는 여러 옵저버를 정의
        // Feed -> 트윗을 받았을 때 알림을 보낼 옵저버 리스트를 유지한다.

        // 기존의 옵저버 패턴
        System.out.println("기존의 옵저버 패턴");
        Feed feed = new Feed();
        feed.registerObserver(new NYTimes());
        feed.registerObserver(new Guardian());
        feed.registerObserver(new LeMonde());
        feed.notifyObservers("The queen said her favorite book is Modern Java in Action !"); // 가디언이 queen 받고 있으니 Guardian이 동작

        // 람다를 사용한 옵저버 패턴
        System.out.println("람다를 사용한 옵저버 패턴");
        Feed feed2 = new Feed();
        feed2.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("NYTimes");
                System.out.println("Breaking news in NY! " + tweet);
            }
        });
        feed2.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Guardian");
                System.out.println("Yet more news from London... " + tweet);
            }
        });
        feed2.notifyObservers("money is good!");
    }
}
