package Chapter8.example;

import java.util.Map;

import static java.util.Map.entry;

public class forEack메서드 {
    public static void main(String[] args) {
        Map<String, Integer> ageOfFriends
                = Map.ofEntries(entry("Raphael",30),
                entry("Olivia", 25),
                entry("Thibaut",26));

        ageOfFriends.forEach((friend,age)-> System.out.println(friend+" is "+age+" years old"));
        ageOfFriends.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);
    }
}
