package Chapter3.example;

//하나의 추상메서드만 가지고 있는 함수형 인터페이스에서의 람다 사용
public class RunnableEX {
    public static void main(String[] args) {
        //람다 사용
        Runnable r1 = () -> System.out.println("Hello World 1");

        //익명클래스
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World 2");
            }
        };

        process(r1);
        process(r2);

        //람다 표현식으로 출력
        process(()->System.out.println("Hello World 3"));

    }

    public static void process(Runnable r){
        r.run();
    }
}