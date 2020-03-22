package chapter2.stage3_복잡한과정간소화.step2_람다표현식사용;

import chapter2.stage3_복잡한과정간소화.CalculateBehavior;

/**
 * Created by YoungMan on 2020-03-16.
 */

public enum CalculateType {

    PLUS("+", (d1, d2) -> d1 + d2),

    MINUS("-", (d1, d2) -> d1 - d2),

    MULTIPLY("*", (d1, d2) -> d1 * d2),

    DIVISION("/", (d1, d2) -> d1 / d2);

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
