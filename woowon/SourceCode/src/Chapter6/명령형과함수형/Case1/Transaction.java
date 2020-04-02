package Chapter6.명령형과함수형.Case1;

public class Transaction {
    private final int year;
    private final int value;
    private final Currency currency; // 통화

    public Transaction(int year, int value, Currency currency) {
        this.year = year;
        this.value = value;
        this.currency = currency;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "year=" + year +
                ", value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }
}
