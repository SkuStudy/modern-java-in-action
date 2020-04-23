package Chapter10.example.Stock_ex;

public class MethodChainingOrderBuillder {
    public final Order order = new Order();
    private MethodChainingOrderBuillder(String customer){
        order.setCustomer(customer);
    }
    public static MethodChainingOrderBuillder forCustomer(String customer){
        return new MethodChainingOrderBuillder((customer));
    }
    public TradeBuilder buy(int quantity){
        return new TradeBuilder(this, Trade.Type.BUY,quantity);
    }

    public TradeBuilder sell(int quantity){
        return new TradeBuilder(this, Trade.Type.SELL,quantity);
    }

    public MethodChainingOrderBuillder addTrade(Trade trade){
        order.addTrade(trade);
        return this;
    }
    public Order end(){
        return order;
    }
}
