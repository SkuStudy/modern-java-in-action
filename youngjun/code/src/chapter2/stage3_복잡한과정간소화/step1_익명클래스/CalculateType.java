package chapter2.stage3_복잡한과정간소화.step1_익명클래스;

import chapter2.stage3_복잡한과정간소화.CalculateBehavior;

/**
 * Created by YoungMan on 2020-03-16.
 */

public enum CalculateType {

    PLUS("+", new CalculateBehavior() {
        @Override
        public double calculate(double number1, double number2) {
            return 0;
        }
    }),

    MINUS("-", new CalculateBehavior() {
        @Override
        public double calculate(double number1, double number2) {
            return 0;
        }
    }),

    MULTIPLY("*", new CalculateBehavior() {
        @Override
        public double calculate(double number1, double number2) {
            return 0;
        }
    }),

    DIVISION("/", new CalculateBehavior() {
        @Override
        public double calculate(double number1, double number2) {
            return 0;
        }
    });

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
