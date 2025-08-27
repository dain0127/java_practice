/* Chapter 18. 예외 처리(Exception handling)
 * 18-2 Exception. 그것이 예외처리 대상이다.
 * 예외 클래스에는 세가지가 있다. (모두 Throwable을 상속받는다.)
 * 1) Error : 본질적인 문제가 일어난 것. (하드웨어, 운영체제적이다.). 예외 처리 대상이 아니다.
 * 2) RuntimeException (-> Exception): 주로 개발자의 실수로 나타나는 예외. 예외 처리보다는 코드 수정의 대상이다.
 * 3) Exception : 외부 환경(DB, network 연결 여부)에 의해서 나타나는 예외. 예외 처리 대상.
 * 
 * Exception이 일어날 것 같은 method는 "반드시" 예외 처리를 해줘야한다.(try-catch) (안그러면 compile error를 낸다.)
 * 혹은, 예외 처리를 할 것임을 명시해야한다. (throw로 명시)
 */

import static java.lang.System.out;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.RuntimeException;
import java.lang.classfile.BufWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.Exception;


class test{
    //throw 키워드로, 어떤 예외 데이터를 던질건지 명시
    //메소드에 알맞은 예외를 던질 것임을 명시하지 않으면 컴파일 에러가 나와버림.
    static void fun2() throws IOException{
        Path file = Paths.get(".\\test.txt");
        BufferedWriter writer = null;
        writer = Files.newBufferedWriter(file); //IOException 발생 가능
        writer.write('A'); //IOException 발생 가능
        writer.write('B'); //IOException 발생 가능

        if(writer != null)
            writer.close(); //IOException 발생 가능
    }

    //throw 키워드로, 어떤 예외 데이터를 던질건지 명시
    static void fun1() throws IOException{
        fun2();
    }

    public static void main(String args[]){
        //18-2-1 Exception. 그것이 예외처리 대상이다.
        try {
            fun1();
        } catch (IOException e) {
            System.out.println("=======IOException=======");
            System.out.println(e.getMessage());
        }


    }
}