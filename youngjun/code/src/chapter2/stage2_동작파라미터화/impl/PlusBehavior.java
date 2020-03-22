package chapter2.stage2_동작파라미터화.impl;

import chapter2.stage2_동작파라미터화.CalculateBehavior;

/**
 * Created by iyoungman on 2020-03-20.
 */

public class PlusBehavior implements CalculateBehavior {

    @Override
    public double calculate(double number1, double number2) {
        return number1 + number2;
    }
}
