package chapter2.stage2_동작파라미터화;

import chapter2.stage2_동작파라미터화.impl.DivisionBehavior;
import chapter2.stage2_동작파라미터화.impl.MinusBehavior;
import chapter2.stage2_동작파라미터화.impl.MultiplyBehavior;
import chapter2.stage2_동작파라미터화.impl.PlusBehavior;

/**
 * Created by YoungMan on 2020-03-16.
 */

public enum CalculateType {

    PLUS("+", new PlusBehavior()),
    MINUS("-", new MinusBehavior()),
    MULTIPLY("*", new MultiplyBehavior()),
    DIVISION("/", new DivisionBehavior());

    private String type;
    private CalculateBehavior calculateBehavior;

    CalculateType(String type, CalculateBehavior calculateBehavior) {
        this.type = type;
        this.calculateBehavior = calculateBehavior;
    }

    public static CalculateType search(String type) {
        CalculateType result = null;

        for (CalculateType calculateType : CalculateType.values()) {
            if (calculateType.type.equals(type)) {
                result = calculateType;
            }
        }

        return result;
    }

    public double calculate(double number1, double number2) {
        return this.calculateBehavior.calculate(number1, number2);
    }
}
