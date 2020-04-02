package Chapter6.명령형과함수형.Case1;

public class Currency {
    private final String country;
    private final String code;

    public Currency(String country, String code) {
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
