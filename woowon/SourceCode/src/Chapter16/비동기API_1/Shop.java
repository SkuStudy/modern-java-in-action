package Chapter16.비동기API_1;

import java.util.Random;

// 사용자가 이 API를 호출하면 비동기 동작이 완료될 때까지 1초 동안 블록된다.
public class Shop {
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
