package Chapter2.익명클래스예제.PreDicate;

import Chapter2.익명클래스예제.Apple.Apple;

public class AppleWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}