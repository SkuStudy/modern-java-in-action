package Chapter2.Stage3_복잡한과정간소화.Step2_람다표현식;

import Chapter2.Stage2_동작파라미터화.CalculateType;

public class Main {

    public static void main(String[] args) {
        String exampleType = "*";
        double number1 = 10;
        double number2 = 5;

        CalculateType calculateType = CalculateType.search(exampleType);
        double result = calculateType.calculate(number1, number2);

        System.out.println(result);
    }

}
