package Chapter1;

public class Default_다중상속 implements Test1, Test2 {

    //방법1 - 오버라이드해서 구현하기
//    @Override
//    public void test() {
//
//    }

    //방법2 - 직접 어떤 Default 메서드 사용할 지 명시해주기
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
