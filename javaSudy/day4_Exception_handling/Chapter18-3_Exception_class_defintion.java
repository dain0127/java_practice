/* Chapter 18. 예외 처리(Exception handling)
 * 18-3. 프로그래머가 정의한 예외 클래스, 그리고 직접 throw하기.
 * 
 * 프로그래머가 직접 Exception 클래스를 상속해서 예외처리 할 수 있다.
 * 이때 프로그래머는 throw로 직접 예외를 던져서 처리할 수 있다.
 * 자세한 문법은 아래 참고.
 */

import static java.lang.System.out;

import java.util.InputMismatchException;
import java.util.Scanner;

class ReadAgeExecption extends Exception{
    ReadAgeExecption(int age){
        //부모 class인 Exception의 생성자에 원하는 문자열을 넘기면,
        //Exception의 getMessage()의 출력 문자열이 된다.
        super("(age)"+ age +"가 0보다 작습니다.");
    }
}

class test{
    //throw 명시. 두개 이상의 클래스도 던진다고 명시 가능.
    static int readAge() throws ReadAgeExecption, InputMismatchException{
        Scanner sc = new Scanner(System.in);
        out.print("age : ");
        int age = sc.nextInt();

        //예외를 던지는 데이터를 프로그래머가 직접 컨트롤 가능.
        if(age < 0)
            throw new ReadAgeExecption(age);
        
        return age;
    }

    public static void main(String args[]){
        try {
            int age = readAge();
        } catch (ReadAgeExecption e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e){
            out.println("숫자를 입력해주세요.");
        }
    }
}