/* Chapter 18. 예외 처리(Exception handling)
 * 18-1 예외 처리 기본 
 * 예외란. 문법적 에러(compile error)가 아니다.
 * 예외란 정상적인 프로그램 동작 중에, 개발자가 예측하지 않은 상황이 나타나는걸 예외라고 말한다.
 * 
 * 물론 그 상황이 run time error로 이어지는 경우가 있으나 (0나누기, int형에 문자 대입...),
 * 예외(exception)가 꼭 run time error가 되지는 않는다.
 * 그냥 개발자 마음에 안들면 예외를 throw해서 catch 한다음, 원하는 코드를 실행할 수도 있다.
 * 
 * 18-1 try-catch문
 * try-catch문이 없을때 : 
 * 아래 소스파일을 java로 run한 다음.
 * b에 0을 대입하면, java.lang.ArithmeticException
 * 두 변수중 아무나 문자를 대입하면, java.util.InputMismatchException
 * 라는 예외 클래스의 data가 throw 되는걸 볼 수 있다.
 * 
 * try-catch문 : 
 * C++과 같다.
 * try-catch문은 try에서 throw된 예외를 catch문에서 받을 수 있다.
 * 그리고 catch문에서 예외 클래스의 데이터를 파라미터에서 받을 수 있다.
 * 
 * 다만, java는 throw로 명시를 안해도, 자기가 알아서 exception을 던져준다.
 * 그리고 둘 이상의 예외 클래스를 catch(ExceptionName1 | ExceptionName2 e)로 표기 가능하다.
 * 
 * C++과 마찬가지로, 함수 안에서 예외가 일어나면, 호출 스택을 따라, 예외를 던지고,
 * 최종적으로 main에서도 예외가 처리 안되면, JVM이 처리하도록 한다.
 * 
 * 18-2 예외 클래스의 메소드 :
 * getMessage(), printStackTrace()
 * 실습 예제 참고.
 * 
 * 
 * 
 * 모든 클래스의 상위 클래스가 Object이듯이,
 * java.lang에서 정의한 모든 예외 클래스의 상위 클래스는 Throwable이다.
 */

import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.System.out;

class test{
    static void inputDivide(){
        Scanner sc = new Scanner(System.in);        
        int a,b;
        out.println("a/b...a : ");
        a=sc.nextInt();
        out.println("a/b...b : ");
        b=sc.nextInt();

        out.printf("%d / %d = %d\n", a, b, a/b);
        out.println("=============================");
    }

    public static void main(String args[]){
        //=========== 18-1 try-catch문 ============

        //try-catch문이 없을때.
        out.println("=========== 18-1 try-catch문 ============");
        out.println("no try-catch");
        inputDivide();

        //try-catch문이 있을때.
        try {
            out.println("try-catch");
            inputDivide();
        }
        //두 클래스 이상을 동시에 받고 싶을때 이런 식으로 사용 가능하다.
        catch (ArithmeticException | InputMismatchException e) {
            out.println(e.getMessage());
            if(e instanceof ArithmeticException)
                out.println("b is not allowed to input 0.");
            if(e instanceof InputMismatchException)
                out.println("must input integer tpye data.");
        }

        //=========== 18-2 예외 클래스의 메소드 ============     
        
        try {
            out.println("=========== 18-2 예외 클래스의 메소드 ============");
            inputDivide();
        } catch (Throwable e) {
            out.println("=====getMessage()=====");
            out.println(e.getMessage());
            out.println("=====printStackTrace()=====");
            e.printStackTrace();
        }


        out.println("Good bye~~~~");
    }

}