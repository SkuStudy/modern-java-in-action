package Chapter16.비동기파이프라인_2_비동기방식;

import static Chapter16.비동기파이프라인_1_순차적동기방식.Shop.delay;

// Quote 객체를 인수로 받아 할인된 가격 문자열을 반환하는 applyDiscount 메서드 지원
public class Discount {
    public enum Code {
        NONE(0),
        SILBER(5),
        GOLD(10),
        PLATINUM(15),
        DIAMOND(20);

        private final int percentage;

        Code(int percentage){
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote){
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code){
        delay();
        return price * (100 - code.percentage) / 100;
    }
}
