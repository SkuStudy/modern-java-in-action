package Chapter10.DSL예제5;

import Chapter10.Stock;
import Chapter10.Trade;

public class StockBuilder {
    private final TradeBuilder builder;
    private final Trade trade;
    private final Stock stock = new Stock();

    public StockBuilder(TradeBuilder builder, Trade trade, String symbol){
        this.builder = builder;
        this.trade = trade;
        stock.setSymbol(symbol);
    }

    public TradeBuilder on(String market){
        stock.setMarket(market);
        trade.setStock(stock);
        return builder;
    }
}
