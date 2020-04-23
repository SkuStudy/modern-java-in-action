package Chapter10.example.Stock_ex;

public class TradeBuilderWithStock {
    private final MethodChainingOrderBuillder buillder;
    private final Trade trade;

    public TradeBuilderWithStock(MethodChainingOrderBuillder buillder, Trade trade){
        this.buillder = buillder;
        this.trade = trade;
    }
    public MethodChainingOrderBuillder at(double price){
        trade.setPrice(price);
        return buillder.addTrade(trade);
    }
}
