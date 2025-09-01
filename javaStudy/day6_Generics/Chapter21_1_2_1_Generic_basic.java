/*
 * Chapter 21 제네릭 1
 * 본 챕터에서는, 제네릭의 문법적인 이해도 겸하여,
 * 제네릭이 나타나게 된 배경. 제네릭의 용도. 를 중심으로 서술하고자 한다. (교재에서도 그 점을 강조하고있다.)
 * 
 * 21-1-1 제네릭이 필요한 이유
 * 21-1-2 제네릭의 기본 문법
 *      21-1-2-1 제네릭의 기본 문법
 *      ★★★21-1-2-2 제네릭의 타입 인자 제한 (extneds 키워드)★★★
 * 
 * 21-1-2 제네릭의 기본 문법
 * 21-1-2-1 제네릭의 기본 문법
 * -제네릭 클래스의 정의 (가독성)(안정성)
 *      이전 코드에 서술함. ClassName<T>{...}으로 표현.
 * -다중 매개변수 기반 제네릭 클래스의 정의
 *      class Dbox<L, R>{...}와 같은 방식으로 가능하다. (가독성)
 * -기본 자료형은 타입 인자값으로 불가능, 하지만 Wrapper 클래스는 가능.
 *      int형을 인스턴스화 할 수 있는 Integer형이 필요한 이유가 된다.
 * -다이아몬드 기호(<>), 타입 인자의 생략.
 *      만약에 복잡한 타입 인자값이 넘겨졌으면, <>는 편리하고 '간결'하다. (가독성)
 * -제네릭 타입 자체를 타입 인자값으로 전달.
 *      제네릭 타입도 결국 타입이다. 당연히 타입 매개변수에 전달이 된다.
 * 
 * 
 * -제네릭 메소드의 정의 (가독성)(안정성)
 *      public static ☆<T>☆ void funName (int n){...} 따위로 표현.
 *      즉, 함수의 시그니처 바로 앞에 <T> (혹은 <T1, T2>)로 명시하여 제너릭 메소드임을 명시한다.
 * 
 */

 
import static java.lang.System.out;

//-다중 매개변수 기반 제네릭 클래스의 정의
class Dbox<L, R>{
    private L left;
    private R right;

    public void set(L l, R r){
        this.left = l;
        this.right = r;
    }

    @Override
    public String toString(){
        return left + " & " + right;
    }
}

class Box<T>{
    private T ob;
    public void set (T o){
        this.ob = o;
    }
    public T get(){
        return this.ob;
    }

    @Override
    public String toString() {
        return ob.toString();
    }

}

// -제네릭 메소드의 정의
class BoxFactory{
    //특정 메소드만 제네릭을 적용해, tpye T 일반화.
    public static <T> Box<T> makeBox(T ob){
        Box<T> box = new Box<T>();
        box.set(ob);
        return box;
    }
}


class Chapter21_1_2_1_Generic_basic{
    public static void main(String[] args){
        //기본자료형 대신, Wrapper class 사용.
        //auto boxing과 auto unboxing 덕분에 int나 double '처럼' 사용할 수 있다.
        Dbox<Integer, Double> dBox1 = new Dbox<>(); //다이아몬드 기호 사용. 참조변수의 타입 인자값을 그대로 받도록, 컴파일러가 해석.
        dBox1.set(10, Math.PI);
        out.println(dBox1);
        out.println();

        //-제네릭 타입 자체를 타입 인자값으로 전달.
        //제네릭 타입도 결국 타입이다. 당연히 타입 매개변수에 전달이 된다.
        Box<String> stringBox1 = new Box<>();
        Box<Box<String>> doubleBox1 = new Box<>(); 
        Box<Box<Box<String>>> tripleBox1 = new Box<>(); //이럴때 다이아몬드 기호가 도움이 된다. 코드의 간결성.
        
        stringBox1.set("hello");
        doubleBox1.set(stringBox1);
        tripleBox1.set(doubleBox1);
         
        out.println(tripleBox1);
        out.println();

        // -제네릭 메소드의 정의
        Box<Integer> boxI1 = BoxFactory.<Integer>makeBox(10);
        Box<Double> boxD1 = BoxFactory.makeBox(Math.E); //<...> 생략 가능.
        //"전달되는 인자 타입"을 통해 컴파일러가 어떤 메소드를 호출할지 판단해줌.
        out.println(boxI1);
        out.println(boxD1);
        out.println();
    }
}