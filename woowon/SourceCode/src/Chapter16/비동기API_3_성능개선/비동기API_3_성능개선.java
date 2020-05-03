package Chapter16.비동기API_3_성능개선;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class 비동기API_3_성능개선 {
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

        System.out.println();

        long start2 = System.nanoTime();
        System.out.println(findPricesParallel(shops, "myPhone27S"));
        long duration2 = (System.nanoTime() - start2) / 1_000_000;
        System.out.println("Done in "+duration2 + " msecs");
    }

    // 4개의 상점을 검색하는 동안 각각 1초의 대기 시간이 있어 4초보다 조금 더 걸린다.
    public static List<String> findPrices(List<Shop> shops, String product){
        return shops.stream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(toList());
    }

    // 병렬 스트림으로 요청 병렬화 하기
    public static List<String> findPricesParallel(List<Shop> shops, String product){
        return shops.parallelStream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(toList());
    }
}
