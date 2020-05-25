# Chapter15

## CompletableFuture와 리액티브 프로그래밍 컨셉의 기초

소프트웨어 개발 방법을 획기적으로 뒤집는 두 가지 추세

1. 애플리케이션을 실행하는 하드웨어와 관련있는 것
2. 애플리케이션을 어떻게 구성하는가와 관련있는 것

- 최근 독립적으로만 동작하는 웹사이트나 네트워크 애플리케이션을 찾아보기 힘듬

    ⇒  앞으로는 다양한 소스의 컨텐츠를 가져와서 사용자가 삶을 풍요롭게 만들도록 합치는 매시업 형태가 될 가능성이 크다

- [ ]  동시성을 필요로 하는 상황, 생산성을 극대화 할 수 있도록 코어를 바쁘게 유지하는 것이 목표라면 결과를 기다리는 스레드를 블록함으로써 연산 자원을 낭비하는 일을 피해야한다.

### 1. 동시성을 구현하는 자바 지원의 진화

다양한 웹 서비스를 이용하고 이들 정보를 실시간으로 조합해 사용자에게 제공하거나 추가 웹 서비스를 통해 제공하는 종류의 애플리케이션을 개발하는데 필수적인 기초 모델과 툴킷을 제공  ⇒ 리액티브 프로그래밍

### 스레드와 높은 수준의 추상화

스트림을 이용해 스레드 사용 패턴을 추상화 할 수 있다.

스트림으로 추상화하는 것은 디자인 패턴을 적용하는 것과 비슷하지만 쓸모없는 코드가 라이브러리 내부로 구현되면서 복잡성도 줄어든다는 장점도 더해진다.

### Executor와 스레드 풀

Executor와 스레드 풀을 통해 스레드의 힘을 높은 수준으로 끌어올리는 자바 프로그래머가 태스크 제출과 실행을 분리할 수 있는 기능을 제공했다.

- 스레드의 문제

    자바 스레드는 직접 운영체제 스레드에 접근한다.

    운영체제 스레드를 만들고 종료하려면 비싼 비용을 치러야 하며 더욱이 운영체제 스레드의 숫자는 제한이 되어 있는 것이 문제

- 스레드 풀 그리고 스레드 풀이 더 좋은 경우

    ExecutorService는 태스크를 제출하고 나중에 결과를 수집할 수 있는 인터페이스를 제공

    newFixedThreadPool 같은 팩토리 메서드 중 하나를 이용해 스레드 풀을 만들어 사용 할 수 있다.

    ```java
    ExcutorService newFixedThreadPool(int nThreads)
    ```

    1. nThreads(워커 스레드라고 불림)는 포함하는 ExecutorService를 만들고 이를 스레드 풀에 저장
    2. 스레드 풀에서 사용하지 않은 스레드로 제출된 태스크를 먼저 온 순서대로 실행
    3. 태스크 실행이 종료되면 스레드 풀로 반환

    ### 장점

    - 하드웨어에 맞는 수의 태스크를 유지함과 동시에 수 천개의 태스크를 스레드 풀에 아무 오버해드 없이 제출할 수 있다는 점
    - 큐의 크지 조정, 거부 정책, 태스크 종류에 따른 우선순위 등 다양한 설정을 할 수 있다.
    - [ ]  프로그래머는 태스크를 제공하면 스레드가 이를 실행

- 스레드 풀 그리고 스레드 풀이 나쁜 이유

    거의 모든 관점에서 스레드를 직접 사용하는 것보다 스레드 풀을 이용하는 것이 바람직하지만 두가지 사항을 주의

    1. k스레드를 가진 스레드 풀은 오직 k만큼의 스레드를 동시에 실행할 수 있다. ⇒ 초과로 제출된 태스크는 큐에 저장되며 이전에 태스크 중 하나가 종료되기 전까지는 스레드에 할당하지 않는다.
        - [ ]  블복 할 수 있는 태스크는 스레드 풀에 제출하지 말아야 한다는 것

    2. 프로그램을 종료하기 전에 모든 스레드 풀을 종료하는 습관을 갖는 것이 중요

        ⇒ 풀의 워커 스래드가 만들어진 다음 다음 다른 태스크 제출을 기다리면서 종료되지 않은 상태일 수 있다.

- 스레드의 다른 추상화: 중첩되지 않은 메서드 호출

    태스크나 스레드가 메서드 호출 안에서 시작 - 스레드 생성과 join()이 한 쌍처럼 중첩된 메서드 호출 내에 추가됨 ⇒ 엄격한 포크/조인

    시작된 태스크를 내부 호출이 아니라 외부 호출에서 종료하도록 기다리는 좀 더 여유로운 방식의 포크 / 조인을 사용해도 비교적 안정적

    비동기 메서드 : 메서드가 반환된 후에도 만들어진 태스크 실행이 계속되는 메서드

    - [ ]  메서드를 사용할 때 위험성
    - 스레드 실행은 메서드를 호출한 다음의 코드와 동시에 실행되므로 데이터 경쟁 문제를 일으키지 않도록 주의
    - 기존 실행 중이던 스레드가 종료되지 않은 상황에서 자바의 main() 메서드가 반환되면 안전하지 못함
        - 애플리케이션을 종료하지 못하고 모든 스레드가 실행을 끝낼 때까지 기다린다.
        - 애플리케이션 종료를 방해하는 스레드를 강제종료 시키고 애플리케이션을 종료한다.

        ⇒ 잊고서 종료를 못한 스레드에 의해 애플리케이션이 크래시 될 수 있다.

        ⇒ 외부 데이터의 일관성이 파괴 

        **애플리케이션에서 만든 모든 스레드를 추적하고 애플리케이션을 종료하기 전에 스레드 풀을 포함한 모든 스레드를 종료한는 것이 좋다.**

        디스크의 데이터 일관성을 파괴하지 않는 동작을 수행할 때 유용하게 활용

        main()메서드는 모든 비데몬 스레드가 종료될 때까지 프로그램을 종료하지 않고 기다린다.

    - 스레드에 무엇을 바라는가?

        프로그램을 작은 태스크 단위로 구조화하는 것이 목표

        (하지만 태스크 변환 비용을 고려해 너무 작은 크기는 아니어야 한다.)

### 동기API와 비동기API

두가지 단계로 병렬성을 이용할 수 있다.

1. 외부 반복(명시적 for 루프)을 내부 반복(스트림 메서드 사용)으로 바꿔야한다.
2. 스트림에 parallel() 메서드를 이용하므로 자바 런타임 라이브러리가 복잡한 스레드 작업을 하지 않고 병렬로 요소가 처리되도록 할 수 있다.
- 내부 반복의 장점 : 런타임 시스템은 사용할 수 있는 스레드를 더 정확하게 알고 있다는 것

```java
public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        int x = 1337;
        Result result = new Result();

        Thread t1 = new Thread(()-> {result.left = f(x);});
        Thread t2 = new Thread(()-> {result.right = g(x);});
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(result.left+result.right);
    }

    private static class Result{
        private int left;
        private int right;
    }
}
```

Runnable 대신 Future API 인터페이스를 이용해 코드를 더 단순화 할 수 있다.

```java
public class ExecutorServiceExample{
	public static void main(String[] args)
		throws ExecutionException, InterruptedException {
		
		int x = 1337;
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Future<Integer> y = executorSevice.submit(()->f(x));
		Future<Integer> z = executorSevice.submit(()->g(x));
		
		System.out.println(y.get() + z.get());
	
		executorService.shutdown();
	}
}
```

여전히 명시적인 submit 메서드 호출 같은 불필요한 코드로 오염되었다.

문제의 해결은 비동기 API라는 기능으로 API를 바꿔서 해결할 수 있다.

---

첫번째 방법인 자바의 Future를 이용하면 이 문제를 조금 개선할 수 있다.

자바5에서 소개된 Future는 자바8의 CompletableFuture로 이들을 조합할 수 있게 되면서 더욱 기능이 풍부

### Future형식 API

API는 그대로 유지, g를 그대로 호출하며서 f에만 Future를 적용

큰 프로그래밍에서 두가지 이유로 이런 방식을 사용x

1. 다른 상황에서는 g에도 Future 형식이 필요할 수 있으므로 API형식을 통일하는 것이 바람직하다.
2. 병렬 하드웨어로 프로그램 실행 속도를 극대화하려면 여러 작은 하지만 합리적인 크기의 태스크로 나누는 것이 좋다.

### 리액티브형식 API

f,g의 시그니처를 바꿔서 콜백 형식의 프로그래밍을 이용하는 것

```java
void f(int x, IntConsumer dealWithResult);
```

f가 값을 반환하지 않았는데 프로그램이 동작?

⇒ 결과가 준비되면 이를 람다로 호출하는 태스크를 만드는 것

```java
public class CallbackStyleExample {
    public static void main(String[] args) {
        int x = 1337;
        Result result = new Result();

        f(x, (int y)->{result.left = y;
            System.out.println((result.left+result.right));
        });
        g(x, (int z)->{result.right = z;
            System.out.println((result.left+result.right));
        });
    }
    private static class Result{
        private int left;
        private int right;
    }
    private static void f(int x, IntConsumer dealWithResult) {
        dealWithResult.accept(Functions.f(x));
    }
    private static void g(int x, IntConsumer dealWithResult) {
        dealWithResult.accept(Functions.g(x));
    }
}
```

- 하지만 결과가 달라지는 문제 발생

    f와 g의 호출의 합계를 정확하게 출력하지 않고 상황에 따라 먼저 계산된 결과를 출력

    락을 사용하지 않고 값을 두번 출력할 수 있을 뿐더러 때로는 

    +에 제공된 두 연산자가 println이 호출되기 전에 업데이트 될 수 있다.

- 문제를 보완하는 두가지 방법
    1. if-then-else를 이용해 적절한 락을 이용해 콜백이 모두 호출 되었는지 확인한 다음 println을 호출해 원하는 기능을 수행
    2. 리액티브 형식의 API는 보통 한 결과가 아니라 일련의 이벤트에 반응하도록 설계가 되었으므로 Future를 이용하는 것이 더 적절

리액티브 형식의 프로그래밍으로 메서드f와 g는 dealWithResult 콜백을 여러번 호출

---

- [ ]  리액티브 형식의 비동기API는 자연스럽게 일련의 값을 처리
- [ ]  Future 형식의 API는 일회성의 값을 처리

- API는 명시적으로 스레드를 처리하는 코드에 비해 사용 코드를 더 단순하게 만들어주며 높은 수준의 구조를 유지할 수 있게 도와준다.
- API를 잘 활용하면 애플리케이션의 활용성이 크게 향상된다.
- 효율적으로 하단의 시스템을 활용할 수 있다는 장점을 추가로 제공

### 잠자기는 해로운 것으로 간주

어떠한 일에 일정 속도로 제한되어 일어나는 상황의 애플리케이션을 만들 때 자연스럽게 sleep() 메서드를 사용할 수 있다.

스레드는 잠들어도 여전히 시스템 자원을 점유한다.

스레드를 단지 몇 개 사용하는 상황에서는 큰 문제가 아니지만 스레드가 많아지고 그 중 대부분이 잠을 잔다면  문제가 심각해진다.

```java
//A
work1();
Thread.sleep();
work2();
```

```java
//B
public class ScheduledExecutorServiceExample {

  public static void main(String[] args) {
      ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

      work1();
      scheduledExecutorService.schedule(ScheduledExecutorServiceExample::work2, 10, TimeUnit.SECONDS);

      scheduledExecutorService.shutdown();
  }

  public static void work1() {
    System.out.println("Hello from Work1!");
  }

  public static void work2() {
    System.out.println("Hello from Work2!");
  }

}
```

- A 코드

    스레드 풀 큐에 추가되며 나중에 차례가 되면 실행

    코드가 실행되면 워커 스레드를 점유한 상태에서 아무것도 하지 않고 10초 잔다.

    그리고 깨어나서 work2()를 실행한 후 작업 종료하고 워커 스레드를 해제

- B 코드

    work1()을 실행하고 종료

    work2()가 10초 뒤에 실행될 수 있도록 큐에 추가

- 차이점

    A가 자는 동안 귀중한 스레드 자원을 점유

    반면 B는 다른 작업이 실행될 수 있도록 허용

스레드의 제한이 없고 저렴하다면 코드 A와 B는 같다

스레드에 제한이 있고 저렴하지 않다면 B형식이 좋다.

### 현실성 확인

모든 동작을 비동기 호출로 구현한다면 병렬 하드웨어를 최대한 활용할 수 있다.

하지만 현식적으로 '모든 것은 비동기'라는 설계원칙을 어겨야한다.'

(모든 API를 비동기로 만드는 것을 따지지 말고 개선된 동시성 API를 사용해보길 권장)

### 비동기 API에서 예외는 어떻게 처리되는가?

비동기 API에서 호출된 메서드의 실제 바디는 별도의 스레드에서 호출되며 이때 발생한 에러는 호출자의 실행 범위와는 관계없는 상황이 된다.

Future를 구현한 CompletableFuture에서는 런타임 get()메서드에 예외를 처리할 수 있는 기능을 제공하며 예외에서 회복할 수 있도록 exceptionally()같은 메서드도 제공

리액티브 형식의 비동기 API에서는 return 대신 기존 콜백이 호출되므로 예외가 발생했을때 실행될 추가 콜백을 만들어 인터페이스를 바꿔야한다.

(리액티브 API에 여러 콜백을 포함해야한다.)

### 박스와 채널 모델

동시성 모델을 가장 잘 설계하고 개념화하려면 그림이 필요하다.

⇒ 박스와 채널 모델이라고 부른다.

```java
int t = p(x);
System.out.println(r(q1(t),q2(t)));
```

⇒ 겉보기엔 깔끔해 보이는 코드, 자바가 차례로 q1,q2를 호출하기 때문에 병렬성의 활용과 거리가 있다.

```java
int t=q(x);
Future<Integer> a1 = executorService.submit(()->q1(t));
Future<Integer> a2 = executorService.submit(()->q2(t));
System.out.println(r(a1.get(),a2.get()));
```

⇒ p와 r을 Future로 감싸지 않았다.

- [ ]  CompletableFuture와 콤비네이터를 이용해 문제를 해결

## CompletableFuture와 콤비네이터를 이용한 동시성

동시 코딩 작업을 Future 인터페이스로 생각하도록 유도한다는 점이 Future 인터페이스이 문제다.

일반적으로 Future는 실행해서 get()으로 결과를 얻을 수 있는 Callable로 만들어진다.

⇒ 하지만 CompletableFuture는 실행할 코드 없이 Future를 만들 수 있도록 허용하며 complete()메서드를 이용해 나중에 어떤 값을 이용해 다른 스레드가 이를 완료할 수 있고 get()으로 값을 얻을 수 있도록 허용 

```java
public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int x = 1337;

        CompletableFuture<Integer> a = new CompletableFuture<>();
        executorService.submit(() -> a.complete(f(x)));
        int b = g(x);
        System.out.println(a.get() + b);

        executorService.shutdown();
    }
```

⇒ f(x)의 실행이 끝나지 않은 상황에서 get()을 기다려야하므로 프로세싱 자원을 낭비할 수 있다. 

- [ ]  자바 8 의 CompletableFuture를 이용하면 이 상황을 해결 할 수 있다.

## 발행-구독 그리고 리액티브 프로그래밍

리액티브 프로그래밍은 시간이 흐르면서 여러 Future같은 객체를 통해 여러 결과를 제공

- 업스트림

    메서드로 값을 전달

- 다운스트림

    메서드로 값을 호출

### 플로 인터페이스의 개념

- 압력

    ex) 매초마다 수천개의 메시지가 onNext로 전달

- 역압력

    ex) 출구가 추가될 공의 숫자를 제한하는 것

> 리액티브 시스템

런타임 환경이 대응하도록 전체 아키텍처가 설계된 프로그램을 가리킨다.

반응성, 회복성, 탄력성 세가지 속성으로 요약 할 수 있다.

> 리액티브 프로그래밍

메세지 주도 속성을 반영한다.