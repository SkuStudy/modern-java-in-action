package Chapter10.DSL예제2;

import Chapter10.Order;
import Chapter10.Trade;

// 주문 빌더의 buy, sell은 다른 주문을 만들어 추가할 수 있도록 자신을 만들어 반환한다.
public class MethodChainingOrderBuilder {
    public final Order order = new Order(); // 빌더로 감싼 주문

    private MethodChainingOrderBuilder(String customer){
        order.setCustomer(customer);
    }

    public static MethodChainingOrderBuilder forCustomer(String customer){
        return new MethodChainingOrderBuilder(customer); // 고객의 주문을 만드는 정적 팩토리 메서드
    }

    public TradeBuilder buy(int quantity){
        return new TradeBuilder(this, Trade.Type.BUY, quantity);
    }

    public TradeBuilder sell(int quantity){
        return new TradeBuilder(this, Trade.Type.SELL, quantity);
    }

    public MethodChainingOrderBuilder addTrade(Trade trade){
        order.addTrade(trade); // 주문에 주식 추가
        return this; // 유연하게 추가 주문을 만들어 추가할 수 있도록 주문 빌더 자체를 반환
    }

    public Order end(){
        return order; // 주문 만들기 종료
    }
}
