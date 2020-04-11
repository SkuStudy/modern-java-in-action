package Chapter9.람다테스팅;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class 람다테스팅 {
    @Test
    public void testComparingTwoPoints() throws Exception{
        Point p1 = new Point(10, 15);
        Point p2 = new Point(10, 20);
        int result = Point.compareByXAndThenY.compare(p1, p2);
        assertTrue(result < 0);
    }
}
