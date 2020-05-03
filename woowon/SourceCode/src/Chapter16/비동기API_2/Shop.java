package Chapter16.비동기API_2;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
    public String name;

    public Shop(String name){
        this.name = name;
    }

    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> futurePrice = new CompletableFuture<>(); // 계산 결과를 포함할 CompletableFuture 생성
        new Thread( () -> {
            double price = calculatePrice(product); // 다른 스레드에서 비동기적으로 계산을 수행한다.
            futurePrice.complete(price);  // 오랜 시간이 걸리는 계산이 완료되면 Future에 값을 설정
        }).start();
        return futurePrice; // 계산 결과가 완료되길 기다리지 않고 Future를 반환한다.
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
