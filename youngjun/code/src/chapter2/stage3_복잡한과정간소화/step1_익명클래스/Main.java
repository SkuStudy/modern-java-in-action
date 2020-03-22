package chapter2.stage3_복잡한과정간소화.step1_익명클래스;

/**
 * Created by iyoungman on 2020-03-20.
 */

public class Main {

    public static void main(String[] args) {
        String exampleType = "+";
        double number1 = 5;
        double number2 = 7;

        CalculateType calculateType = CalculateType.search(exampleType);
        double result = calculateType.calculate(number1, number2);

        System.out.println(result);
    }

}
