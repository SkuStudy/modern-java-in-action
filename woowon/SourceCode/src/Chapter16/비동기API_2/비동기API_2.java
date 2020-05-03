package Chapter16.비동기API_2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class 비동기API_2 {
    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");  // 상품 가격 정보 요청
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after : "+ invocationTime + " msecs - 바로 futurePrice 반환");

        //제품의 가격을 계산하는 동안 다른 작업 수행
        doSomethingElse();
        try{
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    public static void doSomethingElse() {
        int count = 0;
        for(int i=0;i< 10000;i++) {
            count += i;
        }
    }
}
