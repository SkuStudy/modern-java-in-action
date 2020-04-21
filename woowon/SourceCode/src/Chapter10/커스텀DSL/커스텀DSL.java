package Chapter10.커스텀DSL;

import Chapter10.Order;

import static Chapter10.커스텀DSL.CustomBuilder.forCustomer;

public class 커스텀DSL {
    public static void main(String[] args) {

        Order order = forCustomer("BigBank")
                .buy(t -> t.quantity(80)
                        .stock("IBM")
                        .on("NYSE")
                        .at(125.00))
                .sell(t -> t.quantity(50)
                        .stock("GOOGLE")
                        .on("NASDAQ")
                        .at(375.00))
                .end();

        System.out.println(order.toString());
    }
}
