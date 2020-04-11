package Chapter9.람다테스팅;

import java.util.Comparator;

import static java.util.Comparator.comparing;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point moveRightBy(int x) {
        return new Point(this.x + x, this.y);
    }

    // 람다를 테스트하기 위한 Comparator
    public final static Comparator<Point> compareByXAndThenY = comparing(Point::getX).thenComparing(Point::getY);
}
