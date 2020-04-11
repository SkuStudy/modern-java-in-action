package Chapter9.전략;

import Chapter9.전략.Class.IsAllLowerCase;
import Chapter9.전략.Class.IsNumeric;

public class 전략 {
    public static void main(String[] args) {
        System.out.println("기존의 전략 패턴");
        // 기존의 전략 패턴
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");
        System.out.println(b1);

        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbbb");
        System.out.println(b2);


        System.out.println("람다로 바뀐 전략 패턴");
        // 람다로 바뀐 전략 패턴 - 전략 패턴을 위해서 새로운 클래스를 생성할 필요가 없다 !
        Validator numericLambda = new Validator((String s) -> s.matches("\\d+"));
        boolean b3 = numericLambda.validate("aaaa");
        System.out.println(b3);

        Validator lowerCaseLambda = new Validator((String s) -> s.matches("[a-z]+"));
        boolean b4 = lowerCaseLambda.validate("bbbb");
        System.out.println(b4);
    }
}
