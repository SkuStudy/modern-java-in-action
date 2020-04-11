package Chapter9.의무체인;

import Chapter9.의무체인.Process.HeaderTextProcessing;
import Chapter9.의무체인.Process.SpellCheckerProcessing;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class 의무체인 {
    public static void main(String[] args) {
        // 기존의 의무 체인 방식
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);
        String result1 = p1.handle("Aren't labdas really sexy?");
        System.out.println("기존의 의무 체인 방식: labda -> lambda");
        System.out.println(result1);

        // 람다 표현식 방식
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;  // 첫번째 작업 처리
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda"); // 두번째 작업 처리
        Function<String, String> pipline = headerProcessing.andThen(spellCheckerProcessing); // 동작 체인으로 두 함수를 조합
        String result2 = pipline.apply("Aren't labdas really sexy?");
        System.out.println("람다 표현식 방식: labda -> lambda");
        System.out.println(result2);

    }
}
