/*
 * Chapter 27 람다(Lambda)
 * 27-0 람다 소개하기
 * 27-1 람다와 함수형 인터페이스
 * 27-2 자바에서 제공하는 함수형 인터페이스
 * 
 * 
 * 
 * 27-0 람다 소개하기
 * 본 챕터는 그저 람다의 개념을 익명클래스의 일부로서,
 * 컴파일러와 합의하여 가독성을 높인 문법이 장점으로 작용하는걸 보이고자 한다.
 * 
 * 1) 익명클래스와 같은 위치에서, 람다식으로 표현한 코드
 * 2) 람다식을 인자값으로 넘기는 코드
 * 둘로 나뉜다.
 * 
 * 자세한 설명은 아래 코드를 읽고 실습하여라.
 * 
 */


import static java.lang.System.out;

interface Printable{
    public void print(String s);
}

class test{
    public static void main(String[] args) {
        /* ===== 1. 익명클래스와 람다의 개념적 유사성 ===== */

        //해당 코드는, Printable 인터페이스를 구현한 클래스의 인스턴스를 생성한다.
        Printable p1 = new Printable(){
            @Override
            public void print(String s){
                out.println(s+" from Anonymous Class.");
            }
        };

        //위 개념으로 말미암아, 익명클래스가 아래 코드로 단축되었다고 이해해도 좋다.
        //아래 코드는 '람다식'으로 표현한 것이며,
        //추상 메소드가 하나인 인터페이스 (이를 functional interface라 한다)를 구현한 몸체가 정의된
        //클래스의 인스턴스를 생성해너 넘겨준 것이다.
        //즉, '메소드 하나를 구현한' 익명 클래스 '처럼' 생각해도 좋다.

        //람다식으로 표현함으로서, 람다식이 필요한 최소의 정보만 남겨주었음을 주목하라.
        //Functional Interface인 탓에 구현해야할 메소드가 '유일'하며,
        //그 탓에 매개변수의 형식과 반환형마저 알고 있다.
        //그저 컴파일러의 편의를 위해, 어떤걸 매개변수로 삼는지만 표시하면 된다.

        //단, 익명클래스와 람다는 실제로 내부적으로는 다르게 동작함을 이해해야한다.
        Printable p2 = (str) -> {out.println(str + " from Lambda.");}; 

        p1.print("hello");
        p2.print("hello");
        out.println();

        /* ===== 2. 인자값으로서의 람다식 ===== */
        /*
         * 람다식(Lambda expression)은 인자값으로도 전달할 수 있다.
         * 그도 그럴것이, 매개변수로 인자값을 전달하는 것의 본질은,
         * 해당 함수 내부에서 가장 먼저 할당된 지역변수에 대한 초기화이다.
         * 
         * 따라서 함수 내부에 있는 지역변수에 알맞게 구현한 함수의 정보를 토대로한,
         * 인스턴스의 참조값을 넘긴 셈이다.
         * 
         * 그러므로, 본질은 지역(scope)의 차이일뿐, 기능적으로는 1번 코드와 같다.
         * 
         */

        //익명 클래스의 인스턴스 참조값을 인자값으로 넘기기.
        printWith(new Printable(){
            @Override
            public void print(String s){
                out.println(s+" from Anonymous Class.");
            }
        }, "bye");


        //람다식을 인자값으로 넘기기.
        printWith((s) -> {out.println(s + " from Lambda.");}, "bye");
    }

    public static void printWith(Printable p, String s){
        p.print(s);
    }
}