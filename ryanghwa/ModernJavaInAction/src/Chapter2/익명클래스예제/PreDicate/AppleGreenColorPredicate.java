package Chapter2.익명클래스예제.PreDicate;

import Chapter2.익명클래스예제.Apple.Apple;
import Chapter2.익명클래스예제.Apple.Color;

public class AppleGreenColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return Color.GREEN.equals(apple.getColor());
    }
}
