package Chapter16.최적화리팩터링;

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

    // 랜덤으로 지연 시키는 메서드
    public static void delay() {
        Random random = new Random();
        int delayTime = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
