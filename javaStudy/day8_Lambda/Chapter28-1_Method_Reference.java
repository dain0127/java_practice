/*
 * Chapter28 메소드 참조와 Optional 클래스
 * 
 * 28-1 메소드 참조
 * 28-2 Optional 클래스
 *
 * 
 * 
 * 28-1 메소드 참조
 * 메소드 참조는 람다식을 어떤 '약속'에 근거하여 표현을 줄이는 방식이다.
 * 종류는 총 세가지가 있다.
 * 1. static 메소드 호출
 * 2. 인스턴스 메소드 호출(2-1. 전달인자 한 개, 2-2 전달인자 두 개)
 * 3. 생성자 호출
 * 
 * 각각의 케이스에 대해서 어떤 '약속'을 전제로 메소드 이름만 참조하는지를, 파악하고 코드를 읽고 쓸줄 알아야한다.
 * 
 * 
 * 자세한건 아래 코드 참고.
 */

import static java.lang.System.out;

import java.lang.classfile.Interfaces;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;

class test{
    public static void main(String[] args) {
        /* ============ 1. static method 참조 ============== */
        out.println("/* ============ 1. static method 참조 ============== */");
        List<Integer> li = Arrays.asList(1,3,5,7,9);
        li = new LinkedList<>(li);
        
        //Consumer<T>
        //void accept(T a);

        Consumer<List<Integer>> c1 = l -> Collections.reverse(l); //람다식.
        c1.accept(li);
        out.println(li);

        c1 = Collections::reverse; //메소드 참조.
        //우리는 해당 메소드의 유일한 인자값 하나가.
        //그대로 reverse() 메소드에 전달될 것임을 '약속'하고 해당 메소드 참조 표현을 사용하는 것이다.

        //그렇기에 최소한의 함수 정의 기능을 설명하는 람다식에서, 그저 메소드 하나의 언급으로 단축할수 있게 된다.
        //반복컨데, 이는 '약속'이 있기에 가능한 줄임이기에, 코드를 읽을때 약속을 떠올리며 기능을 해석해야한다.

        c1.accept(li);
        out.println(li);

        

        /* ============ 2-1. instance method 참조 1 (인자값 한개) ============== */
        out.println("/* ============ 2-1. instance method 참조 1 ============== */");
        class JustSort{
            public void sort(List<?> l){Collections.reverse(l);}
        }

        li = Arrays.asList(1,3,5,7,9);
        li = new LinkedList<>(li);

        /* final */ JustSort j1 = new JustSort(); //effectively final. 즉, 사실상 상수여야 한다.

        //j1 = new JustSort(); //해당 주석을 해제하면, effectively final이 아니게 되어, 아래 람다식에 컴파일 에러가 난다.
        
        Consumer<List<Integer>> c2 = l -> j1.sort(l);
        /*
         * 우리는 해당 람다식에 주목할 필요가 있다.
         * 람다식이 익명 클래스의 인스턴스를 반환한다는 개념을 상기해라.
         * 
         * 그렇다면 해당 람다식은, 익명 클래스로서 외부의 참조 변수를 가져오는 꼴이 된다.
         * 
         * 본래 그것은 허용되지 않는게 언뜻 마땅해보이나, 컴파일러는 이를 특정 조건하에 허락한다.
         * 그 조건이란, 해당 참조변수가 final 혹은 effectively final일때 가능하다는 조건이다.
         * 즉, 상수(가리키는 참조값 변경X)이거나, '사실상 상수'여야한다.
         * 
         * 여기서 '사실상 상수'라는 말의 뜻은, 해당 람다식을 사용한 이후, 값이 바뀌지 않아야함을 일컫는다.
         * 
         * 컴파일러는 해당 람다식이 정해진 함수 정의코드를 전달했음을 믿고 인스턴스를 생성한 까닭이다.
         * 
         */
        c2.accept(li);
        out.println(li);

        

        c2 = j1::sort; //인스턴스 메소드 참조 1.
        //위 설명과 같다. '약속'에 근거하여, 전달된 단일한 값이 '그대로' 해당 메소드에 전달되는 방식으로, 메소드가 정의됨을 이해하라.
        //다만. j1이 인스턴스이며, 호출된 메소드 또한, 맴버 함수임을 주목해라.        
        c2.accept(li);
        out.println(li);


        //인스턴스 메소드 참조 1. 의 또다른 예시
        //out이 static 참조 변수임을 상기하라. out이 가리키는건 엄연히 힙 영역에 실체화된 인스턴스이다.

        //참고로 forEahc문은 Collection의 defulat 메소드이며, Interable하면 가능하다.
        //내부 코드는. 예상하는 그거 맞다. 다만, 모든 요소에 accept(e)가 적용될 뿐이다.
        li.forEach(System.out::print);
        out.println();

        /* ============ 2-2. instance method 참조 2 (인자값 두개) ============== */
        out.println("/* ============ 2-2. instance method 참조 2 ============== */");
        class IBox{
            int n;
            IBox(){this.n = 0;}
            IBox(int n){this.n = n;}
            //더 큰 내용물을 반환.
            public int larger(IBox b){
                return this.n > b.n ? this.n : b.n;
            }

            public int largerInt(int n){
                return this.n > n ? this.n : n;
            }
        }

        IBox box1 = new IBox(7);
        IBox box2 = new IBox(5);

        ToIntBiFunction<IBox, IBox> bf1 = (b1, b2) -> b1.larger(b2); //람다식 참조
        int result1_1 = bf1.applyAsInt(box1, box2); // 7 vs 5
        out.println("result1_1 : " + result1_1);

        bf1 = IBox::larger; //'인스턴스' 메소드 참조
        int result1_2 = bf1.applyAsInt(box1, box2); // 7 vs 5
        out.println("result1_2 : " + result1_2);

        //ToIntBiFunction<T, U>
        //int applyAsInt (T t, U u)


        
        //--------첫번째 인자와 두번째 인자의 type이 다른 경우에도 마찬가지임을 보이는 코드 -----------//

        ToIntBiFunction<IBox, Integer> bf2 = (b, n) -> b.largerInt(n); //람다식 참조
        int result2_1 = bf2.applyAsInt(box1, 10); // 7 vs 10
        out.println("result2_1 : " + result2_1);

        bf2 = IBox::largerInt; //메소드 참조
        int result2_2 = bf2.applyAsInt(box1, 10); // 7 vs 10
        out.println("result2_2 : " + result2_2);
        out.println();

        /*
         * 인스턴스 메소드 참조 2 (2-2) 같은 경우에는. 상기한 메소드 참조와 사뭇 다른 약속을 전제한다.
         * 
         * 해당 인스턴스 메소드는, 겉보기에는 맨 처음 설명한 static 메소드의 호출과 같아보인다.
         * 하지만 적용의 대상이, 두개를 인자로 받는 메소드임을 이해하면, 다른 케이스임을 깨달을 수 있다.
         * 즉, 1이 아닌. 2-2의 케이스임을 알 수 있다.
         * 
         * 이는 코드를 읽을때 상기해야하는 사고방식이다. 
         * 
         * 해당 메소드 참조는 그럼 어떠한 약속을 전제로, 람다식을 줄였을까.
         * 
         * 이는 "첫번째 인자가 호출할 메소드이며, 두번째 인자는 여느때와 마찬가지로 그대로 인자값으로 전달한다" 이다.
         * 다시말해. 두개의 인자가 있으면, 참조한 메소드는 첫번째 인자가 호출한다는 의미이다.
         * 
         * 예를 들어,
         * large(IBOX b1, Integer n)에서
         * IBox::large는 b1.lagrge(n)을 전제한다.
         * 
         */

        //quiz 28-1
        List<String> li1 = Arrays.asList("robot", "Hello", "string","YES");
        li1 = new LinkedList<>(li1);

        Collections.sort(li1, String::compareToIgnoreCase);
        out.println(li1);
        out.println();
        
        /* ============ 3. 생성자 참조(new) ============== */
        out.println("/* ============ 3. 생성자 참조(new) ============== */");
        
        Function<char[], String> f1 = arr -> new String(arr); //람다식 참조.
        char[] chArr1 = {'H','E','L','L','O'};
        String strResult1 = f1.apply(chArr1);
        out.println("strRsult1 : " + strResult1);
        
        f1 = String::new; //생성자 참조
        char[] chArr2 = {'B','Y','E'};
        String strResult2 = f1.apply(chArr2);
        out.println("strRsult2 : " + strResult2);
        out.println();


        //--------------void 생성자, 혹은 여러 인자를 전달받는 생성자에도 적용됨을 보이는 코드 -----------------//
        //void 생성자 참조 가능. () -> new ReturnType();
        Supplier<IBox> sp1 = ()->new IBox(); //람다식으로 void 생성자 반환값 전달 기능 지정.
        IBox iBox1 = sp1.get();
        out.println("iBox1.n : " + iBox1.n);

        sp1 = IBox::new; //void 생성자 참조
        IBox iBox2 = sp1.get();
        out.println("iBox2.n : " + iBox2.n);
        out.println();




        interface ThreeFunction<T1, T2, T3, R>{
            R apply(T1 t1, T2 t2, T3 t3);
        }

        char[] chArr3 = {'W','E','L','C','O','M','E'};
        ThreeFunction<char[], Integer, Integer, String> threeF1 =
            (arr, offset, len) -> new String(arr, offset, len); //람다식으로 (char[],int,int) 생성자 반환값 전달 기능 지정.
        String strResult3 =  threeF1.apply(chArr3, 1, 4);
        out.println("strRsult3 : " + strResult3);


        threeF1 = String::new; //(char[], int, int) 생성자 지정.
        String strResult4 =  threeF1.apply(chArr3, 1, 4);
        out.println("strRsult3 : " + strResult4);

        /*
         * 생성자 참조의 해석방법은, ClassName::new를 볼 때.
         * 해당 new는 Return type에 따라 return new ReturnType()가 됨을 알 수 있다.
         * 
         * 다만, 어떤 생성자가 호출될지는, 매개변수의 type에 따라 달라진다.
         * 즉, ReturnType Fun(ParamaterTpye1, ParamaterTpye2 ,...) 에 대해서.
         * 우리는 ClassName(ReturnType)::new를 보고 다음과 같이 해석할 수 있어야한다.
         * 
         * return new ReturnType(ParamaterTpye1, ParamaterTpye2 ,...);
         */
    }
}