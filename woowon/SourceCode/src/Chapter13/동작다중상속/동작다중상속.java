package Chapter13.동작다중상속;

public class 동작다중상속 {
    public static void main(String[] args) {
        // 출력은 없고 메서드의 사용에 집중 !
        Monster m = new Monster();
        m.rotateBy(180); // 회전 가능
        m.moveHorizontally(10); // move 가능
        m.setRelativeSize(10,10); // 사이즈 조절 가능

        Sun s = new Sun();
        s.rotateBy(180); // 회전 가능
        s.moveHorizontally(10); // move 가능
        // 사이즈 조절 불가능
    }
}
