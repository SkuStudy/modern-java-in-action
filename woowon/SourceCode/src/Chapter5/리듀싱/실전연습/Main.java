package Chapter5.리듀싱.실전연습;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011 ,300),
                new Transaction(raoul, 2012 ,1000),
                new Transaction(raoul, 2011 ,400),
                new Transaction(mario, 2012 ,710),
                new Transaction(mario, 2012 ,700),
                new Transaction(alan, 2012 ,950)
        );

        //Q1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오.
        List<Transaction> Q1 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println("-----Q1-----");
        Q1.forEach(System.out::println);

        //Q2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
        List<String> Q2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("-----Q2-----");
        Q2.forEach(System.out::println);

        //Q3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
        List<Trader> Q3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println("-----Q3-----");
        Q3.forEach(System.out::println);

        //Q4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
        String Q4 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(","));
        System.out.println("-----Q4-----");
        System.out.println(Q4);
    }
}
