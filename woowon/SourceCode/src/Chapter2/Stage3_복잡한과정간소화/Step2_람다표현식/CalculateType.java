package Chapter2.Stage3_복잡한과정간소화.Step2_람다표현식;

import Chapter2.Stage3_복잡한과정간소화.CalculateOperator;

public enum CalculateType {

    PLUS("+", (d1, d2) -> d1 + d2),

    MINUS("-", (d1, d2) -> d1 - d2),

    MULTIPLY("*", (d1, d2) -> d1 * d2),

    DIVISION("/", (d1, d2) -> d1 / d2);

    private String type;
    private CalculateOperator CalculateOperator;

    CalculateType(String type, CalculateOperator CalculateOperator) {
        this.type = type;
        this.CalculateOperator = CalculateOperator;
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
        return this.CalculateOperator.calculate(number1, number2);
    }
}
