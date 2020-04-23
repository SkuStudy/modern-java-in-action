package Chapter10.example.Stock_ex;

public class TradeBuilder {
    private final MethodChainingOrderBuillder buillder;
    public final Trade trade = new Trade();
    public TradeBuilder(MethodChainingOrderBuillder builder, Trade.Type type, int quantity) {
        this.buillder = builder;
        trade.setType(type);
    }

    public StockBuilder stock(String symbol){
        return new  StockBuilder(buillder, trade, symbol);
    }
}
