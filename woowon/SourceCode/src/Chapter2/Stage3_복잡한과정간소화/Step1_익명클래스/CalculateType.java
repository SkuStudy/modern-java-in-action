package Chapter2.Stage3_복잡한과정간소화.Step1_익명클래스;

import Chapter2.Stage3_복잡한과정간소화.CalculateOperator;

public enum CalculateType {

    PLUS("+", new CalculateOperator() {
        @Override
        public double calculate(double number1, double number2) {
            return number1 + number2;
        }
    }),

    MINUS("-", new CalculateOperator() {
        @Override
        public double calculate(double number1, double number2) {
            return number1 - number2;
        }
    }),

    MULTIPLY("*", new CalculateOperator() {
        @Override
        public double calculate(double number1, double number2) {
            return number1 * number2;
        }
    }),

    DIVISION("/", new CalculateOperator() {
        @Override
        public double calculate(double number1, double number2) {
            return number1 / number2;
        }
    });

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