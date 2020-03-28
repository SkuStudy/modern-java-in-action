package Chapter2.Stage1_변화하는요구사항;

public class Main {

    public static void main(String[] args) {
        String exampleType = "*";
        double number1 = 10;
        double number2 = 5;

        double result = calculate(exampleType, number1, number2);

        System.out.println(result);
    }

    private static double calculate(String calculateType, double number1, double number2) {
        switch (calculateType){
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            default:
                return number1 / number2;
        }
    }

}
