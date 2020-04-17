package Chapter10;

import java.util.ArrayList;
import java.util.List;

// 고객이 요청한 한 개 이상의 거래의 주문(order)
public class Order {
    private String customer;
    private List<Trade> trades = new ArrayList<>();

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

    public void addTrade(Trade trade){
        trades.add(trade);
    }

    public double getValue(){
        return trades.stream().mapToDouble(Trade::getValue).sum();
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer='" + customer + '\'' +
                ", trades=" + trades +
                '}';
    }
}
