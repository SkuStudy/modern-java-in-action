package Chapter8.example;

import java.util.HashMap;
import java.util.Map;

public class 계산패턴_computerIfAbsent {
    public 계산패턴_computerIfAbsent(){

    }
    public static void main(String[] args) {
        new 계산패턴_computerIfAbsent().main();
    }
    private void main() {
        Map<String,String> favouriteMovies = new HashMap<>();
        favouriteMovies.put("Raphael","Star Wars");
        favouriteMovies.put("Cristina","Matrix");
        favouriteMovies.put("Olivia","James Bond");
        //computeIfAbsent null이면 새값
        favouriteMovies.computeIfAbsent("Raphael", this::compute_test);

        favouriteMovies.forEach((key,value)-> System.out.println(key+" : "+value));
    }
    private String compute_test(String key){
        return "add";
    }
}