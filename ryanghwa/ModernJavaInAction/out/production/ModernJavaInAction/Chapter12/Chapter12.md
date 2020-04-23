# Chapter12

## 새로운 날짜와 시간 API

자바8에서는 지금까지의 날짜와 시간 문제를 개선하는 새로운 날짜와 시간 API를 제공한다.

다음은 자바 9릴리스 날짜인 2017년 9월 21일을 가리키는 Date 인스터스를 만드는 코드이다. 

    Date date = new Date(117, 8 ,21);

다음은 날짜 출력 결과이다.

    Thu Sep 21 00:00:00 CET 2017

결과가 직관적이지 못하다.

또한 Date 클래스의 toString으로 반환되는 문자열을 추가로 활용하기가 어렵다.

## 1. LocalDate, LocalTime, Instant, Duration, Period 클래스

java.time 패키지는 LocalDate, LocalTime,LocalDateTime, Instant, Duration, Period 등 새로운 클래스를 제공한다.

### LocalDate와 LocalTime 사용

새로운 날짜와 시간 API를 사용할 때 처음 접하게 되는 것은 LocalData다.

LocalDate 인스턴스는 시간을 제외한 날짜를 표현하는 불변 객체이다.

    LocalDate date = LocalDate.of(2017,9,21);
    int year = date.getYear();
    Month month = date.getMonth();
    int day = date.getDayofWeek();
    DayOfWeek dow = date.getDayOfWeek();
    int len = date.lengthOfMonth();
    boolean leap = date.isLeapYear();

get메서드에 TemporalField를 전달해서 정보를 얻는 방법도 있다.

TemporalField는 시간 관련 객체에서 어떤 필드의 값에 접근할지 정의하는 인터페이스이다.

ChronoField의 열거자 요소를 이용해서 원한는 정보를 얻을 수 있다.

    int year = date.get(ChronoField.YEAR);
    int month = date.get(ChronoField.MONTH_OF_YEAR);
    int day = date.get(ChronoField.DAY_OF_MONTH);

LoclaTime 클래스 예제

    LoclaTime time = LocalTime.of(13,45,20);
    int hour = time.getHour();
    int minute = time.getMinute();
    int second = time.getSecond();

- 다음처럼 정적인 메서드를 사용할 수 있다.

    LocalDate date = LocalDate.parse("2017-09-21");
    LocalTime time = LocalTime.parse("13:45:20");

### 날짜와 시간 조합

LocalDateTime은  LocalDate와 LoclaTime을 쌍으로 갖는 복합 클래스이다.

    LocalDateTime dt1 = LocalDateTime.of(2017,Month.SEPTEMBER, 21, 13, 45, 20);
    LocalDateTime dt2 = LocalDateTime.of(date, time);
    LocalDateTime dt3 = date.atTime(13, 45, 20);
    LocalDateTime dt4 = date.atTime(time);
    LocalDateTime dt5 = time.atDate(date);

### Instant 클래스: 기계와 날짜와 시간

기계의 관점에서는 연속된 시간에서 특정 지점을 하나의 큰 수로 표현하는 것이 가장 자연스러운 시간 표현 방법이다.

java.time.Instant클래스에서는 이와같은 기계적인 관점에서 시간을 표현한다.

### Duration과 Period 정의

Temporal 인터페이스는 특정 시간을 모델링하는 객체의 값을 어떻게 읽고 조작할지 정의한다.

Period 클래스의 팩토리 메서드 between을 이용하면 두 LocalDate의 차이를 확인 할 수 있다.

    Period tenDays = Period.between(LocalDate.of(2017, 9, 11),
    															LocalDate.of(2017,9,21));

---

- Duration 클래스는 두 시간 사이의 간격을 초나 나노 초 단위로 나타낸다.
- Period 클래스는 두 날짜 사이의 간격을 년/월/일 단위로 나타낸다.

---

## 2. 날짜 조정, 파싱, 포매팅

withAttribute 메서드로 기존의 LocalDate를 바꾼 버전을 직접 간단하게 만들 수 있다.

    LocalDate date1 = LocalDate.of(2017,9,21);
    LocalDate date2 = date1.withYear(2011);
    LocalDate date3 = date2.withDayOfMonth(25);
    LocalDate date4 = date3.with(Chronofield.MONTH_OF_YEAR, 2);

선언형으로 LocalDate 사용하는 방법

    LocalDate date1 = LocalDate.of(2017,9,21);
    LocalDate date2 = date1.plusWeeks(1);
    LocalDate date3 = date2.minusYear(6);
    LocalDate date4 = date3.plus(6, ChronoUnit.MONTHS);

### TemporalAdjusters 사용하기

때로는 다음주 일요일, 돌아오는 평일, 어떤 달의 마지막 날 등 좀 더 복잡한 날짜 조정 기능이 필요하다.

⇒ TemporalAdjuster를 전달하는 방법으로 문제를 해결할 수 있다.

    import static java.time.temporal.TemporalAdjuster.*;
    LocalDate date1 = LocalDate.of(2014, 3, 18);
    LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));
    LocalDate date3 = date3.with(lastDayOfMonth());

### 날짜와 시간 객체 출력과 파싱

정적 팩토리 메서드와 상수를 이용해서 쉽게 포매터를 만들 수 있다.

DateTimeFomatter를 이용해서 날짜나 시간을 특정 형식의 문자열로 만들 수 있다.

반대로 날짜나 시간을 표현하는 문자열을 날짜 객체로 만들 수 있다.

## 3. 다양한 시간대와 캘린더 활용 방법

기존의 java.util.TimeZone을 대체할 수 있는 java.time.ZoneId클래스가 새롭게 등장 했다.

날짜와 시간 API에서 제공하는 다른 클래스와 마찬가지로 ZoneId는 불변 클래스다.

### 시간대 사용하기

표준시간이 같은 지역을 묶어서 시간대 규칙 집합을 정의

ZoneRules 클래스에는 약 40개의 시간대가 있다.

    ZoneId romeZone = ZoneId.of("Europe/Rome");

지역 ID는 [지역]/[도시] 형식으로 이루어지며 IANA Time Zone Database에서 제공하는 지역 집합 정보를 사용

 

### UTC/Greenwich 기준의 고정 오브셋

UTC/GMT 를 기준으로 시간대를 표현하기도 한다.

    LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
    DffsetDateTime dateTimeInNewYork = OffsetDateTime.of(date,newYorkOffset);

### 대안 캘린더 시스템 사용하기

자바8에서는 추가로 ThaiBuddhisDate, MinguoDate, JapansesDate, HijrahDate 4개의 클래스가 캘린더 시스템을 대표한다.