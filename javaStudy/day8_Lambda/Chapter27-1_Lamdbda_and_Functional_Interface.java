/*
 * Chapter 27 람다(Lambda)
 * 27-0 람다 소개하기
 * 27-1 람다와 함수형 인터페이스
 * 27-2 자바에서 제공하는 함수형 인터페이스
 * 
 * 
 * 27-1 람다와 함수형 인터페이스
 * 
 * 27-1-1 람다 표현식의 문법
 * 람다란, 하나의 메소드(기능)을 정의해서 전달해야하는 상황에 유용하다.
 * 
 * 다시 말해, 필드와 메소드가 하나로 묶인 구조체로서의 클래스가 필요한 것이 아니라.
 * 그때그때 필요한 기능으로 대체할 하나의 메소드가 필요할때 사용한다. (이를 함수형 프로그래밍이라 한다.)
 * 
 * 이전에 람다의 개념에 대해 살펴봤다면,
 * 이번에는 람다의 표현식에 관한 문법적 규칙을 살펴볼 것이다.
 * 
 * 매개변수가 하나일때, 둘일때, 없을때 유의하며,
 * 반환값이 없을때, 있을때 유의할 규칙이 있다.
 * 
 * 미리 요약하자면,
 * 소괄호의 생략은. 매개변수가 하나일때만 가능하다.
 * 중괄호의 생략은. 한줄일때, 그 또한 return이 없을때 가능하다.
 * 또한 return의 생략은, 값을 남기는 expression을 적는것으로 가능하다.
 * 
 * 
 * 
 * 27-1-2 함수형 인터페이스 (functional interface)
 * 함수형 인터페이스란, 정의해야하는 추상메소드가 하나인 인터페이스를 일컫는다.
 * 
 * 함수형 인터페이스임을 컴파일러에게 확인하도록 지시하는 @FunctionalInterface 어노테이션이 있다. 
 * 실용 예시는 코드를 참조하여라.
 * 
 * 끝.
 * 
 */


import static java.lang.System.out;

//String -> void
@FunctionalInterface
interface Printable{
    public void print(String s);
}


//(int, int) -> int
@FunctionalInterface
interface Calculable{
    public int calculate(int a, int b);
}

//void -> T
@FunctionalInterface
interface Gettable<T>{
    public T get();
}

class test{
    public static void main(String[] args) {
        /* ===== 람다 표현식의 문법 ===== */
        //String -> void
        Printable p1 = (String str) -> {out.println(str);}; //줄임 없는 표현
        Printable p2 = (str) -> {out.println(str);}; //매개변수 형 생략
        Printable p3 = (str) -> out.println(str); //중괄호 생략(한줄로 쓸거면 가능)
        Printable p4 = str -> out.println(str); //소괄호 생략(매개변수 하나면 가능)

        p1.print("one");
        p2.print("two");
        p3.print("three");
        p4.print("four");

        //(int, int) -> int
        Calculable cAdd1 = (int a, int b) -> {return a+b;}; //줄임 없는 표현
        Calculable cSub1 = (a, b) -> {return a-b;}; //매개변수 형 생략
        Calculable cMul1 = (a, b) -> a*b; //return문 생략. 이로인한 중괄호 생략. (expression로 남겨진 값으로 반환)
        //가장 흔히 쓰는 방식.

        p1.print("a+b = "+cAdd1.calculate(1, 2));
        p1.print("a-b = "+cSub1.calculate(4, 2));
        p1.print("a*b = "+cMul1.calculate(3, 4));
        out.println();

        //void -> T
        //매개변수가 없으면 비어있는 소괄호로 표현.
        Gettable<String> sGetter1 = () -> "hello";
        Gettable<Integer> iGetter1 = () -> 3+4;
        Gettable<Calculable> cGetter1 = () -> {return (a,b) -> a/b;}; //이것 또한 가능하다.
        //결국 인스턴스를 임시로 만들어서. 그걸 반환값으로 전달하는것에 불과하기 때문이다.
        Gettable<Calculable> cGetter2 = () -> (a,b) -> a%b;

        String str1 = sGetter1.get();
        out.println("str1: "+str1);

        int num1 = iGetter1.get();
        out.println("num1: " + num1);

        Calculable cal1 = cGetter1.get();
        int result1 = cal1.calculate(10, 3);
        out.println("result1: " + result1);

        Calculable cal2 = cGetter2.get();
        int result2 = cal2.calculate(10, 3);
        out.println("result2: " + result2);        
        out.println();
    }
}