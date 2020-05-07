package Chapter5.example;

import java.util.Arrays;

public class 스트림만들기 {
    public static void main(String[] args) {
        int[] num = {1,2,3,4,5};
        int sum = Arrays.stream(num).sum();
        System.out.println(sum);
    }

}
