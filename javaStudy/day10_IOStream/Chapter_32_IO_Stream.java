import static java.lang.System.out;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
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

        //======================= f. 문자 스트림 =============================
        out.println("//===================== f. 문자 스트림 ========================");
        /*
         * 시스템과 자바 프로그램의 인코딩 방식의 차이가 있을 수 있다.
         * 때문에 '문자'와 관련된 데이터를 읽고 쓸때에는, 각 플렛폼에 알맞는 인코딩 방식으로 '변환' 해주는 스트림이 필요하다.
         * 
         * 문자 데이터의 경우에는 단순히 데이터를 옮기는 것 만으로는 해결되지 않기 때문이다.
         * 실제적으로 저장된 바이너리 데이터 '그 자체'는 같을지 몰라도 인코딩 방식에 따른 '해석'의 차이가 존재하기 때문이다.
         * 
         * 다시 말하자면, 데이터는 같으나 의미가 달라질 수 있다는 뜻이다.
         * 
         * 예를 들어, java 프로그램 내에서는 문자 데이터가 '유니코드' 방식의 인코딩 방식(내지는 문자셋)을 사용한다면,
         * 윈도우의 메모장 프로그램은 '코드페이지 949'라는 인코딩 방식으로 문자를 해석한다.
         * 
         * 때문에 플랫폼 별로 각기 다른 인코딩 방식에 대한 '자동적인 호환'기능이 들어있는 I/O 스트림인 '문자 스트림'의
         * 필요성이 생긴다.
         * 
         * 사용례는 아래 코드를 참고하면 된다.
         * 
         */

        //문자 스트림을 사용한 예시
        try (Writer out1 = new FileWriter("f_character.txt")) {
            out1.write('A');
            out1.write('B');
            out1.write('\n');
            out1.write("hello world\n");
            out1.write('한');
            out1.write('글');
        } catch (IOException e) {
            e.printStackTrace();
        }


        //바이트 스트림을 사용한 예시
        //바이트 스트림을 사용해 write한 문서의 경우, 인코딩 방식이 달라 문자가 깨져서 나오는 것을 확인하라.
        try (OutputStream out1 = new FileOutputStream("f_byte.txt")) {
            out1.write('A');
            out1.write('B');
            out1.write('\n');
            out1.write('한');
            out1.write('글');
        } catch (IOException e) {
            e.printStackTrace();
        }



        //문자 스트림을 통해 시스템에서 파일을 읽어, 'java 프로그램'에서 출력하는 코드.
        try (Reader in1 = new FileReader("text_origin.txt")) {
            int ch;

            while (true) {
                ch = in1.read();
                if(ch == -1)
                    break;
                out.print((char)ch);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        out.println();

        //바이트 스트림을 통해 시스템에서 파일을 읽어, 'java 프로그램'에서 출력하는 코드.
        //해당 코드가 출력이 될때에는 문자들이 깨져서 출력됨을 주목하라.
        try (InputStream in1 = new FileInputStream("text_origin.txt")) {
            int ch;
            while (true) {
                ch = in1.read();
                if(ch == -1)
                    break;
                out.print((char)ch);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println();

        /*
         * 참고로. 문자 스트림또한. 그만의 필터 스트림이 존재한다.
         * 이때 이미 문자형 데이터를 저장했다는 면에서, 굳이 데이터 스트림으로의 필요성보다는.
         * '버퍼 스트림'이 사용된다.
         * 
         * 이때 Writer/Reader의 맞추어서 BufferedReader/BufferedWriter 가 쓰여진다.
         * 
         * 사용법 또한 같으며 개념도 같다.
         * 
         * 다만 문자가 버퍼로 쌓이면 '문자열'이다.
         * 즉, 문자열로 모아서 한거번에 전송하는 기능이 있다.
         * 
         * 이에 따른 BufferedReader/BufferedWriter 만의 API가 존재한다.
         * readLine() 문자열 단위로 읽는 것.(구분자는 해당 시스템의 '개항')
         * write(String s, int off, int len) s를 off부터 시작헤 len까지의 문자를 쓰기.
         * 혹은 write(String s)
         * 
         * 가 그러하다.
         * 
         */

        //최종적으로, 자바로부터 나온 문자열을, 시스템의 텍스트 파일에 저장하고.
        //그 텍스트 파일을 다시 자바 프로그램으로 옮기는 코드
        String kr_str1 = new String("안녕. 나는 한글 문자열이야.");
        String en_str1 = new String("hello, I'm english String.");

        try (BufferedWriter in1 = new BufferedWriter(new FileWriter("f_test.txt"))) {
            
            in1.write(kr_str1);
            in1.newLine();
            in1.write(en_str1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader out1 = new BufferedReader(new FileReader("f_test.txt"))) { 
            String str;
            while (true) {
                str = out1.readLine();
                if(str == null)
                    break;
                out.println(str);
            }
        
        } catch (IOException e) {
            e.printStackTrace();
        }

        //======================= g. 오브젝트 스트림 =============================
        out.println("//===================== g. 오브젝트 스트림 ========================");
        /*
         * 오브젝트 또한 파일에 읽고 쓸수 있는 대상이다.
         * 당연히 바이트 스트림으로 해야한다.
         * 
         * 오브젝트 스트림(ObjectInputStream/ObjectOutputStream)같은 경우에는
         * 필터 스트림과 유사하지만, '필터 스트림'이 아니다.
         * 그 이유는 필터 스트림이 공통적으로 상속하는, FilterInputStream/FilterOutputStream을 상속하지 않음을 의미한다.
         * 그 말인 즉슨, 필터 스트림들의 클래스가 가지는 공통적인 규약에서 벗어난, 예외적인 인터페이스를 가졌음을 추측할 수 있다.
         * 그럼에도 필터 스트림과 비슷하다고 한 이유는, 사용법이나 보조적인 기능을 한다는 점에서 그러하다는 것이다.
         * 
         * 오브젝트 스트림을 사용할때는, 기본 자료형 대신, 인스턴스 자체를 read / write 메소드 따위의 인자로 넘겨주면 된다.
         * 
         * 클래스에 Serializable이라는 마커 인터페이스가 구현이 되어야, 오브젝트 스트림을 통해 파일에 읽기/쓰기가 가능하다.
         * 
         * 클래스를 정의할때 필드에 transient라는 키워드를 붙이면, 해당 필드는 파일에 입출력할때, null이나 0으로 읽고/써진다.
         * 
         */

        class SBox implements Serializable {
            String str;
            transient String str_trans;
            SBox(String s){
                this.str = s;
                this.str_trans = s; 
            }
            @Override
            public String toString() {return str;}
            public String getTransString(){return str_trans;}
        }

        SBox sbox1 = new SBox("one");
        SBox sbox2 = new SBox("two");

        //인스턴스의 데이터를 파일에 저장(직렬화(Serialization)).
        try (ObjectOutputStream out1 = new ObjectOutputStream(new FileOutputStream("g_objectStreamTest.dat"))) {
            out1.writeObject(sbox1);    
            out1.writeObject(sbox2);    
        } catch (IOException e) {
            e.printStackTrace();
        }

        SBox sbox3;
        SBox sbox4;

        //파일의 인스턴스 데이터를 자바 프로그램에 불러오기(역직렬화(Deserialization))
        try (ObjectInputStream in1 = new ObjectInputStream(new FileInputStream("g_objectStreamTest.dat"))) {
            sbox3 = (SBox)in1.readObject();
            sbox4 = (SBox)in1.readObject();           

            out.println(sbox3);
            out.println(sbox4);

            out.println("trans string : " + sbox3.getTransString());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}