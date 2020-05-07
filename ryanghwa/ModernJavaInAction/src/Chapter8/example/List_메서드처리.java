package Chapter8.example;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class List_메서드처리 {
    public static void main(String[] args) {



        List<String> referenceCodes = new ArrayList<>(Arrays.asList("a12","C14","b13","1A"));

        referenceCodes.removeIf(n->Character.isDigit(n.charAt(0)));

//        referenceCodes.stream()
//                .map(code->Character.toUpperCase(code.charAt(0))+code.substring(1))
//                .collect(Collectors.toList())
//                .forEach(System.out::println);
//
//        referenceCodes.replaceAll(code-> Character.toUpperCase(code.charAt(0))+code.substring(1));
        System.out.println(referenceCodes);
    }
}
