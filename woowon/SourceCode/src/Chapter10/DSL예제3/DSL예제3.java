package Chapter10.DSL예제3;

import Chapter10.Order;

import static Chapter10.DSL예제3.NestedFunctionOrderBuilder.*;

public class DSL예제3 {
    public static void main(String[] args) {
        Order order = order("BigBank",
                buy(80,
                        stock("IBM", on("NYSE")), at(125.00)),
                sell(50,
                        stock("GOOGLE", on("NASDAQ")), at(375.00))
        );

        System.out.println(order.toString());
    }
}
