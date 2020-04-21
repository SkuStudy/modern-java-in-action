package Chapter11;

public class Insurance {
    private String name;  // 보험 회사에는 반드시 이름이 있다.
    public String getName(){
        return name;
    }

    public Insurance(String name) {
        this.name = name;
    }
}
