package Chapter11;

import java.util.Optional;

public class Person {
    private Car car;  // 사람이 차를 소유했을 수도 않을 수도
    public Optional<Car> getCar(){
        return Optional.ofNullable(car);
//        return Optional.of(car);
    }

    public Person(Car car) {
        this.car = car;
    }
}