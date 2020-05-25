# Chapter16

## CompletableFuture: 안정적 비동기 프로그래밍

Future의 구현 CompletableFuture이 비동기 프로그램에 얼마나 큰 도움을 주는지 설명

## Future의 단순 활용

미래의 어느 시점에 결과를 얻는 모델에 활용 할 수 있도록 Future인터페이스 제공

- Future
    - 비동기 계산을 모델링하는데 Future 이용
    - 계산이 끝났을 때 결과에 접근할 수 있는 참조를 제공
    - 시간이 걸릴 수 있는 작업을 Future내부로 설정하면 호출자 스레드가 결과를 기다리는 동안 다른 작업을 수행 가능

### Future 제한

선형기능을 사용 할 수 있도록 자바8에서 CompletableFuture 클래스 제공한다.

- [ ]  동기 API : 메서드를 호출한 다음에 메서드가 계산을 완료할 때까지 기다렸다가 메서드가 반환되면 호출자는 반환값으로 계속 다른 동작을 수행한다.노ㅔㅐ

    ⇒ 블록 호출

- [ ]  비동기 API : 메서드가 즉시 반환되며 끝내지 못한 나머지 작업을 호출자 스레드와 동기적으로 실행될 수 있도록 다른 스레드에 할당한다.

    ⇒ 비블록 호출

## 비동기 API 구현

CompletableFuture : 다른 스레드를 만든 다음에 오래 걸리는 계산 결과를 기다리지 않고 결과를 포함할 Future 인스턴스를 바로 반환

### 에러 처리 방법

completeExceptionally 메서드를 이용해서 CompletableFuture 내부에서 발생한 예외를 클라이언트로 전달

### 팩토리 메서드 supplyAsync로 CompletableFuture만들기

CompletableFugure를 직접 만들었지만 getPriceAsync 메서드를 다음처럼 간단하게 만들 수 있다.

```java
public Future<Double> getPriceAsync(String product){
	return CompletableFuture.supplyAsync(()-> calculatePrice(product));
}
```

⇒ 팩토리 메서드 supplyAsync로 CompletableFuture를 만들 수 있다.

## 비블록 코드 만들기

전에 배운 스트림을 이용하여 동작을 구현할 수 있다.

```java
public List<String> findPrices(String product){
	return shops.stream()
				.map(shop->String.format("%s price is %.2f",
										shop.getName(), shop.getPrice(product)))
				.collect(toList());
}
```

하지만 검색의 결과는 4초보다 조금 더 걸린다.

### 병렬 스트림으로 요청 병렬화하기

병렬 스트림을 이용해서 순차 계산을 병렬로 처리해서 성능을 개선할 수 있다.

```java
public List<String> findPrices(String product){
	return shops.parallelstream()
				.map(shop->String.format("%s price is %.2f",
										shop.getName(), shop.getPrice(product)))
				.collect(toList());
}
```

### CompletableFuture로 비동기 호출 구현

supplyAsync로 CompletableFuture를 만들 수 있다.

```java
public List<String> findPrices(String product){
	List<CompletableRuture<String>>priceFutures = 
				shops.stream()
				.map(shop->CompletableFuture.supplyAsync(
										() -> shop.getName() + "price is " +
										shop.getPrice(product)))
				.collect(Collectors.toList());
	return priceFutures.stream()
		.map(CompletableFuture::join)
		.collect(toList());
}
```

### 더 확장성이 좋은 해결 방법

CompletableFuture은 다양한 Executor를 지정할 수 있다는 장점이 있다.

스레드 풀의 크기를 조절하는 등 애플리케이션에 맞는 최적화된 설정을 만들 수 있다.

### 커스텀 Executor 사용하기

- [ ]  스레드 풀 크기 조절
    - 스레드 풀이 너무 크면 CPU와 메모리 자원을 서로 경쟁하느라 시간을 낭비할 수 있다.
    - 스레드 풀이 너무 작으면 CPU의 일부 코어는 활용되지 않을 수 있다.

- [ ]  데몬 스레드
    - 자바 프로그램이 종료될 때 강제로 종료될 수 있다.

## 비동기 작업 파이프라인 만들기