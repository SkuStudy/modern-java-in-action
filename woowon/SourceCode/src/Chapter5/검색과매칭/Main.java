package Chapter5.검색과매칭;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800,Dish.Type.MEAT),
                new Dish("beef", false, 700,Dish.Type.MEAT),
                new Dish("chicken", false, 400,Dish.Type.MEAT),
                new Dish("rice", true, 350,Dish.Type.OTHER),
                new Dish("pizza", true, 550,Dish.Type.OTHER)
        );
        //프레디케이트가 적어도 한 요소와 일치하는지 확인
        boolean isExistVegetarian = menu.stream().anyMatch(Dish::isVegetarian);
        System.out.println("isExistVegetarian : "+ isExistVegetarian);

        //프레디케이트가 모든 요소와 일치하는지 검사
        boolean isHealthy = menu.stream().allMatch(dish -> dish.getCalories() < 1000);
        System.out.println("isHealthy : "+isHealthy);

        //프레디케이트가 모든 요소와 일치하지 않는지 검사 ( allMatch의 반대 )
        isHealthy = menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);
        System.out.println("isHealthy : "+isHealthy);

        //findAny  메서드는 현재 스트림에서 임의의 요소를 반환
        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
        System.out.println("dish : "+dish);

        //findFirst 메서드는 스트림의 첫 번째 요소를 반환
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstNumber = someNumbers.stream().map(n -> n*n).findFirst();
        System.out.println("firstNumber : "+firstNumber);
    }
}
