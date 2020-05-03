package Chapter16.최적화리팩터링;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

// 최적화와는 거리가 먼 구현
public class 최적화리팩터링 {
    public static void main(String[] args) {
        List<Shop> shops = Arrays.asList(
                new Shop("BestShop"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("ModernShop"));
        long start1 = System.nanoTime();
        CompletableFuture[] futures = findPricesStream(shops, "myPhone")
                .map(f -> f.thenAccept( s ->
                        System.out.println(s + " ( done in " + ((System.nanoTime() - start1) / 1_000_000) + " msecs )")))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        long duration1 = (System.nanoTime() - start1) / 1_000_000;
        System.out.println("All shops have now responded in " + duration1 + " msecs");
    }

    // 비동기적으로 재구현
    public static Stream<CompletableFuture<String>> findPricesStream(List<Shop> shops, String product) {
        Executor executor =
                Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                        new ThreadFactory() {
                            public Thread newThread(Runnable r) {
                                Thread t = new Thread(r);
                                t.setDaemon(true); // 프로그램 종료를 방해하지 않는 데몬 스레드를 사용한다.
                                return t;
                            }
                        });

        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(() ->
                                Discount.applyDiscount(quote), executor)));
    }
}
