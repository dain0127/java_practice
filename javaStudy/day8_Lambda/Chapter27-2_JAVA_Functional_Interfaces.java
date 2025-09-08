/*
 * Chapter 27 람다(Lambda)
 * 27-0 람다 소개하기
 * 27-1 람다와 함수형 인터페이스
 * 27-2 자바에서 제공하는 함수형 인터페이스
 * 
 * 
 * 
 * 27-2 자바에서 제공하는 함수형 인터페이스
 * 자바에서는 기본적으로 제공하는 함수형 인터페이스가 있다.
 * 교재에서 소개하는건 우선 네 가지이며, java.util.function에 묶여있다.
 * 
 * Predicate<T>{boolean test(T t)}
 * Supplier<T>{T get()}
 * Consumer<T>{void accept(T t)}
 * Function<T, R>{R apply(T t)}
 * 
 * 각자 주로 사용하는 용도가 저마다 있으며, 변형본 또한 여러개 존재한다.
 * 자세한건 실습 코드 참조.
 * 
 */

import static java.lang.System.out;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

class test{
    public static void main(String[] args) {
        /*================ 1. Predicate p.test()를 위한 람다 적용 ================= */
        out.println("/*================ 1. Predicate p.test()를 위한 람다 적용 ================= */");
        //겸사겸사. Quiz 27-2.
        List<Integer> iList = new LinkedList<>();
        Random rand = new Random();
        for(int i=0;i<10;i++)
            iList.add(rand.nextInt(10));
        
        showList(n->true, iList); //전부 출력
        showList(n -> n%2 == 1, iList); //홀수만 출력
        showList(n -> n%2 == 0, iList); //짝수만 출력
        out.println();

        List<Double> dList = Arrays.asList(1.2,-3.0,3.4,-1.2,10.2);
        dList = new LinkedList<>(dList);

        showList(n -> true, dList); //전부 출력
        showList(n -> n > 0.0, dList); //0.0 보다 큰 수 출력


        //IntPredicate
        showIntList(n -> n>=0, iList); //0보다 같거나 큰 정수 출력.

        //BiPredicate
        //quiz_27-3의 답안.
        BiPredicate<String, Integer> p = (s,n) -> s.length() > n;
        if(p.test("Robot", 3))
            out.println("Robot이 길이 3을 초과합니다.");
        else
            out.println("Robot이 길이 3을 초과합니다.");
            

        if(p.test("Box", 5))
            out.println("Box이 길이 5을 초과합니다.");
        else
            out.println("Box이 길이 5을 초과합니다.");
        
        out.println();
        /*================ 2. Suppiler s.get()를 위한 람다 적용 ================= */
        out.println("/*================ 2. Suppiler s.get()를 위한 람다 적용 ================= */");

        //참고로 IntSupplier인터페이스도 있다.
        Supplier<Integer> sp = ()->{
            Random r = new Random();
            return r.nextInt(10);
        };
        List<Integer> iList3 = getIntList(sp, 10);
        out.println(iList3);

        out.println();
        /*================ 3. Consumer c.accept()를 위한 람다 적용 ================= */
        out.println("/*================ 3. Consumer c.accept()를 위한 람다 적용 ================= */");
        Consumer<String> cn = s -> out.println("String is " + s);   
        cn.accept("hello");
        cn.accept("bye");
        cn.accept("yes");
        out.println();

        //quiz 27-4
        class Box<T>{
            private T ob;
            public void set(T o){this.ob = o;}
            public T get(){return this.ob;}
        }

        BiConsumer<Box<Integer>, Integer> iBoxCon;
        BiConsumer<Box<Double>, Double> dBoxCon;
        iBoxCon = (box, i) -> box.set(i);
        dBoxCon = (box, d) -> box.set(d);

        Box<Integer> iBox = new Box<Integer>();
        Box<Double> dBox = new Box<Double>();
        
        iBoxCon.accept(iBox, 100);
        dBoxCon.accept(dBox, 10.10);
        
        out.println("iBox has " + iBox.get());
        out.println("dBox has " + dBox.get());

        out.println();
        /*================ 4. Function f.apply()를 위한 람다 적용 ================= */
        out.println("/*================ 4. Function f.apply()를 위한 람다 적용 ================= */");
        //그외 여러가지 상당한 인터페이스가 있다. 교재 참고.

        Function<String, Integer> sToIntFun = s -> s.length();
        int strLen = sToIntFun.apply("hello");
        out.println("strLen : " + strLen);
        

        /*================ 5. Collection o.removeif()에 함수형 인터페이스 써보기 ================= */
        out.println("/*================ 5. Collection o.removeif()에 함수형 인터페이스 써보기 ================= */");
        //Collection<E>에 defulat 메소드로 존재하는 removeif()메소드의 시그니처는 아래와 같다.
        //public <E> boolean removeif(Predicate<? super E> filter)
        //의미는. 해당 Predicate의 test()에 통과한 것들은 모두 지워라.

        List<Integer> iList4 = new LinkedList<>();
        for(int i=0;i<10;i++)
            iList4.add(rand.nextInt(-10, 10));

        List<Double> dList3 = new LinkedList<>();
        for(int i=0;i<10;i++)
            dList3.add(rand.nextDouble(-10, 10));
        
        //Integer랑 Double 리스트에도 통용되도록, Number로 T를 결정하였다.
        Predicate<Number> p1 = n -> n.doubleValue() < 0.0; //삭제의 조건. 음수.

        out.println(iList4);
        iList4.removeIf(p1); //음수는 모두 지워라!
        out.println(iList4);

        out.println();

        out.println(dList3);
        dList3.removeIf(p1); //음수는 모두 지워라! Double List도 가능함에 주목해라. (<? super E>)
        out.println(dList3);
        
    }



    static <T> void showList(Predicate<T> p, List<T> list){
        for(T e : list){
            if(p.test(e))
                out.print(e+ " ");
        }
        out.println();
    }

     static void showIntList(IntPredicate ip, List<Integer> list){
        for(int e : list){
            if(ip.test(e))
                out.print(e+ " ");
        }
        out.println();
    }

    static List<Integer> getIntList(Supplier<Integer> s, int length){
        List<Integer> li = new LinkedList<>();
        for(int i=0;i<length;i++)
            li.add(s.get());
        return li;
    }
}