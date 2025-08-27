/* Chapter 18. 예외 처리(Exception handling)
 * 18-4. try-with-resource
 * 
 * file을 open하고 write하는 과정에서 exception handling이 나올 수 있다.
 * 그런데 이떄 file을 close()하는 마무리 코드를 실행 못시킬 수도 있다.
 * 이때 자동으로 마무리할 수 있게 try에 resource구문을 넣어준다.
 * 자세한 문법은 아래 참조.
 * 
 * 이는 모든 resource관련 class들이 반드시 AutoClaoseable interface를 구현하도록 되어있기에 가능한 문법적 기능이다.
 * AutoClaoseable는 이름부터 보면 알겠지만, 자동으로 리소스의 연결을 해제해주는 메소드의 구현을 요구한다.
 */

import static java.lang.System.out;

import java.lang.AutoCloseable; //AutoClaoseable 가 존재함을 보여주고 있다.

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class test{
    public static void main(String args[]){
        Path file = Paths.get(".\\test.txt");
        
        //이렇게 resource 구문을 넣으면 된다. (method 방식하고는 완전 다름을 상기.)
        try(BufferedWriter writer = Files.newBufferedWriter(file)) {
            writer.write('A'); //IOException 발생 가능
            writer.write('B'); //IOException 발생 가능
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}