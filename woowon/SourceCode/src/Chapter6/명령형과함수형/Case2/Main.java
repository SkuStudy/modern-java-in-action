package Chapter6.명령형과함수형.Case2;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class Main {
    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(2011 ,300, Currency.KOREA),
                new Transaction(2012 ,1000, Currency.America),
                new Transaction(2011 ,400, Currency.Japan),
                new Transaction(2012 ,710, Currency.America),
                new Transaction(2012 ,700, Currency.America),
                new Transaction(2012 ,950, Currency.KOREA)
        );

        // 책에서 소개한 예제 1 - 함수형 프로그래밍 버전
        Map<Currency, List<Transaction>> transactionByCurrenciesEx = transactions.stream()
                .collect(groupingBy(Transaction::getCurrency));
        System.out.println("Case2. 예제1 - Map<Currency, ~>");
        transactionByCurrenciesEx.entrySet().forEach(System.out::println);

        // 책에서 소개한 예제 2 - 함수형 프로그래밍 버전
        Map<String, List<Transaction>> transactionByCurrencies = transactions.stream()
                .collect(groupingBy((transaction -> transaction.getCurrency().getCode())));
        System.out.println("Case2. 예제2 - Map<String, ~>");
        transactionByCurrencies.entrySet().forEach(System.out::println);
    }
}
