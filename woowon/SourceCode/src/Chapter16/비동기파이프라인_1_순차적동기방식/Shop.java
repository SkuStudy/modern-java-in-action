package Chapter16.비동기파이프라인_1_순차적동기방식;

import java.util.Random;

public class Shop {
    public String name;

    public Shop(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPrice(String product){
        Random random = new Random();
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s",name, price, code); //Ex) BestPrice:123.26:GOLD
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
