package Chapter9.옵저버.News;

import Chapter9.옵저버.Interface.Observer;

public class NYTimes implements Observer {
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("money")) {
            System.out.println(this.getClass().getName());
            System.out.println("Breaking news in NY! " + tweet);
        }
    }
}
