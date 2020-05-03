package Chapter16.비동기파이프라인_1_순차적동기방식;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

// 최적화와는 거리가 먼 구현
public class 순차적동기방식 {
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

    public static List<String> findPrices(List<Shop> shops, String product){
        return shops.stream()
                .map(shop -> shop.getPrice(product)) // 각 상점에서 할인 전 가격 얻기
                .map(Quote::parse)         // 상점에서 반환한 결과 문자열을 파싱해 Quote 객체로 변환
                .map(Discount::applyDiscount) // Discount 객체를 사용해 각 Quote에 할인을 적용
                .collect(toList());
    }
}
