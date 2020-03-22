## 1.1 역사의 흐름은 무엇인가?

- 간단한 방식으로 코드 구현 가능
```java
    //Java 7
    Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
          @Override
          public int compare(Dish d1, Dish d2) {
            return Integer.compare(d1.getCalories(), d2.getCalories());
          }
        });
    
    //Java 8
    lowCaloricDishes.sort(Comparator.comparingInt(Dish::getCalories));
```

- 멀티코어 CPU 대중화와 같은 하드웨어적인 변화도 영향
    - `JAVA 8 이전`
        - 코어 중 하나만 사용
        - 멀티 코어를 위해 스레드를 사용
            - 관리가 어렵다
    - `JAVA 8 이후`
        - 스트림을 이용한 쉬운 병렬 실행 기법 제공

- 자바 8의 요구사항
    - 간결한 코드
    - 멀티코어 프로세서의 쉬운 활용
    - `해결`
        - 스트림 API
        - 메서드에 코드를 전달하는 기법
        - 인터페이스의 디폴트 메서드

- 스트림 API 제공 `p39`
    - DB 질의 언어 동작 방식
    - 옵티마이저
        - 비용기반 옵티마이저
            - SQL문을 처리하는데 필요한 비용이 적은 실행계획을 선택하는 방식
            - 현재 대부분의 RDBMS에서 사용하는 방식
    - [http://www.dbguide.net/db.db?cmd=view&boardUid=148218&boardConfigUid=9&boardIdx=139&boardStep=1](http://www.dbguide.net/db.db?cmd=view&boardUid=148218&boardConfigUid=9&boardIdx=139&boardStep=1)
    - [https://devmango.tistory.com/24](https://devmango.tistory.com/24)

## 1.2 왜 아직도 자바는 변화하는가?

- 자바의 장점
    - 객체지향언어
    - JVM
    - `스터디를 진행하며 Scala와 비교해보는 것도 좋을듯`

- 변화의 필요성
    - 멀티코어 컴퓨팅을 통한 빅데이터 처리 필요
    - `병렬 프로세싱 활용이 필요하다`

- **스트림 처리**
    - 파이프라인을 만드는데 필요한 메서드들 제공
    - 유닉스 명령어와 비슷

        cat file1 file2 | sort | tail -3

    - `스트림 파이프라인을 이용해 입력부분을 여러 CPU 코어에 할당`
        - 스레드 사용하지 않고 쉽게 `병렬성` 제공

- **동작 파라미터화로 메서드에 코드 전달하기**
```java
        //Java 7
        //Comparator 인터페이스의 구현체를 만들어야한다
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
              @Override
              public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
              }
            });
        
        //Java 8
        lowCaloricDishes.sort(Comparator.comparingInt(Dish::getCalories));
```

  
- 자바 8 이전
    - 메서드를 다른 메서드로 전달할 수 없다
- 왜 중요할까?
    - 스트림 API는 연산의 동작을 파라미터화할 수 있는 코드를 전달한다는 사상에 기초
- 장점은?
    - 고차함수가 가능
        - 적절한 함수만 넘겨주면 된다
        - [https://bestalign.github.io/2015/10/18/first-class-object/](https://bestalign.github.io/2015/10/18/first-class-object/)
    - 불필요한 구현이 필요 없어진다

- 병렬성과 공유 가변 데이터
    - `스트림 사용시 공유된 가변 데이터에 접근하지 않아야한다`
    - **자바 8 이전**
        - 스레드 synchronized을 이용한 Lock
            - Lock을 걸면 순차적으로 실행된다
            - `병렬이란 목적을 무력화`
        - synchronized 참고 자료
            - [https://www.slipp.net/questions/179](https://www.slipp.net/questions/179)
    - **자바 8 이후**
        - 쉽게 병렬성 활용

## 1.3 자바 함수

- 메서드 vs 함수
    - 함수는 독립적인 개념, 메서드는 클래스에 종속되어 존재

- 일급 시민
    - 런타임 때 자유롭게 값을 전달할 수 있는 것
    - 함수를 값으로 취한다
        - 스트림의 토대

- 메서드 참조
- 람다를 이용하여 함수를 값 취급
    - 직접 메서드를 정의할 수도 있지만, 이용할 수 있는 편리한 클래스나 메서드가 없을 때
    - `간결한 코드 구현이 가능하다`
- 예제
    - 코드를 인수로 넘겨줄 수 있기 때문에 중복코드가 줄어든다

## 1.4 스트림

- 컬렉션은 필수
- 컬렉션 API
    - 외부 반복
- 스트림 API
    - 내부 반복
    - 많은 요소 반복
        - 각 CPU 코어에 작업 할당
        - parallelStream()

## 1.5 디폴트 메서드와 자바 모듈

- 디폴트 메서드
    - 인터페이스에서 구현 가능

- 미래에 프로그램이 쉽게 변화할 수 있는 환경 제공
    - List.sort() 예제

- 디폴트 메소드의 다중상속 문제 해결
    - `Default 메소드의 다중상속은 가능할까?`
    - https://doohyun.tistory.com/48
    

## 1.6 함수형 프로그래밍에서 가져온 다른 유용한 아이디어
- Null 문제
- 패턴 매칭