package Chapter10.example.Stock_ex;

public class StockBuilder {
    private final MethodChainingOrderBuillder buillder;
    private final Trade trade;
    private final Stock stock = new Stock();

    public StockBuilder(MethodChainingOrderBuillder buillder, Trade trade, String symbol){
        this.buillder = buillder;
        this.trade = trade;
        stock.setSymbol(symbol);
    }

    public TradeBuilderWithStock on(String market){
        stock.setMarket(market);
        trade.setStock(stock);
        return new TradeBuilderWithStock(buillder, trade);
    }
}
