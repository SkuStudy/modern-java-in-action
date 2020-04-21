package Chapter11;

import java.util.Optional;

public class Car {
    private Insurance insurance;
    public Optional<Insurance> getInsurance(){
        return Optional.ofNullable(insurance);
//        return Optional.of(insurance);
    }

    public Car(Insurance insurance) {
        this.insurance = insurance;
    }
}
