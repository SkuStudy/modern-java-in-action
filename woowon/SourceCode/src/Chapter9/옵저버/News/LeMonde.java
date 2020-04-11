package Chapter9.옵저버.News;

import Chapter9.옵저버.Interface.Observer;

public class LeMonde implements Observer {
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("wine")) {
            System.out.println(this.getClass().getName());
            System.out.println("Today cheese, wine and news! " + tweet);
        }
    }
}
