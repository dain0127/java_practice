/*
 * Chapter 29 스트림(stream) 라이브러리. (스트림 2)
 * 
 * 해당 챕터는 스트림의 생성, 중간 연산, 최종 연산의 라이브러리이다.
 * 간단한 소개 이후. 실습할 것이다.
 * 1) 스트림의 생성과 연결
 * 2) 스트림의 중간 연산
 * 3) 스트람의 최종 연산
 * 
 * 이 세가지로 나뉘어진다.
 * 
 */

import static java.lang.System.exit;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

class test{
    public static void main(String[] args) {
        /* ============ 1) 스트림의 생성과 연결 ================ */
        out.println("/* ============ 1) 스트림의 생성과 연결 ================ */");
        /* ------------ 스트림의 생성 ---------------- */
        //Chapter 29에서 설명한 stream 생성 방법 두가지.
        //1) array.   Arrays.stream()로 호출
        //용도 : 이미 존재하는 array에 대해서 유용함.
        int[] intArr1 = {1,2,3,4,5};
        Arrays.stream(intArr1) //array의 스트림 생성. Arrays의 static 메소드. stream(T [])을 호출한다.
        .sorted() //중간 연산자 
        .forEach(out::print); //최종 연산자
        out.println();

        //2) Collection.   collectionName.stream()로 호출
        //용도 : 이미 존재하는 Collection에 대해서 유용함.
        Set<Character> charSet1 = new HashSet<Character>(Arrays.asList('A','C','Z','D'));
        charSet1.stream() //Collection의 스트림 생성. collecion에 존재하는 디폴트 메소드. stream()을 호출한다.
        .sorted()
        .forEach(out::print);
        out.println();

        out.println();

        //3) Stream.of()  스트림 생성에 필요한 데이터를 직접 전달.
        //용도 : 일반적으로 사용할 수 있음.
        Stream.of(1,2,3).filter(n -> n%2==0).forEach(out::print);
        out.println();
        Stream.of("hello","bye","welcome").filter(s->s.length() >=5).forEach(s -> out.print(s+ " ")); 
        out.println();
        Stream.of(Arrays.asList("BB","A","CCC")).sorted().forEach(out::print); 
        out.println();

        out.println();

        //기본 자료형 특화 클래스
        //IntStream, DoubleStream... 등. 마찬가지로 오토 박싱 및 언박싱 연산을 생략하기 위한, 특화 클래스가 존재한다.
        //정수형 클래스같은 경우,
        //public static range(int startIndex, int endExclusive)
        //public static rangeClosed(int startIndex, int endExclusive)
        //가 있어 데이터 생성에 대해서 편의를 더해준다.
        IntStream iStream1 = IntStream.of(3,2,4,1);
        out.println("sum : " + iStream1.sum());
        DoubleStream dStream1 = DoubleStream.of(3.,2.,4.,1.);
        out.println("sum : " + dStream1.reduce(0.,Double::max));
        LongStream lStream1 = LongStream.of(3L,2L,5L,10L);
        out.println("sum : " + lStream1.sum());
        out.println();

        IntStream.range(0,10).forEach(n -> out.print(n + " "));
        out.println();
        IntStream.rangeClosed(0,10).forEach(n -> out.print(n + " "));
        out.println();
        out.println();


        //병렬 스트림으로의 전환
        List<Integer> li1 = Arrays.asList(1,3,5,6,2,7);
        Stream<Integer> iStm1 = li1.stream();

        BinaryOperator<Integer> biOper1 = Integer::max;
        
        iStm1.parallel();//일반 스트림에서, 병렬 스트림으로 전환.
        int result1 = iStm1.reduce(0,biOper1);
        out.println("result1 : "+ result1);
        out.println();

        /* ------------ 스트림의 연결 ---------------- */
        Stream<String> strStm1 = Stream.of("hello","yes");
        Stream<String> strStm2 = Stream.of("bye","no");

        Stream.concat(strStm1, strStm2).forEach(s->out.print(s+ " "));
        out.println();

        /* ============ 2) 스트림의 중간 연산 ================ */
        out.println("/* ============ 2) 스트림의 중간 연산 ================ */");
        //---------------flatMap()-----------------
        /*
         * map에는 단순히 map이 있을 뿐만 아니라,
         * flatMap이라는 종류의 메소드 또한 존재하다.
         * 
         * 이전의 Optional 클래스에서도 봤다시피, flatMap은 반환형이 포장된 클래스 그 자체를 반환한다.
         * 즉, Stream을 반환한다.
         * 
         * flatMap()의 중요한 장점은, map()이 1대1 변환만이 가능했던 것과 달리,
         * flatMap()은 1대 다의 매핑을 가능하게 해준다.
         * 
         * 자세한건 아래 예제를 참고하라.
         */

        class ReportCard{
            private int kor;
            private int eng;
            private int math;

            public ReportCard(int k, int e, int m){
                this.kor = k;              
                this.eng = e;              
                this.math = m;              
            }

            public int getKor(){return this.kor;}
            public int getEng(){return this.eng;}
            public int getMath(){return this.math;}
        }

        Stream<ReportCard> rStm1 = Stream.of(new ReportCard(100, 90, 70));
        Stream<ReportCard> rStm2 = Stream.of(new ReportCard(60, 60, 80));
        Stream<ReportCard> allRStm1 = Stream.concat(rStm1, rStm2);
        
        IntStream allIntStm1 = allRStm1.flatMapToInt(r -> IntStream.of(r.getKor(),r.getEng(),r.getMath()));
        double avgResult1 = allIntStm1.average().getAsDouble();
        out.println("avg : " + avgResult1);
        out.println();

        //--------------sort()---------------
        //마찬가지로 Comparable<T> 를 상속한 클래스에 대한 sort 연산이 있고,
        //직접 비교 기준을 제시하는. Comparator(T o1, T o2) 인스턴스를 같이 넘기는 방식이 있다.
        
        
        //Comparable<T>를 상속한 클래스에 대한 정렬
        Stream.of("AAA","C","BB")
        .sorted()
        .forEach(s -> out.print(s+ " "));
        out.println();

        //Comparator<T>의 인스턴스에 있는 compare정렬 기준을 통해 정렬
        Stream.of("AAA","C","BB")
        .sorted((s1,s2) -> s1.length() - s2.length())
        .forEach(s -> out.print(s+ " "));
        out.println();

        out.println();

        //--------------peek()------------------
        //forEach()의 중간연산 버전이다.
        
        IntStream.of(1,2,3,4,5)
        .peek(n->out.print(n*2+" "))
        .sum();
        out.println();
        out.println();


        /* ============ 3) 스트림의 최종 연산 ================ */
        out.println("/* ============ 3) 스트림의 최종 연산 ================ */");

        //sum, count, average, min, max
        out.println("---------sum(), count(), average(), min(), max()---------");
        //수 관련 스트림(IntStream, DoubleStream, LongStream 등만 가능)
        int iResult1 = IntStream.of(1,3,5,7,9)
        .sum();
        out.println("sum : " + iResult1);

        long lResult1 = IntStream.of(1,3,5,7,9)
        .count();
        out.println("count : " + lResult1);

        double dResult1 = IntStream.of(1,3,5,7,9)
        .average().orElse(0.);
        out.println("average : " + dResult1);

        iResult1 = IntStream.of(1,3,5,7,9)
        .min().orElse(100);
        out.println("min : " + iResult1);

        iResult1 = IntStream.of(1,3,5,7,9)
        .max().orElse(0);
        out.println("max : " + iResult1);

        out.println();
        
        //forEach()
        out.println("---------forEach()---------");
        IntStream.of(1,3,5,7,9)
        .forEach(n->out.print(n+"\t"));
        out.println();

        out.println();

        //allMatch(), anyMatch(), noneMatch()
        out.println("---------allMatch(), anyMatch(), noneMatch()---------");
        Consumer<Boolean> con = out::println;
        boolean boolResult1;

        boolResult1 = IntStream.of(0,1,2)
        .allMatch(n -> n>=0);
        con.accept(boolResult1);

        boolResult1 = IntStream.of(0,1,2)
        .anyMatch(n -> n==1);
        con.accept(boolResult1);

        boolResult1 = IntStream.of(0,1,2)
        .noneMatch(n->n>100);
        con.accept(boolResult1);
        out.println();

        //collect().
        out.println("---------collect()---------");
        //스트림은 한번 사용되면, 다시는 되돌릴 수 없다.
        //그렇기 때문에 중간 연산, 최종 연산 이전에 스냅샷 개념으로,
        //'현재' 스트림의 데이터를 원하는 저장소에 저장하도록 해준다.

        //시그니처는 다음과 같다.
        //<R> R collect(Supplier<R> sup, BiComsumer<R, ? super T> accumulator, BiComsumer <R, R> combiner)
        //sup는 저장할 저장소를 생성하는 기능을 정의받는다.
        //accumulator는 저장소 R에 T를 저장하는 기능을 정의받는다.
        //combiner는 저장소 R1과 R2를 합치는 기능을 정의받는다.

        //3번째 인자의 존재 이유는 collect가 병렬 스트림에서 다르게 작동하기에, 시그니처의 각 파라미터가 이와 같은 역할을 하는 것이다.
        //(reduce() 를 상기하면 된다.)


        //순차 스트림
        List<Integer> iList1 = 
        Stream.of(1,2,3,4,5)
        .filter(n -> n%2 == 1) //홀수만 통과
        .collect(ArrayList<Integer>::new, (c,e)->c.add(e) ,(c1, c2)->c1.addAll(c2));

        out.println(iList1);

        //병렬 스트림
        List<Integer> iList2 =
        Stream.of(1,2,3,4,5)
        .parallel() //병렬 스트림으로 변경
        .filter(n -> n%2 == 1) //홀수만 통과
        .collect(ArrayList<Integer>::new, (c,e)->c.add(e), (c1,c2)->c1.addAll(c2));

        out.println(iList2);
    }
}