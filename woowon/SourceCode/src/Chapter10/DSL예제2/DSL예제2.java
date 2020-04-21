package Chapter10.DSL예제2;

import Chapter10.Order;

import static Chapter10.DSL예제2.MethodChainingOrderBuilder.forCustomer;

public class DSL예제2 {
    public static void main(String[] args) {
        Order order = forCustomer("BigBank")
                .buy(80)
                .stock("IBM")
                .on("NYSE")
                .at(125.00)
                .sell(50)
                .stock("GOOGLE")
                .on("NASDAQ")
                .at(375.00)
                .end();

        System.out.println(order.toString());
    }
}
