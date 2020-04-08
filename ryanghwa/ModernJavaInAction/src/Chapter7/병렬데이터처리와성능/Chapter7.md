# Chapter7

## 병렬 데이터 처리와 성능

자바 7은 쉽게 병렬화를 수행하면서 에러를 최소화할 수 있도록 포크/조인 프레임워크를 제공한다.

## 1. 병렬 스트림

parallelStream을 호출하면 병렬 스트림이 생성된다.

- 아래는 두 수를 더하는 BinaryOperator로 리듀싱 작업 수행한 코드

    //n을 인수로 받아서 1부터 n까지 모든 숫자의 합을 반환하는 메서드
    public long sequentialSum(long n){
    	return Stream.iterate(1L, i-> +1)
    						.limit(n)
    						.reduce(0L,Long::sum);
    }

- n의 숫자가 커진다면 연산을 병렬로 처리 하는 것이 좋다
- 병렬 스트림을 사용한다면 "몇개의 쓰레드를 사용해야하는지", "숫자는 어떻게 생성할지" 등의 걱정없이 모든 문제를 쉽게 해결할 수 있다.

- parallel메서드를 호출하여 병렬 수행한 코드

    public long sequentialSum(long n){
    	return Stream.iterate(1L, i-> +1)
    						.limit(n)
    						.parallel()  //스트림을 병렬 스트림으로 변환
    						.reduce(0L,Long::sum);
    }

- 마지막으로 리듀싱 연산으로 생성된 부분 결과를 다시 리듀싱 연산으로 합쳐서  전체 스트림의 리듀싱 결과를 도출

---

- parallel을 호출하면 연산이 병렬로 수행해야 함을 의미하는 불리언 플래그가 설정
- sequential을 호출하면 병렬 스트림을 순차 스트림으로 변경가능

---

### 스트림 성능 측정

자바 마이크로벤치마크 하니스(JMH) 라이브러리를 이용하여 벤치마크 구현

ㅇ

---

- 병렬 프로그래밍을 오용하면 오히려 전체 프로그램의 성능이 더 나빠질 수도 있다.
- 코어 간 데이터 전송 시간보다 훨씬 오래 걸리는 작업만 병렬로 다른 코어에서 사용하는 것이 바람직

---

### 병렬 스트림의 올바른 방법

- 확신이 서지 않으면 직접 측정
- 자동 박싱과 언박싱은 성능을 크게 저하 시킬 수 있는 요소

     ⇒   박싱 동작을 피할 수 있도록 기본형 특화 스트림(IntStream, LongStream, DoubleStream)을 제공한다. 

- 순차 스트림보다 병렬 스트림에서 성능이 떨어지는 연산이 있다.

    ⇒ 요소 순서가 상관 없다면 비정렬 스트림에 limit를 호출한느 것이 더 효율적

- 스트림에서 수행하는 전체 파이프라인 연산 비용을 고려
- 소량의 데이터에서는  병렬 스트림이 도움 되지 않는다.
- 스트림을 구성하는 자료구조가 적절한지 확인해라
- 중간 연산이 스트림의 특성을 어떻게  바꾸는지에 따라 분해 과정의 성능이 달라질 수 있다.
- 최종 연산의 병합 과정 비용을 살펴라

### 포크/조인 프레임 워크

코어 개수만큼  병렬화된 태스크로 작업 부화를 분할하면 각각의 태스크는 같은 시간에 종료될 것이라 생각

⇒ 하지만 복잡한 시나리오가 사용되면 각각의 서브태스크의 작업완료 시간이 달라짐

- 이러한 문제를 해결하기 위해 작업 홈치기 기법 사용

⇒  각각의 쓰레드는 자신에게 할당된 테스크를 포함하는 이중 연결 리스트를 참조

⇒  작업이 끝날 때마다 큐의 헤드에서 다른 태스크를 가져와 작업

일이 끝난 쓰레드는 다른 쓰레드 큐의 꼬리에서 작업을 홈쳐온다.

### spliterator 인터페이스

- spliterator : 분할 반복자라는 의미

    public interface Spliterator<T>{
    	boolean tryAdvance(Consumer<? super T> action);
    	Spliterator<T> trySplit();
    	long estimateSize();
    	int characteristics();
    }

- T는 Spliterator에서 탐색하는 요소의 형식
- tryAdvance : 순차적으로 하나씩 소비, 탐색할 요소가 남아 있으면 참 반환
- trySplit : Spliterator의 일부 요소를 분할, 두 번째 Spliterator를 생성
- estimateSize : 탐색해야 할 요소 수 정보 제공

- 특성
1. ORDERED : 정해진 순서가 있다.
2. DISTINCT : 중복되는 요소는 false 반환
3. SORTED : 정의된 정렬 순서에 따름
4. SIZED : 정확한 값 반환
5. NON-NULL : 모든 요소 null이 아님
6. IMMUTABLE : 불변
7. CONCURRENT : 여러 스레드 동시에 고칠수 있음
8. SUBSIZED : SIZED 특성을 가짐