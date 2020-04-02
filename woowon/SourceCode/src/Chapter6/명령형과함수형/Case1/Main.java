package Chapter6.명령형과함수형.Case1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class Main {
    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(2011 ,300, new Currency("한국", "KRW")),
                new Transaction(2012 ,1000, new Currency("미국", "USD")),
                new Transaction(2011 ,400, new Currency("일본", "JPY")),
                new Transaction(2012 ,710, new Currency("미국", "USD")),
                new Transaction(2012 ,700, new Currency("미국", "USD")),
                new Transaction(2012 ,950, new Currency("한국", "KRW"))
        );

        // 책에서 소개한 예제 1 - 함수형 프로그래밍 버전
        Map<Currency, List<Transaction>> transactionByCurrenciesEx = transactions.stream()
                .collect(groupingBy(Transaction::getCurrency));
        System.out.println("Case1. 예제1 - Map<Currency, ~>");
        transactionByCurrenciesEx.entrySet().forEach(System.out::println);


        // 책에서 소개한 예제 2 - 함수형 프로그래밍 버전
        Map<String, List<Transaction>> transactionByCurrencies = transactions.stream()
                .collect(groupingBy((transaction -> transaction.getCurrency().getCode())));
        System.out.println("Case1. 예제2 - Map<String, ~>");
        transactionByCurrencies.entrySet().forEach(System.out::println);




        /*
            명령형 프로그래밍 버전 (구)
            Map<Curreny, ~~ > 안됨 -> 저번 시간에 공유했던 해시코드 오버라이드 or Enum 사용해야함
        */
//        Map<String, List<Transaction>> transactionByCurrencies = new HashMap<>();
//
//        for (Transaction transaction : transactions){
//            Currency currency = transaction.getCurrency();
//            String currencyValue = currency.getValue();
//            List<Transaction> transactionsForCurrency = transactionByCurrencies.get(currencyValue);
//
//            if (transactionsForCurrency == null){
//                transactionsForCurrency = new ArrayList<>();
//                transactionByCurrencies.put(currencyValue, transactionsForCurrency);
//            }
//            transactionsForCurrency.add(transaction);
//        }
//        transactionByCurrencies.entrySet().forEach(System.out::println);
    }
}
