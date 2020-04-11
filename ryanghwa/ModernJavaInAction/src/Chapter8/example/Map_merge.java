package Chapter8.example;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Map_merge {
    public static void main(String[] args) {
        Map<String, String> family = Map.ofEntries(entry("Teo","Star Wars"),entry("Cristina","James Bond"));
        Map<String, String> friends = Map.ofEntries(entry("Raphael","Star Wars"),entry("Cristina","Matrix"));

        Map<String, String> everyone1 = new HashMap<>(family);
        Map<String, String> everyone2 = new HashMap<>(family);

        everyone1.putAll(friends);
        System.out.println(everyone1);

        friends.forEach((k,v) ->
                everyone2.merge(k,v,(movie1, movie2) -> movie1+" & "+movie2));

        System.out.println(everyone2);


    }
}
