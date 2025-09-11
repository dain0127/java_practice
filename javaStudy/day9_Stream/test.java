/*
 * Chapter 29 스트림(stream) 개념. (스트림 1)
 * 
 * 29-1 스트림의 소개. 그리고 lazy 연산
 * 29-2 필터링과 매핑
 * 29-3 리덕션과 병렬 스트림
 * 
 * 참고로 교재에서 '스트림'은 두 챕터에 걸쳐서 진행이 된다.
 * 그리고 해당 챕터(스트림 1)에서는 '스트림'에 대한 전반적인 이야기를 얇게 한다.
 * 스트림 2는 라이브러리의 성격을 갖는다.
 * 
 * 스트림은 세단계에 걸쳐서 일어나게 되는데.
 * 1) 스트림의 생성 (array, Collection)
 * 2) 중간 연산자의 호출
 * 3) 최종 연산자의 호출
 * 
 * 스트림1 에서 개념과 스트림의 생성을 간단히 소개하며,
 * 스트림 2에서 중간 연산자와 최종 연산자에 대한 자세한 메소드의 종류와 활용은 자세히 알려주게된다.
 * 
 * 
 * 
 * stream이란 java에서 배열이나 Collection에 저장된 일련의 데이터들을,
 * '가공하기 좋은 형태'로 바꾼 것을 의미한다.
 * 이때 가공하기 좋은 형태란 체이닝 메소드를 통해, 연달아 호출하여,
 * 데이터를 파이프라인에 통과시키듯 쉽게 컨트롤이 가능하다는 말이다.
 * 
 * 즉, 스트림(Stream)은 데이터 소스를 효율적으로 탐색·가공할 수 있도록 지원하는 선언적 데이터 처리 파이프라인이다.
 * 
 *
 * Stream은 자바에서 class의 일종이며, 아래 코드를 참조하면 알겠지만.
 * Stream의 생성 -> 각종 중간 연산 -> 최종 연산. 의 형태를 거쳐 원하는 데이터에 대해서 원하는 연산이 가능하다.
 * 
 * 
 * 중간 연산은 없어도 되고, 여러개를 해도 되지만.
 * 최종 연산은 반드시 있어야한다.
 * 
 * 최종 연산이 호출되기 전에는, Stream에 예약해둔, 중간 연산도 실행되지 않는다.
 * 최종 연산이 호출되어야 실행된다. 이를 지연(lazy) 연산이라고 하며, 자세한건 코드를 참조해라.
 * 즉, 최종 연산은 파이프의 여닫이를 담당하는 '잠금 벨브' 역할을 한다.
 * 
 */

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.Box;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

class test{
    public static void main(String[] args) {
        out.println("/* ============ <29-1 스트림의 소개와 지연(lazy) 연산> =================== */");
        out.println("/* ============ 스트림을 사용하는 이유를 보여주는 코드 =================== */");
        //아래 배열의 짝수의 합을 구하는 기능을 코드로 구현한다고 해보자.
        int[] ar = {1,2,3,4,5};


        //Stream이 없을때 구현 방법.
        //5줄
        int sum1 = 0;
        for(int e : ar){
            if(e%2 == 0)
                sum1 += e;
        }
        System.out.println("sum1 : " + sum1);


        //Stream이 있을때 구현 방법. (메소드 체이닝 X)
        IntStream stm1 = Arrays.stream(ar);
        IntStream stm2 = stm1.filter(n -> n%2 == 0);
        int sum2 = stm2.sum();
        System.out.println("sum2 : " + sum2);


        //Stream이 있을때 구현 방법. (메소드 체이닝 O)
        //1줄
        int sum3 = Arrays.stream(ar).filter(n -> n%2 == 0).sum();
        out.println("sum3 : " + sum3);

        /* ================== 지연(lazy) 처리 방식 ==================== */
        out.println("/* ================== 지연(lazy) 처리 방식 ==================== */");
        //지연 처리 방식이란. 스트림이 최종 연산이 되기 전에는,
        //모든 중간 연산을 유예함을 의미한다.
        //스트림은 지연(lazy) 처리 방식을 사용한다.

        //아래 코드는 지연 처리 방식이 작동함을 보이는 코드이다.



        List<String> li2 = List.of("a","aa","bbb");
        li2 = new LinkedList<>(li2);

        //중간 연산들... 세팅만 해놓는다.
        Stream<String> tempStm1 = li2.stream().filter(s ->{
            out.println("filter : " + s);
            return s.length() > 1;
        })
        .map(s -> {
            out.println("map : " + s);
            return s.toUpperCase();            
        });
        

        out.println("다른거 하는 중...");
        out.println("다른거 하는 중...");
        out.println("다른거 하는 중...");

        tempStm1.forEach(out::println);
        out.println();
        
        /* ================== Stream 인스턴스 생성 방식 두가지 ==================== */
        out.println("/* ================== Stream 인스턴스 생성 방식 두가지 ==================== */");
        //1) array.
        int[] intArr1 = {1,2,3,4,5};
        Arrays.stream(intArr1) //array의 스트림 생성. Arrays의 static 메소드. stream(T [])을 호출한다.
        .sorted() //중간 연산자 
        .forEach(out::print); //최종 연산자

        out.println();

        //2) Collection
        Set<Character> charSet1 = new HashSet<Character>(Arrays.asList('A','C','Z','D'));
        charSet1.stream() //Collection의 스트림 생성. collecion에 존재하는 디폴트 메소드. stream()을 호출한다.
        .sorted()
        .forEach(out::print);
        
        out.println();
        
        /* ================== 29-2 중간 연산자 간단 소개 : 필터링과 매핑 ==================== */
        out.println("/* ================== <29-2 중간 연산자 간단 소개 : 필터링과 매핑> ==================== */");
        
        //간단하다. 필터링은 걸러주고. 매핑은 바꿔준다.
        //각각 Predicate와 Function 참조값을 요구하며, 알맞은 람다값을 호출하면 된다.
        //자세한건 아래 코드 참고.

        // ---------------- 실습 -----------------
        //이 String 배열에서 5글자 이상을, 사전순으로 정렬하여, 대문자로 바꿔, 출력해라.
        List<String> li1 = Arrays.asList("Hello", "bye", "changin", "apple", "comphrehand", "interprete", "difference");
        li1 = new LinkedList<>(li1);
        li1.stream()
        .filter(s -> s.length() >= 7).sorted()
        .map(s -> s.toUpperCase())
        .forEach(out::println);
        
        out.println();


        //Quiz 29-1. 1번문제. 2번문제.
        class Box<T>{
            private T ob;
            public Box(T o){this.ob = o;}
            public T get(){return this.ob;}
        }

        //1번 문제
        List<Box<String>> liBox1 = Arrays.asList(new Box<String>("hello"), new Box<String>("bye"));
        liBox1.stream()
        .map(b->b.get()) //Stream< Box<String> > -> Stream< String >
        .forEach(out::println);

        out.println();

        //2번 문제
        liBox1.stream()
        .map(b->b.get().length())
        .forEach(out::println);

        out.println();

        /* ================== 29-3 리덕션(Reduction)과 병렬 스트림(Parallel Streams) ==================== */
        out.println("/* ================== <29-3 리덕션(Reduction)과 병렬 스트림(Parallel Streams)> ==================== */");
        out.println("/* ========== 1) 리덕션(Reduction)과 reduce() ============ */");
        /*
         * 리덕션(Reduction)이란 '데이터를 축소하는 연산'을 일컫는다.
         * 
         * sum() (스트림에 있는 데이터를 전부 합한 결과를 반환)과 같은 최종연산 메소드가 리덕션이다.
         * 이를 선언적으로 사용 가능한 것이 reduce() 메소드이다.
         * 
         * reduce() 또한 최종연산자이다.
         * 
         * BinaryOperator<T> 인터페이스에 정의된 T apply(T,T)를 순차적으로 모든 요소에 적용하여,
         * 결과적으로 하나로 줄이는 방식이다.
         * 
         */

    
        //합 구하기.
        List<Integer> li3 = Arrays.asList(1,3,4,2,5);
        li3 = new LinkedList<>(li3);

        int result1 = li3.stream()
        .reduce(0,Integer::sum); //메소드 참조 2-2. 상기하여라.

        out.println("result1 : " + result1);
        out.println();

        //가장 긴 문자열 출력하기
        List<String> li4 = Arrays.asList("A","BB","CCCCC","DDD");
        li4 = new LinkedList<>(li4);

        String result2 = li4.stream()
        .reduce("",(s1, s2) -> s1.length() > s2.length() ? s1 : s2);

        out.println("result2 : " + result2);
        out.println();


        out.println("/* ========== 2) 병렬 스트림(Paralle Stream) ============ */");
        /*

         * 병렬 스트림이란. 요즘같은 멀티 코어 시대에서,
         * 여러개의 코어로 reduce 연산을 동시에 진행하여, 연산의 단계를 줄이는 방식을 사용한다.
         * 즉, 토너먼트 방식에 가깝게 만들어, O(log(N))에 가깝게 성능을 줄이려는 목적이 있다.
         * 
         */


        List<Integer> li5 = new ArrayList<>();
        Random rand1 = new Random();

        for(int i=0;i<100;i++)
            li5.add(rand1.nextInt(1000));
        
        int resultMax = li5.parallelStream()
        .reduce(-1, Integer::max);
        out.println("resultMax : " + resultMax);
    }
}