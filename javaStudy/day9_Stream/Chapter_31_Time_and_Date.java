/*
 * Chapter 31 시각과 날짜의 처리
 * 
 * 
 * 
 */

import static java.lang.System.out;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class test{
    public static long fibonacci(long n){
        if(n == 1 || n == 2)
            return 1;
        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static void main(String[] args) {
        /* ============= 1) Instant, Duration. 시간을 재는 방법 ================== */
        out.println("/* ============= 1) Instant, Duration. 시간 재는 방법 ================== */");
        //시간 재는 방법.
        //Instant는 흐르는 시간 속, 특정 '시점(시각)'을 표현하기 위한 클래스이다.
        //Duration은 '시간(시각의 차)'를 표현하기 위한 클래스이다.
        Instant start = Instant.now();
        out.println("시작 : " + start.getEpochSecond());

        out.println("hello. time is illusion.");
        
        Instant end = Instant.now();
        out.println("끝 : " + end.getEpochSecond());

        Duration time = Duration.between(start, end);
        out.println("밀리 초 단위 차 : " + time.toMillis());
        out.println();


        //순차 스트림과 병렬 스트림의 성능 차이 확인하기.
        //피보나치는 대표적인 재귀 함수로 구현 가능한 문제이다.
        //그리고 재귀 알고리즘은, 스케일이 커질 수록, 상당히 많은 시간이 걸리는 특징이 있다.
        //아래의 순차 스트림과, 병렬 스트림에서 성능이 두배가 차이가 나는 것을 알 수 있다.
        List<Integer> li1 = Arrays.asList(41,42,43,44,45);
        
        //순차 스트림
        Instant start1 = Instant.now();
        
        li1.stream()
        .map(n->fibonacci(n))
        .forEach(out::println);

        Instant end1 = Instant.now();

        Duration time1 = Duration.between(start1, end1);
        out.println("순차 스트림으로 피보나치 해결한 시간 : " + time1.toMillis());


        Instant start2 = Instant.now();

        //병렬 스트림
        li1.parallelStream()
        .map(n->fibonacci(n))
        .forEach(out::println);

        Instant end2 = Instant.now();
        
        Duration time2 = Duration.between(start2, end2);
        out.println("병렬 스트림으로 피보나치 해결한 시간 : " + time2.toMillis());

        /* =============== 2) LocalDate 날짜를 저장하기 ============== */
        out.println("/* =============== 2) LocalDate 날짜를 저장하기 ============== */");
        //LocalDate는 '날짜'를 저장하는 클래스이다.
        //Period는 날짜 단위로 '기간(날짜의 간격)'을 저장하는 클래스이다.

        //오늘
        LocalDate today = LocalDate.now();
        out.println("Today: " + today);

        //올해의 크리스마스
        LocalDate xmas = LocalDate.of(today.getYear(), 12, 25);
        out.println("크리스마스 : " + xmas);

        //올해의 크리스마스 이브
        LocalDate eve = xmas.minusDays(1);
        out.println("크리스마스 이브 : " + eve);

        //오늘부터 크리스마스까지 남은 기간
        Period left = Period.between(today, xmas);
        out.println("오늘부터 크리스마스까지 남은 기간 : " + left.getMonths()+"월 " + left.getDays() + "일");


        /* =============== 3) LocalTime 시각을 저장하기 ============== */
        //LocalTime는 '시각'을 저장하는 클래스이다. Instant와의 차이점은, 시,분,초로 저장되지 않는다는 점이다.
        //(상술했다시피)Duration은 '시간(시각의 차)'를 표현하기 위한 클래스이다.
        out.println("/* =============== 3) LocalTime 시각을 저장하기 ============== */");
        LocalTime now1 = LocalTime.now();
        out.println("지금 시각 : " + now1);

        //미팅은 2시간 30분 뒤
        LocalTime mt = now1.plusHours(2);
        mt = mt.plusMinutes(30);
        out.println("미팅 시각 : " + mt);

        Duration time3 = Duration.between(mt,now1);
        out.println("지금부터 미팅까지 걸리는 시간 : " + time3);

        /* ============== 4) LocalDateTime 날짜와 시간을 동시에 저장하기 =============  */
        out.println("/* ============== 4) LocalDateTime 날짜와 시간을 동시에 저장하기 =============  */");
        LocalDateTime now2 = LocalDateTime.now();
        out.println("지금 날짜와 시각 : " + now2);


        //빠른 항공편을 선택한 후, 지금부터 얼마나 걸리는지 날짜와 시간을 출력
        LocalDateTime now3 = LocalDateTime.of(2020, 1, 30, 10, 30);
        LocalDateTime fight1 = LocalDateTime.of(2020, 3, 20, 11, 00);
        LocalDateTime fight2 = LocalDateTime.of(2020, 4, 10, 13, 40);

        LocalDateTime myFight;

        if(fight1.isBefore(fight2))
            myFight = fight1;
        else
            myFight = fight2;

        Period p1 = Period.between(myFight.toLocalDate(), now3.toLocalDate());
        Duration d1 = Duration.between(myFight.toLocalTime(), now3.toLocalTime());

        out.println("지금부터 가장 가까운 항공편까지 걸리는 기간과 시간 : "+p1 + " :: "+d1);

        /* ============== 6) DateTimeFormatter 날짜와 시간을 출력하는 포멧을 결정하기 =============  */
        LocalDateTime now4 = LocalDateTime.now();
        
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("yy-M-d");
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("yyyy-MM-dd, H:m:s");
        DateTimeFormatter f3 = DateTimeFormatter.ofPattern("yy-M-d, hh-mm-ss");

        out.println(now4.format(f1));
        out.println(now4.format(f2));
        out.println(now4.format(f3));


        /*
         * 사족을 붙이자면, 전세계의 경도에 따른 시간대의 차이. 즉 시차의 반영을 고려한.
         * ZoneDateTime 클래스 또한 존재한다.
         * 하지만 이 클래스는 사족같아서 생략한다.
         * 
         * 궁금하면 교재 참고하거나 검색해라.
         * 
         */
    }
}