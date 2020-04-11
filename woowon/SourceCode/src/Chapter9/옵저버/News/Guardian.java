package Chapter9.옵저버.News;

import Chapter9.옵저버.Interface.Observer;

// 트윗에 포함된 다양한 키워드에 다른 동작을 수행할 수 있는 여러 옵저버를 정의
public class Guardian implements Observer {

    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("queen")) {
            System.out.println(this.getClass().getName());
            System.out.println("Yet more news from London... " + tweet);
        }
    }
}
