package Chapter19;

import static Chapter19.LazyList.from;

public class MyListMain {
    public static void main(String[] args) {
        MyList<Integer> l = new MyLinkdeList<>(5,new MyLinkdeList<>(10,new Empty<>()));
        System.out.println(l.head()+" => "+l.tail().head());

        LazyList<Integer> numbers = from(2);
        int two = numbers.head();
        int three = numbers.tail().head();
        int four = numbers.tail().tail().head();

        System.out.println(two+" "+three+" "+four);
    }
}
