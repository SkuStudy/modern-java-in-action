package Chapter7.커스텀스플리터레이터;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) {
        String SENTENCE = "남이 던진 돌의 의미는 무엇일가요? " +
                "그 돌은 어떻게보면 의미있는 기회일 수 있지만 " +
                "그 기회를 저버리는 독단적인 행동이 아닐까요? " +
                "남들에게 보여지는 것은 위기일 수 있지만 그 위기를 기회로 승화시키는 것과도 비슷한 맥락이라고 볼 수도 있구요. " +
                "또한 남들이 무심코 흘려버리는 것을 조금 더 다른관점과 시각으로 바라보는 창의력일 수도 있겠죠?";

        System.out.println("반복형으로 단어를 세는 메서드");
        long start1 = System.nanoTime();
        System.out.println("FOUND " + countWordsIteratively(SENTENCE) + " words");
        long end1 = System.nanoTime();
        System.out.println("1. 실행 시간 : " + (end1 - start1) / 1000000000.0 + "초");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);

        System.out.println("함수형으로 단어를 세는 메서드");
        long start2 = System.nanoTime();
        System.out.println("FOUND " + countWords(stream) + " words");
        long end2 = System.nanoTime();
        System.out.println("2. 실행 시간 : " + (end2 - start2) / 1000000000.0 + "초");

    }

    // 반복형
    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c: s.toCharArray()){  // 문자열의 모든 문자를 하나씩 탐색
            if(Character.isWhitespace(c)){
                lastSpace = true;
            }
            else {
                if (lastSpace) counter ++; // 문자 탐색하면서 공백 문자를 만나면 지금까지 탐색한 문자를 단어로 간주(공백 제외)
                lastSpace = false;
            }
        }
        return counter;
    }

    // 스트림의 리듀싱 연산으로 구현 - 함수형
    public static int countWords(Stream<Character> stream){
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }
}
