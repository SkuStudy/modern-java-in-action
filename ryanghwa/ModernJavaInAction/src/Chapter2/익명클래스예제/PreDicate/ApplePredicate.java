package Chapter2.익명클래스예제.PreDicate;

import Chapter2.익명클래스예제.Apple.Apple;

public interface ApplePredicate<T> {

    boolean test(Apple apple);
}
