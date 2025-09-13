import static java.lang.System.out;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
         */

        //기본적인 파일 열기, 읽기 및 쓰기, 닫기 API.
        OutputStream out0 = new FileOutputStream("data.dat");
        out0.write(10);
        out0.close();

        InputStream in0 = new FileInputStream("data.dat");
        int n = in0.read();
        in0.close();

        System.out.println(n);

        //try-with-resource 사용법.
        try(OutputStream out1 = new FileOutputStream("data.dat")){
            out1.write(100);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        try(InputStream in1 = new FileInputStream("data.dat")){
            int n1 = in1.read();
            System.out.println(n1);
        }
        catch(IOException e){
            e.printStackTrace();
        }



        //파일 복사 코드
        Scanner sc1 = new Scanner(System.in);

        String src = "origin.dat";

        out.print("사본 파일 이름1 : ");
        String dst = sc1.nextLine();

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

        out.print("사본 파일 이름2 : ");
        dst = sc1.nextLine();

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


        sc1.close();
    }
}