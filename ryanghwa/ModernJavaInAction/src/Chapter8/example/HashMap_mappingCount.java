package Chapter8.example;

import java.util.concurrent.ConcurrentHashMap;

public class HashMap_mappingCount {
    public static void main(String[] args) {
        Typetester typetester = new Typetester();

        ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<String, String>();
        chm.put("a","ㄱ");
        chm.put("b","ㄴ");
        chm.put("c","ㄷ");
        chm.put("d","ㄹ");
        chm.put("e","ㅁ");

        typetester.printType(chm.size());
        System.out.println(chm.size());

        typetester.printType(chm.mappingCount());
        System.out.println(chm.mappingCount());

        System.out.println(chm.keySet());
    }
}
