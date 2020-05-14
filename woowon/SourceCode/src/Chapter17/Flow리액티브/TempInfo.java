package Chapter17.Flow리액티브;

import java.util.Random;

// 현재 보고된 온도를 전달하는 자바 빈
public class TempInfo {
    public static final Random random = new Random();

    private final String town;
    private final int temp;

    public TempInfo(String town, int temp) {
        this.town = town;
        this.temp = temp;
    }

    public static TempInfo fetch(String town){
        if (random.nextInt(10) == 0) throw new RuntimeException("Error !"); // 10분의 1 확률로 가져오기 실패
        return new TempInfo(town, random.nextInt(100)); // 0~99도 임의의 온도 반환
    }

    @Override
    public String toString() {
        return
                "town='" + town + '\'' +
                ", temp='" + temp + '\'';
    }

    public String getTown() {
        return town;
    }

    public int getTemp() {
        return temp;
    }
}
