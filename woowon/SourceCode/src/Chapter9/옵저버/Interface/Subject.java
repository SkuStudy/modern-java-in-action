package Chapter9.옵저버.Interface;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
