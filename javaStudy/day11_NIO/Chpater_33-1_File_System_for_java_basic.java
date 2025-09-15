/*
 * Chapter 33 NIO 그리고 NIO.2
 * 33-1 파일 시스템을 다루는 nio의 기본
 * 33-2 nio.2
 * 33-3 nio
 * 
 * 
 * 33-1 파일 시스템을 다루는 nio의 기본
 * 
 * NIO 방식이 I/O Stream보다 우월한건 아니다.
 * 하지만, NIO는 코드적으로 간편하다. 대가로, 기능적인 유연함은 부족하다.
 * 
 * NIO 방식은 Path/Paths라는 클래스를 통해 경로를 저장하고,
 * 이를 기반으로 Files라는 클래스를 통해 쉽게 파일의 생성/읽기/쓰기/복사/이동...
 * 일련의 전형적인 데이터 입출력 기능을 비교적 간단한 코드로 실행할 수 있다.
 * 
 * 이는 정해진 방식으로 자주쓰이는 기능에 대해서는, 개발자의 생상성을 높이리라 기대할 수 있다.
 * 
 * 당장 try-with-resorce를 쓰지 않는 것 만으로도 큰 장점이다.
 * 
 * 실습 방법 :
 * 0) 코드를 하니씩 분석한다.
 * 1) run.bat을 실행한다.
 * 2) 코드와 결과를 비교한다.
 * 3) 재실습을 하고 싶으면, reset.bat을 실행해, 실습 환경을 초기화한다.
 * 4) 반복한다. 
 * 
 */

import static java.lang.System.out;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class test{
    public static void main(String[] args) throws IOException, InterruptedException {
        /*
         * Path는 특정 경로에 있는 '파일' 또는 '디렉토리'를 가리키는 정보를 저장할 수 있다.
         * 
         */


        Scanner sc = new Scanner(System.in);

        //------------- 1) 파일의 경로 저장하는 방법 ----------------
        //절대 경로로 호출하는 법
        Path abPath1 = Paths.get("C:\\Users\\dain0\\Desktop\\developer\\JAVA\\javaStudy\\day11_NIO\\test.java");
        Path pt2 = abPath1.getRoot();
        Path pt3 = abPath1.getParent();
        Path pt4 = abPath1.getFileName();

        out.println("path 1 : " + abPath1);
        out.println("path 2 : " + pt2);
        out.println("path 3 : " + pt3);
        out.println("path 4 : " + pt4);
        out.println();

        //상대 경로로 호출하는 법
        Path cur1 = Paths.get(""); //현재 디렉토리
        String cdir_str;

        if(cur1.isAbsolute())
            cdir_str = cur1.toString();
        else
            cdir_str = cur1.toAbsolutePath().toString();

        out.println("Current dir : " + cdir_str);
        out.println();

        //------------- 2) 파일 생성하는 방법 ----------------
        Path cur2 = Paths.get("").toAbsolutePath();

        Path fp1 = Paths.get(cur2.toString() + "\\createFile.dat");
        Path dp1 = Paths.get(cur2.toString() + "\\createDir\\");
        Path dp2 = Paths.get(cur2.toString() + "\\createDir2\\CreateDir3\\");

        out.print("Press any button to create file");
        sc.nextLine();
        fp1 = Files.createFile(fp1); //경로가 존재하는 경우 -> 파일 생성 (파일이 이미 존재하면 예외 발생)

        out.print("Press any button to create directory");
        sc.nextLine();
        dp1 = Files.createDirectory(dp1); //경로가 존재하는 경우 -> 디렉토리 생성

        out.print("Press any button to create directories");
        sc.nextLine();
        dp2 = Files.createDirectories(dp2); //경로에 필요한 '모든 디렉토리를 생성하면서' 최종 디렉토리까지 생성

        out.println("fp : " + fp1);
        out.println("dp1 : " + dp1);
        out.println("dp2 : " + dp2);

        //------------- 3)-(1) 바이트 방식으로 읽기/쓰기 하는 방법 ----------------
        /* 
         * write(Path, byte[], StandardOpenOption)에서
         * StandardOpenOption는 열거형이다.
         * 교재에서 제시한 종류는 아래와 같다.
         * 
         * APPEND               파일의 끝에 데이터 추가
         * CREATE               파일이 존재하지 않으면 생성
         * CREATE_NEW           파일이 존재하지 '않아야', 생성 (존재하면 예외 발생)
         * TRUNCATE_EXISTING    파일이 존재하더라도 덮어쓰기.
         * 
         */

        //파일 생성
        Path cur3 = Paths.get("").toAbsolutePath();
        Path fp2 = Paths.get(cur3.toString() + "\\InputOuputTest_1.dat");
        fp2 = Files.createFile(fp2);
        
        //입력할 데이터 확인
        out.println("쓸 데이터 확인");
        byte[] buf1 = {0x11, 0x12, 0x13};
        for(int e : buf1)
            out.println(e+" ");
        out.println();


        out.print("Press any button to write file by byte");
        sc.nextLine();
        //파일에 데이터 쓰기
        Files.write(fp2, buf1, StandardOpenOption.CREATE);


        out.print("Press any button to read file by byte");
        sc.nextLine();
        //파일로부터 데이터 읽기
        byte[] buf2 = Files.readAllBytes(fp2);

        //출력할 데이터 확인
        out.println("읽은 데이터 확인");
        for(int e : buf2)
            out.println(e+" ");
        out.println();

        //------------- 3)-(2) 문자 방식으로 파일 읽기/쓰기 하는 방법 ----------------
        /*
         * 방금의 방식은 byte 배열의 데이터를 파일에 읽기/쓰기하는 방식을 보았다.
         * 
         * 이번에 소개하는 것은 문자열 배열(List<String>)에 저장된 데이터를 파일에 읽기/쓰기 하는 방식이다.
         * 
         * 이는 각각 I/O Stream에서 바이트 스트림, 문자 스트림에 대응된다.
         * 상술했다시피, I/O Stream에 비해 코드가 간결함을 주목하라.
         * 
         */

        Path fp3 = Paths.get("").toAbsolutePath();
        fp3 = Paths.get(fp3.toString()+"\\InputOuputTest_2.dat");

        List<String> li1 = Arrays.asList("one", "two");

        out.print("Press any button to write file by char");
        sc.nextLine();
        //파일에 문자열 저장하기
        Files.write(fp3, li1);

        out.print("Press any button to read file by char");
        sc.nextLine();
        //파일로부터 문자열 읽기
        List<String> li2 = Files.readAllLines(fp3);
        
        //읽은 문자열 확인
        out.println("읽은 문자열 확인");
        for(String e : li2)
            out.print(e+" ");
        out.println();
        out.println();


        //------------- 4) 파일(디렉토리)의 복사와 이동 ----------------
        /*
         *
         * Path copy(Path src, Path dest, OpenOption...options)
         * Path move(Path src, Path dest, OpenOption...options)
         * 
         */

        Path srcP1 = Paths.get("").toAbsolutePath();
        srcP1 = Paths.get(srcP1.toString() + "\\DIR_1\\SourceText.txt");

        Path dstP1 = Paths.get("").toAbsolutePath();
        dstP1 = Paths.get(dstP1.toString() + "\\DIR_2\\Destination_for_copy.txt");

        Path dstP2 = Paths.get("").toAbsolutePath();
        dstP2 = Paths.get(dstP2.toString() + "\\DIR_2\\Destination_for_move.txt");


        out.print("Press any button to copy start");
        sc.nextLine();
        
        Files.copy(srcP1, dstP1); //파일 복사

        out.print("Press any button to move start");
        sc.nextLine();

        Files.move(srcP1, dstP2); //파일 이동



        
        //실습 환경 리셋
        out.print("리셋하기 위해서 아무 버튼을 눌러주세요!!!");
        sc.nextLine();

        Path resetP1 = Paths.get("").toAbsolutePath();
        resetP1 = Paths.get(resetP1.toString() + "\\reset_batch_files\\reset_Chapter_33_1.bat");

        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", resetP1.toString());
        pb.redirectErrorStream(true); // 표준 에러를 표준 출력에 합치기
        Process p = pb.start();

        int exitCode = p.waitFor();
        System.out.println("프로세스 종료 코드: " + exitCode);

        sc.close();
    }
}