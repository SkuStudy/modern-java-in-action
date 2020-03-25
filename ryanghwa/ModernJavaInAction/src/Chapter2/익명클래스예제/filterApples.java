package Chapter2.익명클래스예제;

import Chapter2.익명클래스예제.Apple.Apple;
import Chapter2.익명클래스예제.PreDicate.ApplePredicate;

import java.util.ArrayList;
import java.util.List;

import static com.sun.tools.internal.xjc.reader.Ring.add;

public class filterApples {
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<>();

        for(Apple apple : inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
}
