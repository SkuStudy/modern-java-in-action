package Chapter10.DSL예제5;

import Chapter10.Order;

import static Chapter10.DSL예제5.MixedBuilder.*;

public class DSL예제5 {
    public static void main(String[] args) {
        Order order = forCustomer("BigBank", // 최상위 수준 주문의 속성을 지정하는 중첩 함수
                buy(t -> t.quantity(80)               // 한 개의 주문을 만드는 람다 표현식
                          .stock("IBM")       // 거래 객체를 만드는 람다 표현식 바디의 메서드 체인
                          .on("NYSE")
                          .at(125.00)),
                sell(t -> t.quantity(50)
                           .stock("GOOGLE")
                           .on("NASDAQ")
                           .at(375.00))
        );

        System.out.println(order.toString());
    }
}
