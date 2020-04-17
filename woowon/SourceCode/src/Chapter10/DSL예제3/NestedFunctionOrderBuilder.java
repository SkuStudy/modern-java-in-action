package Chapter10.DSL예제3;

import Chapter10.Order;
import Chapter10.Stock;
import Chapter10.Trade;

import java.util.stream.Stream;

public class NestedFunctionOrderBuilder {
    public static Order order (String customer, Trade... trades){
        Order order = new Order(); // 해당 고객 주문 만들기
        order.setCustomer(customer);
        Stream.of(trades).forEach(order::addTrade); // 주문에 모든 거래 추가
        return order;
    }

    public static Trade buy(int quantity, Stock stock,double price){
        return buildTrade(quantity, stock, price, Trade.Type.BUY);  // 매수 거래 만들기
    }

    public static Trade sell(int quantity, Stock stock,double price){
        return buildTrade(quantity, stock, price, Trade.Type.SELL); // 매도 거래 만들기
    }

    private static Trade buildTrade(int quantity, Stock stock, double price, Trade.Type buy){
        Trade trade = new Trade();
        trade.setQuantity(quantity);
        trade.setStock(stock);
        trade.setPrice(price);
        trade.setType(buy);
        return trade;
    }

    public static double at(double price){  //거래된 주식의 단가를 정하는 더미 메서드
        return price;
    }

    public static Stock stock(String symbol, String market){
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setMarket(market);
        return stock;
    }

    public static String on(String market){ // 주식이 거래된 시장을 정의하는 더미 메서드
        return market;
    }
}
