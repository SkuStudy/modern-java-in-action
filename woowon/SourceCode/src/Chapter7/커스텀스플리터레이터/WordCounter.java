package Chapter7.커스텀스플리터레이터;

public class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;     // 단어 개수
        this.lastSpace = lastSpace; // 마지막 공백인지
    }

    public WordCounter accumulate(Character c){ // 문자열 하나씩 탐색
        if (Character.isWhitespace(c)){
            return new WordCounter(counter, true);
        } else {
            return lastSpace ? new WordCounter(counter + 1, false) : this;       // 문자열 하나씩 탐색하다 공백문자 만나면 지금까지 탐색한 문자를 단어로 간주
        }
    }

    public WordCounter combine(WordCounter wordCounter){
        // 두 WordCounter의 counter를 더하고, counter값만 더할것이므로 마지막 공백은 신경쓰지 않는다.
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }
}
