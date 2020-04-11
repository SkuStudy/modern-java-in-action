package Chapter8;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Main {
    public static void main(String[] args) {
        Map<String, String> family = Map.ofEntries(
                entry("Teo", "Star Wars"), entry("Cristina", "James Bond")
        );

        Map<String, String> friends = Map.ofEntries(
                entry("Raphael", "Star Wars"), entry("Cristina", "Matrix")
        );

        // 기존 코드 - 두 맵을 합치는 코드
        Map<String, String> everyone1 = new HashMap<>(family);
        everyone1.putAll(friends);
        System.out.println("그냥 맵을 합치는 코드");
        System.out.println(everyone1);

        // merge 메서드 사용 - 조건에 따라 맵을 합치는 코드
        Map<String, String> everyone2 = new HashMap<>(family);
        friends.forEach((k, v) -> everyone2.merge(k, v, (movie1, movie2) -> movie1 + " & " + movie2));
        System.out.println("조건에 따라 맵을 합치는 코드");
        System.out.println(everyone2);
    }
}
