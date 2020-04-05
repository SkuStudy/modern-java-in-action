package Chapter6.명령형과함수형;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class Main {
    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(2011 ,300, Currency.KOREA),
                new Transaction(2012 ,1000, Currency.AMERICA),
                new Transaction(2011 ,400, Currency.JAPAN),
                new Transaction(2012 ,710, Currency.AMERICA),
                new Transaction(2012 ,700, Currency.AMERICA),
                new Transaction(2012 ,950, Currency.KOREA)
        );

        // 명령형 프로그래밍 버전
        Map<Currency, List<Transaction>> transactionByCurrencies = new HashMap<>();

        for (Transaction transaction : transactions){
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionByCurrencies.get(currency);

            if (transactionsForCurrency == null){
                transactionsForCurrency = new ArrayList<>();
                transactionByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }

        System.out.println("- 명령형 프로그래밍 버전 -");
        transactionByCurrencies.entrySet().forEach(System.out::println);

        // 함수형 프로그래밍 버전
        Map<Currency, List<Transaction>> transactionByCurrenciesEx = transactions.stream()
                .collect(groupingBy(Transaction::getCurrency));

        System.out.println("- 함수형 프로그래밍 버전 -");
        transactionByCurrenciesEx.entrySet().forEach(System.out::println);
    }

    private static void test1(List<?> test) {

    }
}
