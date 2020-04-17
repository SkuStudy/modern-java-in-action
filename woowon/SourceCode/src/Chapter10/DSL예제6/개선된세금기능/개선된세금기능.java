package Chapter10.DSL예제6.개선된세금기능;

import Chapter10.DSL예제6.Tax;
import Chapter10.Order;

import static Chapter10.DSL예제5.MixedBuilder.*;

public class 개선된세금기능 {
    public static void main(String[] args) {
        Order order = forCustomer("BigBank",
                buy(t -> t.quantity(80)
                        .stock("IBM")
                        .on("NYSE")
                        .at(125.00)),
                sell(t -> t.quantity(50)
                        .stock("GOOGLE")
                        .on("NASDAQ")
                        .at(375.00))
        );
        System.out.println(order.toString());

        System.out.println("유창하게 정의하는 세금 기능 2");
        System.out.println("세전 : " + order.getValue());
        double value = new TaxCalculator().with(Tax::regional)
                                          .with(Tax::surcharge)
                                          .calculate(order);
        System.out.println("세후 : " + value);
    }
}
