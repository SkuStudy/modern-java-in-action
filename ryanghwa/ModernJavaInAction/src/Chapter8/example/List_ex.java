package Chapter8.example;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class List_ex {
    public static void main(String[] args) {
        //
        List<String> friends1 = new ArrayList<>();
        friends1.add("Raphael");
        friends1.add("Olivia");
        friends1.add("Thibaut");

        friends1.set(0, "Lee");
        System.out.println("friends1 = "+ friends1);

        List<String> friends2 = Arrays.asList("Rapheal", "Olivia", "Thibaut");
        friends2.set(0, "Lee");
        System.out.println("friends2 = "+ friends2);

        List<String> friends3 = List.of("Rapheal", "Olivia", "Thibaut");
        friends3.set(0,"Lee");  //List.of는 변경 불가
        System.out.println("friends3 = "+ friends3);

    }

}