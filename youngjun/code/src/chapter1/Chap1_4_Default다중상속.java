package chapter1;

/**
 * Created by iyoungman on 2020-03-20.
 * https://doohyun.tistory.com/48
 */

public class Chap1_4_Default다중상속 implements Test1, Test2 {

    //방법1
//    @Override
//    public void test() {
//
//    }

    //방법2
    public void test() {
        Test1.super.test();
    }
}

interface Test1 {

    default void test() {
        System.out.println("TEST1");
    }
}

interface Test2 {

    default void test() {
        System.out.println("TEST2");
    }
}











