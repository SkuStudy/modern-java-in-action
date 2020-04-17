package Chapter10.DSL예제6.개선된세금기능;

import Chapter10.Order;

import java.util.function.DoubleUnaryOperator;

public class TaxCalculator {
    public DoubleUnaryOperator taxFunction = d -> d;       // 주문 값에 적용된 모든 세금을 계산하는 함수

    public TaxCalculator with(DoubleUnaryOperator f){
        taxFunction = taxFunction.andThen(f);               // 새로운 세금 계산 함수를 얻어서 인수로 전달된 함수와 현재 함수를 합친다.
        return this;
    }

    public double calculate(Order order){
        return taxFunction.applyAsDouble(order.getValue()); // 주문의 총 합에 세금 계산 함수를 적용해 최종 주문값을 계산
    }
}
