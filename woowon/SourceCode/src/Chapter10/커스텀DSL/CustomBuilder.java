package Chapter10.커스텀DSL;

import Chapter10.Order;
import Chapter10.Trade;

import java.util.function.Consumer;

public class CustomBuilder {
    public final Order order = new Order(); // 빌더로 감싼 주문

    public CustomBuilder(String customer){
        order.setCustomer(customer);
    }

    public static CustomBuilder forCustomer(String customer){
        return new CustomBuilder(customer);
    }

    public CustomBuilder buy(Consumer<TradeBuilder> consumer){
        buildTrade(consumer, Trade.Type.BUY);
        return this;
    }

    public CustomBuilder sell(Consumer<TradeBuilder> consumer){
        buildTrade(consumer, Trade.Type.BUY);
        return this;
    }

    public Order end(){
        return this.order;
    }

    private TradeBuilder buildTrade(Consumer<TradeBuilder> consumer, Trade.Type type){
        TradeBuilder builder = new TradeBuilder();
        builder.trade.setType(type);

        order.addTrade(builder.trade);

        consumer.accept(builder);
        return builder;
    }

}
