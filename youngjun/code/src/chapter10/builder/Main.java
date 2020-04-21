package chapter10.builder;

import chapter10.builder.model.Order;
import chapter10.builder.model.Stock;
import chapter10.builder.model.Trade;
import java.util.Arrays;

/**
 * Created by iyoungman on 2020-04-18.
 */

public class Main {

    public static void main(String[] args) {
        //Stock -> Order -> Trade
        Stock stock1 = new Stock.Builder()
                .symbol("IBM")
                .market("NYSE")
                .build();

        Trade trade = new Trade.Builder()
                .price(125.00)
                .quantity(3)
                .stock(stock1)
                .build();

        Order order = new Order.Builder()
                .customer("BigBack")
                .trades(Arrays.asList(trade))
                .build();
    }
}
