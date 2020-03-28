package Chapter2.Stage2_동작파라미터화;

import Chapter2.Stage2_동작파라미터화.impl.DivisionOperator;
import Chapter2.Stage2_동작파라미터화.impl.MinusOperator;
import Chapter2.Stage2_동작파라미터화.impl.MultipleOperator;
import Chapter2.Stage2_동작파라미터화.impl.PlusOperator;

public enum CalculateType {

    PLUS("+", new PlusOperator()),
    MINUS("-", new MinusOperator()),
    MULTIPLY("*", new MultipleOperator()),
    DIVISION("/", new DivisionOperator());

    private String type;
    private CalculateOperator calculateOperator;

    CalculateType(String type, CalculateOperator calculateOperator) {
        this.type = type;
        this.calculateOperator = calculateOperator;
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
        return this.calculateOperator.calculate(number1, number2);
    }
}
