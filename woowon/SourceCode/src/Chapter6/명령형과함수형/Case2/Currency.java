package Chapter6.명령형과함수형.Case2;

public enum Currency {
    KOREA("한국", "KRW"),
    America("미국", "USD"),
    Japan("일본", "JPY");

    private final String country;
    private final String code;

    Currency(String country, String code) {
        this.country = country;
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
