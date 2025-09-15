/*
 * Chapter 33 NIO 그리고 NIO.2
 * 33-1 파일 시스템을 다루는 nio의 기본
 * 33-2 nio.2
 * 33-3 nio
 * 
 * 
 * 
 * 33-2 nio.2
 * nio.2 는 단순히 I/O Stream을 코드상에서 간단히 쓰기 위해 고안된 것이다.
 * 끝.
 * 
 */

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


class test{
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        Path fp1 = Paths.get("nio2_data_stream_test1.dat");
        
        //데이터 스트림 생성(입출력 모두)
        //nio.2 에서 연달아 new 키워드를 쓰는 것이 아닌,
        //Files의 static 메소드를 통해 비교적 간단하게 I/O Stream의 인스턴스를 생성했음에 주목하라.
        //게다가 Path를 통해서 경로를 손쉽게 다룰 수 있음 또한 유용하다.
        try (DataOutputStream out1 = new DataOutputStream(Files.newOutputStream(fp1));
            DataInputStream in1 = new DataInputStream(Files.newInputStream(fp1))) {
            out1.writeInt(100);
            out1.writeChar('A');
            out1.writeDouble(3.14);
            out1.writeLong(9L);

            out.println("data : " + in1.readInt());
            out.println("data : " + in1.readChar());
            out.println("data : " + in1.readDouble());
            out.println("data : " + in1.readLong());
            out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Path fp2 = Paths.get("nio2_buffered_character_stream_test1.dat");

        //문자열 버퍼 스트림 생성(입출력 모두)
        //게다가 Path를 통해서 경로를 손쉽게 다룰 수 있음 또한 유용하다.
        try (BufferedWriter out1 = Files.newBufferedWriter(fp2);
            BufferedReader in1 = Files.newBufferedReader(fp2)) {
                out1.write("hello");
                out1.newLine();
                out1.write("world");
                out1.newLine();
                out1.write("good");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader in1 = Files.newBufferedReader(fp2)) {
    

                String str;
                while (true) {
                    str = in1.readLine();
                    if(str == null)
                        break;
                    out.println("str : " + str);
                }
                out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }



        //실습 환경 리셋
        out.print("리셋하기 위해서 아무 버튼을 눌러주세요!!!");
        sc.nextLine();

        Path resetP1 = Paths.get("").toAbsolutePath();
        resetP1 = Paths.get(resetP1.toString() + "\\reset_batch_files\\reset_Chapter_33_2.bat");

        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", resetP1.toString());
        pb.redirectErrorStream(true); // 표준 에러를 표준 출력에 합치기
        Process p = pb.start();

        int exitCode = p.waitFor();
        System.out.println("프로세스 종료 코드: " + exitCode);


        sc.close();
    }
}