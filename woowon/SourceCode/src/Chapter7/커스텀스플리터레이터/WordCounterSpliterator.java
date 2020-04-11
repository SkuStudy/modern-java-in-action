package Chapter7.커스텀스플리터레이터;

import java.util.Spliterator;
import java.util.function.Consumer;

// 병렬로 수행하기 위한 Spliterator 클래스
public class WordCounterSpliterator implements Spliterator<Character> {
    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    // 스트림을 탐색하면서 WordCounter의 accumulate 메서드를 적용
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++)); // 현재 문자를 소비
        return currentChar < string.length();        // 소비할 문자가 남아있으면 true를 반환
    }

    // 분할하는 로직으로 Spliterator에서 가장 중요한 메서드
    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < 10){  // 충분히 작아졌으면 null을 반환
            return null;
        }
        for (int splitPos = currentSize / 2 + currentChar;         // 파싱할 문자열의 중간을 분할 위치로 설정
             splitPos < string.length() ; splitPos++)  {
            if(Character.isWhitespace(string.charAt(splitPos))){   // 공백이 나올 때까지 분할 위치를 뒤로 이동
                Spliterator<Character> spliterator =
                        new WordCounterSpliterator(string.substring(currentChar, splitPos)); // 처음부터 공백이 나온 분할 위치까지 문자열을 파싱할 새로운 WordCounterSpliterator를 생성
                currentChar = splitPos;                            // 이 WordCounterSpliterator의 시작 위치를 분할 위치로 설정
                return spliterator;                                // 공백을 찾았고 문자열을 분리했으므로 루프를 종료
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
