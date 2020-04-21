package Chapter10.커스텀DSL;

import Chapter10.Stock;
import Chapter10.Trade;

public class StockBuilder {
    private final TradeBuilder builder;
    private final Trade trade;
    public final Stock stock = new Stock();

    public StockBuilder(TradeBuilder builder, String symbol){
        this.builder = builder;
        this.trade = builder.trade;
        stock.setSymbol(symbol);
    }

    public TradeBuilder on(String market){
        stock.setMarket(market);
        trade.setStock(stock);
        return builder;
    }
}
