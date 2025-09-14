import static java.lang.System.out;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.Scanner;

class test{
    public static void main(String[] args) throws IOException {
        /*
         * InputStream/OutputStream 들은  FileInputStream/FileOutputStream의 상위 클래스이다.
         * InputStream/OutputStream 라는 상위클래스는, 파일 외에 각종 입출력 장치 및 자원에 대하여 일관된 인터페이스를 제공한다.
         * 
         * 즉, 겉보기에는 같은 read. write를 사용해도. 내부적으로 다르게 구현된 기능으로 처리해줄 수 있다.
         * 
         * 추상화/다형성이 적용된 유용한 기능이다.
         * 
         * 
         * FileInputStream/FileOutputStream는 파일과의 연결을 위한 클래스이다.
         * 
         */


        //===================a. 기본적인 파일 열기, 읽기 및 쓰기, 닫기 API.========================
        out.println("//===================a. 기본적인 파일 열기, 읽기 및 쓰기, 닫기 API.========================");

        OutputStream out0 = new FileOutputStream("a_test1.dat");
        out0.write(10);
        out0.close();

        InputStream in0 = new FileInputStream("a_test1.dat");
        int n = in0.read();
        in0.close();

        System.out.println(n);

        //try-with-resource 사용법.
        try(OutputStream out1 = new FileOutputStream("a_test2.dat")){
            out1.write(100);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        try(InputStream in1 = new FileInputStream("a_test2.dat")){
            int n1 = in1.read();
            System.out.println(n1);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        out.println();

        //====================== b. 파일 복사 코드 ===============================
        out.println("//====================== b. 파일 복사 코드 ===============================");


        Scanner sc1 = new Scanner(System.in);

        String src = "origin.dat";
        String dst;


        //byte 하나 단위로 파일의 내용물 복사하는 방법.

        //시간 측정.
        Instant start1 = Instant.now();

        //out.print("사본 파일 이름1 : ");
        //dst = sc1.nextLine();
        dst = "b_copy1.dat";
        try (InputStream in1 = new FileInputStream(src);
        OutputStream out1 = new FileOutputStream(dst)) {
            int data;//int를 사용하는 이유는 EOF로써 -1를 받기 위해서이다. 그 외에는 하위바이트에 0~255 범위내 값을 받는다.
            //이는 read()메소드의 설명을 읽으면 이해할 수 있다.
            while(true){
                data = in1.read();
                if(data == -1)
                    break;
                out1.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //시간 측정
        Instant end1 = Instant.now();
        Duration time1 = Duration.between(start1, end1);
        out.println("time1(1byte씩 읽기/쓰기) : " + time1.toMillis()+"ms");

        //byte 배열로 buffer를 만들어서 뭉텅이로 파일 데이터 복사하는 방법.

        //시간 측정
        Instant start2 = Instant.now();

        //out.print("사본 파일 이름2 : ");
        //dst = sc1.nextLine();
        dst = "b_copy2.dat";
        try (InputStream in1 = new FileInputStream(src);
        OutputStream out1 = new FileOutputStream(dst)) {
            byte[] buf = new byte[1024];
            int len;

            while(true){
                len = in1.read(buf);
                if(len == -1)
                    break;
                out1.write(buf, 0, len);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }


        Instant end2 = Instant.now();
        Duration time2 = Duration.between(start2, end2);
        out.println("time1(1KiB씩 읽기/쓰기) : " + time2.toMillis()+"ms");
        sc1.close();

        out.println();


        //============ c. 필터스트림 (1) DataInputStream/DataOutputStream ==============
        out.println("//=========== c. 필터스트림 (1) DataInputStream/DataOutputStream ===============");

        /*
         * 앞서 소개한 FileInputStream/FileOutputStream 같은 I/O Stream은 단순히 하드디스크와 프로세스를 연결하는 기능을 수행한다.
         * 
         * 그러나 필터스트림은 I/O Stream에서 'byte' 단위로 주고받는 입출력을, 데이터의 가공 재조립따위를 통해 기능을 보조한다.
         * 필터스트림은 I/O Stream에 '연결'되어 데이터를 전달받는다.
         * 이때의 '연결'을 코드 상에서의 구현방식을 보려면 아래 코드를 참조하라.
         * 
         * 교재에서 소개하는 필터스트림은 두가지가 있다.
         * 1) DataInputStream/DataOutputStream : java code에서 기본 자료형으로 다룰 수 있도록 데이터를 재조립
         * 2) BufferedInputStream/BufferedOutputStream : 버퍼링 기능을 제공
         * 
         * 
         */

        /* 
         * DataInputStream/DataOutputStream은 필터스트림의 중 하나인데, 'byte' 단위의 입출력을
         * '기본 자료형(int, double, char...)'으로 주고받을 수 있도록 데이터를 가공하고 재조립하는 기능을 수행한다.
         * 
         * 즉, java 코드 내에서, 파일 입출력으로 받은 데이터를 기본 자료형으로서 활용할 수 있도록 해준다.
         * 
         * 개념적으로, [프로세스(CPU, 메모리... )] <= [필터스트림1] <= [필터스트림2] <= ... <= [I/O 스트림] <= [하드웨어적 장치(파일)].
         * 순으로 데이터가 거쳐서 오게 된다.
         * 
         */


        //스트림의 연결은, 생성자에 인스턴스 참조값을 전달하는 방식을 취한다.
        //그럼 이후의 연결된 스트림의 생성은, 해당 참조값을 바탕으로 스트림과의 데이터 송수신을 가능케한다.
        try (DataOutputStream out1 = new DataOutputStream(new FileOutputStream("c_dataStreamTest.dat"));
            DataInputStream in1 = new DataInputStream(new FileInputStream("c_dataStreamTest.dat"))) {
            out1.writeInt(10);
            out1.writeDouble(3.14);

            int n1 = in1.readInt();
            double n2 = in1.readDouble();

            out.println("int n1 : " + n1);
            out.println("double n2 : " + n2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        out.println();

        //============= d. 필터스트림 (2) BufferedInputStream/BufferedOutputStream ===========
        /*
         * 버퍼 스트림은, 내부에 있는 byte 버퍼에 일정량의 데이터를 쌓다가, 한거번에 파일에 읽기/쓰기를 하는 방식이다.
         * 이는 b. 파일 복사 코드의 두번째 방식을 class로 쓰기 좋게 만든 것임을 참고하라.
         * 
         * 하드 디스크/입출력 장치는 메모리와 거리가 물리적으로 멀다. 즉, 거쳐야하는 장치도 많고, 전선도 길고, ...이래저래 시간이 오래걸린다.
         * 다시 말하자면, 파일에 읽고 쓰려는 시도가 잦을수록, 성능이 자연히 떨어진다는 의미이다.
         * 
         * 이러한 점 때문에 버퍼 스트림이 고안된 것이다. 데이터를 한번에 모아놓고 입출력을 하여. 파일의 접근 횟수를 되도록 줄이는 것.
         * 그로인해 성능의 향상을 기대하는 공학적 아이디어가 담긴 클래스이다.
         * 
         * 
         * 참고로 flush() 메소드가 있는데, 버퍼(메모리상의 저장 공간)에 남아있는 데이터를 파일에 쓴다.
         * (참고로 읽기(input)는 flush가 없는데... 당연하지 않는가. 이미 버퍼에 왔는데. read하면 그만이다.)
         * 중요한 데이터를 반드시 파일에 써주고 싶을때, 그럴때 flush()를 하면 된다.
         * (시스템이 다운되거나, 프로그램이 강제 종료 되거나... 이럴때 버퍼에 있는 데이터가 날라가, 파일에 안써질수도 있다.)
         * 
         * 참고로 Buffered Output Stream이 종료될때, 알아서 flush가 호출이 된다.
         * 그래서 굳이 종료시 flush를 호출할 의무는 없다.
         * 
         *          
         */
        out.println("//============== d. 필터스트림 (2) BufferedInputStream/BufferedOutputStream ===========");

        Instant start3 = Instant.now();

        try (BufferedOutputStream out1 = new BufferedOutputStream(new FileOutputStream("d_bufferedStreamTest.dat"));
        BufferedInputStream in1 = new BufferedInputStream(new FileInputStream("origin.dat"))) {
            int data;
            while(true){
                data = in1.read();
                if(data == -1)
                    break;
                out1.write(data);
            }

            out1.write(0xFF); //중요한 데이터!
            out1.flush(); //중요한 데이터를 쓸 때 호출해 주면 된다.
        
        } catch (IOException e) {
            e.printStackTrace();
        }

        Instant end3 = Instant.now();
        Duration time3 = Duration.between(start3, end3);
        out.println("time3(BufferedStream에 코드 상으로는 1byte씩 읽기/쓰기) : " + time3.toMillis()+"ms");
        out.println();

        //============= e. Data Input/Output Stream과 Buffered Input/Output Stream을 연달아 잇기 ===========
        out.println("//============= e. Data Input/Output Stream과 Buffered Input/Output Stream을 연달아 잇기 ===========");
        /*
         * Data Stream은 개발자가 I/O Stream으로 부터 받은 데이터를 기본 자료형으로 이용할 수 있게 가공해준다.
         * Buffered Stream은 물리 장치의 접근으로 발생하는 오버헤드를 줄이기위해, 데이터를 모았다가 한거번에 읽기/쓰기를 하여 성능을 올려준다.
         * 
         * 이 두가지의 유용한 기능을 한거번에 사용할 수 있다. Stream을 연달아 이어 붙이는 것이다.
         * 
         * 상술했다시피, 스트림을 사용할때 개념적으로는 이러하다.
         * [프로세스(CPU, 메모리... )] <= ★★★[필터스트림1] <= [필터스트림2]★★★ <= ... <= [I/O 스트림] <= [하드웨어적 장치(파일)].
         * 이때 필터스트림은 중간에 연달아 다른 종류로 이어 붙일 수 있다.
         * 이때 사용자 인터페이스의 역할을 하는 Data Stream이 맨 앞(필터스트림 1부분)에 오는것이 옳다.
         * 
         * 즉, [프로세스] <= [Data Stream] <= [Buffered Stream] <= ... 순으로 스트림 연결이 이루어져야한다.
         * 
         */

        try (DataOutputStream out1 = new DataOutputStream(
            new BufferedOutputStream(
                new FileOutputStream("e_concatenated_Filter_Stream_Test.dat")
            ));
            DataInputStream in1 = new DataInputStream(
            new BufferedInputStream(
                new FileInputStream("origin.dat")
            ));
        ) {
            int data;

            while(true){
                data = in1.read();
                if(data == -1)
                    break;
                out1.write(data);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}