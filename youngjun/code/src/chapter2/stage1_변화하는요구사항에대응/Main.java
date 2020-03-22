package chapter2.stage1_변화하는요구사항에대응;

/**
 * Created by iyoungman on 2020-03-20.
 */

public class Main {

    public static void main(String[] args) {
        String exampleType = "+";
        double number1 = 5;
        double number2 = 7;

        double result = calculate(exampleType, number1, number2);

        System.out.println(result);
    }

    private static double calculate(String calculateType, double number1, double number2) {
        if (calculateType.equals("+")) {//또는 switch
            return number1 + number2;
        } else if (calculateType.equals("-")) {
            return number1 - number2;
        } else if (calculateType.equals("*")) {
            return number1 * number2;
        } else {
            return number1 / number2;
        }
    }

}
