package Chapter2.Stage2_동작파라미터화.impl;

import Chapter2.Stage2_동작파라미터화.CalculateOperator;

public class DivisionOperator implements CalculateOperator {

    @Override
    public double calculate(double number1, double number2) {
        return number1 / number2;
    }
}

