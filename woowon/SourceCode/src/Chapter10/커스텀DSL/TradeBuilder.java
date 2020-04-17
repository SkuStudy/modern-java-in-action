package Chapter10.커스텀DSL;

import Chapter10.Trade;

public class TradeBuilder {
    public final Trade trade = new Trade();

    public TradeBuilder quantity(int quantity){
        trade.setQuantity(quantity);
        return this;
    }

    public TradeBuilder at(double price){
        trade.setPrice(price);
        return this;
    }

    public StockBuilder stock(String symbol){
        return new StockBuilder(this, symbol);
    }
}
