/*
 * Chapter10 static keyword
 * 10-1 static 맴버변수
 * 10-2 static 메소드
 * 10-3 System.out.println()과 public static void main()의 정체
 * 10-4 static 초기화 블록
 * 10-5 static import
 * 
 * <해당 소스코드는 10-1~3에 대한 설명을 기술합니다.>
 * 
 * 10-1 static 맴버변수
 * C++이랑 같다. 클래스 전용 변수이고, 모든 인스턴스가 공유하며 쓰이고 있다.
 * (실제로 데이터 영역에 전역 변수처럼 할당이 되기도 한다.)
 * C++이랑 피상적으로 다른 문법은,
 * C++은 ClassName::static_var; 이런 식으로 접근하지만,
 * java는 ClassName.static_var; 이런 식으로 접근한다.
 * (이를 맴버변수의 접근으로 오해하면 안된다!!! 그냥 class영역에 있는 정적 변수를 가져오는 것이다!!!)
 * 
 * 10-2 static 메소드
 * 맴버함수는 '애초에' 모든 인스턴스가 동일하게 '공유'한다.(text segment)
 * 따라서 static 메소드는 다른 관점에서 바라봐야한다.
 * 
 * C++이랑 java랑 거의 비슷하다.
 * Class에서 전용으로 쓰이는 공통 유틸리티 함수를 추가하는 용도이다.
 * Class 영역에 속한 함수라, ClassName.staticMethod() (C++에서는 ClassName::staicMethod())로 접근이 가능하다.
 * 그리고 Class 전용 함수인지라, 객체가 고유하게 가지고 있는 맴버 변수에 접근이 불가능하다.
 * 
 * (그리고 C++에서는 전역 함수라는 개념이 있어, static 전역 함수, 전역 변수라는 개념이 있다. 알다시피, 소스파일로 접근 제한 하는 것.)
 * 
 * 10-3 System.out.println()과 public static void main()의 정체
 * 10-3-1 System.out.println()
 */
    import java.lang.*; //compiler가 자동 삽입하는 import문.
    
    class Example{
        public void exampleFun(){
            java.lang.System.out.println();
            //또는
            System.out.println(1); 
        }
    }
 /* 일단 System은 대문자로 시작한다. 따라서 관례적으로 'class' 이름인걸 알 수 있다.
 * 그리고 out은 System 클래스의 'static 맴버 변수'이다. (그러니깐 Sysytem.out으로 접근할 수 있는 게 아니겠는가?)
 * 
 * java.lang라는 package에 System이라는 class가 존재하기 때문에, 
 * 원칙적으로는 java.lang.System.out.println()으로 접근해야한다.
 * 
 * 하지만 컴파일러는 기본적으로 import java.lang.*을 삽입하기 떄문에 상관없다.
 * 
 * 10-3-2 public static void main()
 */
    class Run{
        //10-3. main method는 여러 class에 존재할 수 있다.
        public static void main(String args[]){
        System.out.println("it's Run's main method");
    }    
    }
 /* 런처는 JVM에게 public 그리고 static으로 선언된 main을 찾아 엔트리포인트로서 먼저 실행한다.
 * main이 엔트리 포인트인건 항상 있던 관례이다.
 * public인건 외부(운영체제)에서 실행해야하니 당연하다.
 * static은 main의 instance를 생성하기 전에 실행해야하니 당연하다.
 *  
 * 그러니 main함수가 저런 형테를 띄어야하는건 당연한 논리이다.
 * 
 * 
 * 그리고 참고해야할건...! main method는 사실 여러 class에나 들어갈 수 있다!
 * 우리가 java.exe를 실행할때, class name을 대상으로 실행하는걸 상기해라.
 * 그리고 class name을 대상으로 런처를 실행할때, 해당 class에 있는 main함수를 엔트리 포인트로 실핸하는 것이다.
 * 
 * 사실상 static 함수이기때문에, class 영역은 공간만 제공한 것일뿐, 별도의 독립된 함수라고 이해해도 괜찮다.
 * 
 */

 /*
 * ================ 실습 방법 ==================
 * 1. test class를 분석해 static 맴버변수와 static 메소드에 대해서 파악한다.
 * 2. 각각의 class에 main method가 있는걸 확인한다. (Example class 제외)
 * 3. run.bat을 읽고 실행한다. 그리고 출력문과 런처 에러를 확인하며, main method와 static에 대해서 고찰한다.
 */


//아래 코드는 10-1와 10-2의 내용을 포함한다.
class EasyClass{
    private static int static_num=0; //static 맴버 변수. 즉, class 전용 변수.
    private int feild_num;

    EasyClass(){
        feild_num=0;
    }

    void justMethod(){}

    //static 맴버함수
    //Class전용 유틸리티를 책임질때 사용.
    static void addNum(){
        static_num++;

        //객체별로 존재하는 맴버 변수에는 접근이 불가능.
        //feild_num++; //compile error.

        //마찬가지로, 맴버 함수에도 접근이 불가능.
        //justMethod(); //compile error.
    }

    public static void showStaticNumber(){
        System.out.println(static_num);
    }


    //10-3. main method는 여러 class에 존재할 수 있다.
    public static void main(String args[]){
        System.out.println("it's EasyClass's main method");
    }    
}

class test {
    //10-3. main method는 여러 class에 존재할 수 있다.
    public static void main(String args[]){
        EasyClass obj = new EasyClass();

        //EasyClass.static_num++; //C++에서 ClassName::num과 같음.
        //obj.static_num++; //객체에서도 접근 가능. 모든 객체가 공유하고 있는 변수이기 때문.
        obj.showStaticNumber();

        EasyClass.addNum();
        EasyClass.showStaticNumber();
    }
}
