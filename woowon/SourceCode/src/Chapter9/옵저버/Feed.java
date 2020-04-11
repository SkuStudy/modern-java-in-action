package Chapter9.옵저버;

import Chapter9.옵저버.Interface.Observer;
import Chapter9.옵저버.Interface.Subject;

import java.util.ArrayList;
import java.util.List;

public class Feed implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers(String tweet) {
        this.observers.forEach(o -> o.notify(tweet));
    }
}
