package Chapter15;

public class Main {
    public static void main(String[] args) {
//        SimpleCell c1 = new SimpleCell("C1");
//        SimpleCell c2 = new SimpleCell("C2");
//        SimpleCell c3 = new SimpleCell("C3");
//
//        c1.subscribe(c3); // c3는 c1을 구독
//
//        c1.onNext(10); // c1의 값을 10으로 갱신
//        c2.onNext(20); // c2의 값을 20으로 갱신
//        결과
//        C1:10
//        C3:10
//        C2:20

        // C3 = C1 + C2 는 ?
        ArithmeticCell c3 = new ArithmeticCell("C3");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3::setLeft);
        c2.subscribe(c3::setRight);

        c1.onNext(10); // C1의 값을 10으로 갱신
        c2.onNext(20); // C2의 값을 20으로 갱신
        c1.onNext(15); // C1의 값을 15로 갱신
//        결과
//        C1:10
//        C3:10
//        C2:20
//        C3:30
//        C1:15
//        C3:35
    }
}
