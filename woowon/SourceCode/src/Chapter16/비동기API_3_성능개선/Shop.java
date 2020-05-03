package Chapter16.비동기API_3_성능개선;

import java.util.Random;

public class Shop {
    public String name;

    public Shop(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice(String product){
        return calculatePrice(product);
    }

    // 임의의 계산값을 반환하는 메서드
    public double calculatePrice(String product){
        delay();
        Random random = new Random();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    // 인위적으로 1초 지연 시키는 메서드
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
