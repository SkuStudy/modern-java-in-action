##Chapter6 스트림으로 데이터 수집

####1. 컬렉터 
######[요약](https://12bme.tistory.com/468)

- ####collectors에서 제공하는 메서드 기능
    - 스트림 요소를 하나의 값으로 리듀스하고 요약 (다양한 계산을 수행)
    
    - 요소 그룹화(다수준으로 그룹화하거나 각각의 결과 서브그룹에 추가로 리듀싱 연산을 적용할 수 있도록 다양한 컬렉터를 조합)
    
    - 요소 분할 (한개의 인수를 받아 불리언을 반환하는 함수)
   

   
####2. 리듀싱과 요약
 - 요약 연산 
   <pre>
    <code>IntsummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));</code>
   </pre>
   아래 클래스를 이용하여 모든 정보를 수집 할 수있다.
    - IntSummaryStatistics (메서드: summarizingInt())
    - LongSummaryStatistics (메서드: summarizingLong())
    - DoubleSummaryStatistics (메서드: summarizingDouble())
    
 
- 문자열 연결
    <pre>
    <code>String shortMenu = menu.stream().map(Dish::getName).collect(joining());</code></pre>
    
    - joinging : 모든 문자열을 하나의 문자열로 연결해서 반환
    
    <pre>
    <code>.collect(joining(", ")); //joining()에 ","를 넣어 스트링을 구분 할 수 있게 했다.</code></pre>

- 범용 리듀싱 요약 연산
    - 모든 컬렉터는 reducing 팩토리 메서드로도 정의할 수 있다.
    -------------
        reducing 메서드는 잘못 사용하면 실용성 문제도 발생
        
- 자신의 상황에 맞는 최적의 해법 선택

        스트림 인터페이스에서 직접 제공하는 메서드를 이용하는 것에 비해 컬렉터를 이용하면 코드가 복잡
        코드가 복잡한 대신 재사용과 커스터마이즈 가능성을 제공, 높은 수준의 추상화와 일반화를 얻음
        
>    - get(), orElse()에 대해
>   
>    
>      Optional<String> optVal = Optional.of("abc");
    
________________________
   
>    1. String str1 = optVal.get();<br>
>   // optVal에 저장된 값을 반환. null이면 예외발생<br>
>    2. String str2 = optVal.orElse("");<br>
>  // optVal에 저장된 값이 null이면 ""를 반환
>    3. String str3 = optVal.orElseGet(String::new);<br>
>  // null을 대체할 값을 반환하는 람다식 지정<br>
>    4. String str4 = optVal.orElseThrow(NullPointerException::new);<br>
>  // null일 때 지정된 예외를 발생
    
####3. 그룹화
- 자바 8의 함수형을 이용하면 가독성 있는 한 줄의 코드로 그룹화를 구현 가능

   > - Collectors.groupingBy를 이용하여 쉽게 메뉴를 그룹화
    
  >        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
   >  
    이것을 **분류 함수** 라고 부른다.
    ( 람다 표현식으로 필요한 로직을 구현할 수도 있다. )
    
- 다수준 그룹화

        groupingBy 메서드에 내부 groupingBy를 하여 두 수준으로 스트림 항목 그룹화가능
        
- 서브그룹으로 데이터 수집

         groupingBy로 넘겨주는 컬렉터의 형식은 제한이 없다. groupingBy 컬렉터에 두번째 인수로 counting 컬렉터를 전달해서 메뉴에서 요리의 수를 종류별로 계산할 수 있다.
   
####4. 분할
 
- partitionedMenu 를 통해 참과 거짓으로 분류를 한다.
           
    - 분할의 장점 : 참, 거짓 두가지 요소의 스트림 리스트를 모두 유지한다는 것이 장점이다.
                코드가 더 간결하고 효과적이다. 
                
####5. Collector 인터페이스

- supplier 메서드 : 새로운 결과 컨테이너 만들기

        빈 누적자 인터페이스를 만드는 파라미터가 없는 함수

- accumulator 메서드 : 결과 컨테이너에 요소 추가하기

        리듀싱 연산을 수행하는 함수를 반환
        반환 값은 void
        요소를 탐색하면서 적용하는 함수에 의해 누적자 내부상태가 바뀐다 (누적자가 어떤 값인지 단정x )
        
- finisher 메서드 : 최종 변환값을 결과 컨테이너로 적용하기
        
        finisher메서드는 스트림 탐색을 끝내고 누적자 객체를 최종  결과로 변환하면서 누적 과정을 끝낼때 호출할 함수를 반환
        
- combiner메서드 : 두 결과 컨테이너 병합

       comviner은 스트림의 서로 다른 서브파트를 병렬로 처리할 때 누적자가 이 결과를 어떻게 처리할지 정의
 
     - 스트림을 분할해야 하는지 정의하는 조건이 거짓으로 바뀌기 전까지 원래 스트림을 재귀적으로 분할
     - 각 요소에 리듀싱 연산을 순차적으로 적용해서 서브스트림을 병렬로 처리
     - combiner메서드가 반환하는 함수로 모든 부분결과를 쌍으로 합침
       
       
- characteristics 메서드
    
        스트림을 병렬로 리듀스할 것인지 그리고 병렬로 리듀스한다면 어떤 최적화를 선택해야 할지 힌트를 제공한다.
      
    - characteristics는 다음 세 항목을 포함하는 열거형  
        1. UNORDERED : 리듀싱 결과는 스트림 요소의 방문 순서나 누적 순서에 영향을 받지 않는다.
        2. CONCURRENT : 다중 스레드에서 accumulator 함수를 동시에 호출 할 수 있으며 이 컬렉터는 스트림의 병렬 리듀싱을 수행할 수 있다.
        3. IDENTITY_FINISH : finisher 메서드가 반환하는 함수는 단순히 identity를 적용할 뿐이므로 이를 생량할 수 있다.