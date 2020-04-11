# Chapter8

## 컬렉션 API 개선

## 1. 컬렉션 팩토리

    List<String> friends =
     Arrays.asList("Raphael","Olivia","Thibaut");

고정 크기의 리스트를 만들었으므로 갱신할 순 있지만 새 요소를 추가하거나 요소를 삭제할 수 없다.

- 요소를 추가하려 하면 Unsupported OperationException이 발생

---

List는 내부 구조가 배열로 만들어져 있다.

따라서 asList()를 사용해서 반환되는 List도 배열을 갖게 된다.

이때 asList()를 사용해서 List객체를 만들 때 새로운 배열 객체를 만드는 것이 아니라 원본 배열의 주소 값을 가져오게 된다.

 ⇒ 새로운 메모리 공간을 만드는 것 x

고정 크기의 배열 주소 값을 가져와 List를 만드는 개념

---

**Unsupported OperationException** : 내부적으로 고정된 크기의 변환할 수 있는 배열로 구현 되었기 때문에 발생

- 리스트 팩토리

    List<String> freinds = 
    	List.of("Raphael","Olivia","Thibaut");

List.of 팩토리 메서드를 이용하여 간단하게 리스트 생성 가능

    friends.add("Chih-Chun");

리스트에 요소를 추가하게 된다면 Unsupported OperationException 발생

- 컬렉션이 의도치 않게 변하는 것을 막음(값을 변경 할 수 없다.)
    - asList는 set으로 변경 o
    - List.of는 set으로 변경 x
- null요소를 금지하여 의도치 않은 버그 방지

- 집합 팩토리

    Set<String> friends =
    	 Set.of("Raphael","Olivia","Thibaut");

- 고유의 요소만 포함 할 수 있다

    Set<String> friends =	
     Set.of("Raphael","Olivia","Olivia");

- 중복되어 있는 요소를 제공해 집합을 만들려하면 IllegalArgumentExceptino 발생

- 맵 팩토리

Map.of 팩토리 메서드에 키와 값을 번갈아 제공하는 방법으로 맵을 만들수 있다.

    Map<String,Integer> ageOfFriends = 
    	Map.of("Rapheal", 30, "Olivia", 25, "Thibaut", 26);

10개 이하의 키와 값 쌍을 가진 맵을 만들 때 유용

    Map<String, Integer> ageOfFriends
     = Map.ofEntries(entry("Raphael",30),
    								 entry("Olivia", 25),
    								 entry("Thibaut",26));

10개 이상의 맵에서는 Map.Entry<K,V> 객체를 인수로 받으며 가변 인수로된 Map.ofEntries 팩토리 메서드를 이용하는 것이 좋다.

## 2. 리스트와 집합 처리

자바8에서 List, Set 인터페이스에 추가된 메서드

- removeIf : 프레디케이트를 만족하는 요소를 제거

        List<String> referenceCodes = new ArrayList<>(Arrays.asList("a12","C14","b13","1A"));
        referenceCodes.removeIf(n->Character.isDigit(n.charAt(0)));

- replaceAll : UnaryOperator 함수를 통해 요소 변경

        referenceCodes.replaceAll(code-> Character.toUpperCase(code.charAt(0))+code.substring(1));

- sort : 리스트 정렬

## 3. 맵 처리

- forEach 메서드

        ageOfFriends.forEach((friend,age)-> System.out.println(friend+" is "+age+" years old"));

- 정렬 메서드

    맵 항목의 키와 값을 기준으로 정렬할 수 있다.

    - Entry.comparingByValue
    - Entry.comparingByKey

        ageOfFriends.entrySet()
        	.stream()
        	.sorted(Map.Entry.comparingByKey())
        	.forEachOrdered(System.out::println);

- getOrDefault 메서드

    요청한 맵에 키의 존재 여부에 따라 어떤 값을 반환할지 결정

        System.out.println(ageOfFriends.getOrDefault("Olivia","Matrix"));
        System.out.println(ageOfFriends.getOrDefault("Lee","Matrix"));

- 계산 패턴

    computeIfAbsent : 제공된 키에 해당 값이 없으면, 키를 이용해 새 값을 계산하고 맵에 추가

    computeIfPresent : 제공된 키가 존재하면 새 값을 계산하고 맵에 추가 (null이 아닐 때만 새 값을 계산)

    compute : 제공된 키로 새 값을 계산하고 맵에 저장

- 삭제 패턴

        favouriteMovies.remove(key, value);

- 교체 패턴

    맵의 항목을 바꾸는데 사용할 수 있는 두 개의 메서드가 맵에 추가

    - replaceAll
        - List의 replaceAll과 비슷한 동작 수행
        - BiFuntion을 적용한 결과로 각 항목의 값을 교체

            BiFuntion 서로 다른 타입의 2개의 인자를 받아 또 다른 타입으로 반환한다.

                favouriteMovies.replaceAll((friend,movie)→movie.toUpperCase());

    - Replace
        - 키가 존재하면 맵의 값을 바꾼다.
        - 키가 특정 값으로 매핑되었을 때만 값을 교체하는 오버로드 버전도 존재

- 합침

    두 개의 맵을 합칠 때 사용

    - putAll()

        중복된 키가 없다면 코드가 잘 작동

        ⇒ 중복이 있으면 합친 키의 값이 덮어씌워짐

            everyone.putAll(friends);

    - merge()

        merge를 이용하여 값을 합치거나 변경할 수 있다.

            friends.forEach((k,v) ->
                            everyone2.merge(k,v,(movie1, movie2) -> movie1+" & "+movie2));

## 4. 개선된 ConcurrentHashMap

ConcurrentHashMap은 내부 자료 구조의 특정 부분만 잠궈 동시 추가, 갱신 작업을 허용한다. 

- ConcurrentHashMap 연산
    - forEach : 각 쌍에 주어진 액션을 수행
    - reduce : 모든 쌍을 제공된 리듀스 함수를 이용해 결과로 합침
    - search : 널이 아닌 값을 반환할 때까지 각 쌍에 함수를 적용

    연산에 제공한 함수는 계산이 진행되는 동안 바뀔 수 있는 객체, 값, 순서 등에 의존하지 않아야 한다.

- 계수(mappingCount)

    맵의 매핑 개수를 반환

    매핑 개수가 int의 범위를 넘어서는 이후의 상황을 대처할 수 있다.

- 집합뷰(keySet)

    ConcurrentHashMap을 집합 뷰로 반환

    ⇒ HashMap 키 값을 집합으로 변환