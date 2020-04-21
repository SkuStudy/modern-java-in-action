package Chapter11.Optional스트림조작;

import Chapter11.Car;
import Chapter11.Insurance;
import Chapter11.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class Optional스트림조작 {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person(new Car(new Insurance("삼성화재"))),
                new Person(new Car(new Insurance("DB손해보험"))),
                new Person(new Car(new Insurance("KB손해보험"))),
                new Person(new Car(new Insurance("메리츠화재"))),
                new Person(new Car(new Insurance(null)))
        );

        Set<String> names1 = getCarInsuranceNames(persons);
        System.out.println("예제 11-6");
        System.out.println(names1);

        Set<String> names2 = getCarInsuranceNames2(persons);
        System.out.println("예제 11-7");
        System.out.println(names2);

    }

    // 예제 11-6
    public static Set<String> getCarInsuranceNames(List<Person> persons){
        return persons.stream()
                .map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optIns -> optIns.map(Insurance::getName))
                .flatMap(Optional::stream)
                .collect(toSet());
    }
    // 예제 11-7
    public static Set<String> getCarInsuranceNames2(List<Person> persons){
        Stream<Optional<String>> stream = persons.stream()
                .map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optIns -> optIns.map(Insurance::getName));
        return stream.filter(Optional::isPresent)
                     .map(Optional::get)
                     .collect(toSet());
    }
}
