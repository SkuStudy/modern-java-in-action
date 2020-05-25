package Chapter3.ex;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ex1 {
    public static void main(String[] args) {
        Myinterface m1  = ()-> System.out.println("hello");
        m1.test();

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        System.out.println("forEach");
        list.forEach(System.out::println);

        Map<String, Integer> map = new HashMap<>();
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);

        map.forEach((key,value)-> System.out.println(key+" : "+value));
    }
}
