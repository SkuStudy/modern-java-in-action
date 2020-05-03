package Chapter16.비동기파이프라인_2_비동기방식;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.util.stream.Collectors.toList;

// 최적화와는 거리가 먼 구현
public class 비동기방식 {
    public static void main(String[] args) {
        List<Shop> shops = Arrays.asList(
                new Shop("BestShop"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("ModernShop"));
        long start1 = System.nanoTime();
        System.out.println(findPrices(shops, "myPhone27S"));
        long duration1 = (System.nanoTime() - start1) / 1_000_000;
        System.out.println("Done in "+duration1 + " msecs");
    }

    // 비동기적으로 재구현
    public static List<String> findPrices(List<Shop> shops, String product){
        Executor executor =
                Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                        new ThreadFactory() {
                            public Thread newThread(Runnable r) {
                                Thread t = new Thread(r);
                                t.setDaemon(true); // 프로그램 종료를 방해하지 않는 데몬 스레드를 사용한다.
                                return t;
                            }
                        });

        List<CompletableFuture<String>> pricefutures =
                shops.stream()
                     .map(shop -> CompletableFuture.supplyAsync(()-> shop.getPrice(product), executor)) // 동기 동작 1 -> 비동기로 변경
                     .map(future -> future.thenApply(Quote::parse))// 동기 동작이 아니니 thenApply로 연결
                     .map(future -> future.thenCompose(quote ->     // 동기 동작이니 thenCompose로 비동기 동작 연결 ( 이전 연산의 결과를 다음 연산으로 전달 )
                             CompletableFuture.supplyAsync(()->
                                     Discount.applyDiscount(quote), executor)) // 동기 동작 2 -> 비동기로 변경
                     )
                     .collect(toList());

        return pricefutures.stream()
                .map(CompletableFuture::join) // 모든 작업이 완료되길 기다렸다가 join으로 값을 추출
                .collect(toList());
    }
}
