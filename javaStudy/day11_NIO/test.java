/*
 * Chapter 33 NIO 그리고 NIO.2
 * 
 * 
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

class test{
    public static void main(String[] args) throws IOException {
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

        fp1 = Files.createFile(fp1); //경로가 존재하는 경우 -> 파일 생성 (파일이 이미 존재하면 예외 발생)
        dp1 = Files.createDirectory(dp1); //경로가 존재하는 경우 -> 디렉토리 생성
        dp2 = Files.createDirectories(dp2); //경로에 필요한 '모든 디렉토리를 생성하면서' 최종 디렉토리까지 생성

        out.println("fp : " + fp1);
        out.println("dp1 : " + dp1);
        out.println("dp2 : " + dp2);

        //------------- 3) 파일 읽기/쓰기 하는 방법 ----------------
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
        Path fp2 = Paths.get(cur3.toString() + "\\InputOuputTest.dat");
        fp2 = Files.createFile(fp2);
        
        //입력할 데이터 확인
        byte[] buf1 = {0x11, 0x12, 0x13};
        for(int e : buf1)
            out.println(e+" ");
        out.println();

        //파일에 데이터 쓰기
        Files.write(fp2, buf1, StandardOpenOption.CREATE);

        //파일로부터 데이터 읽기
        byte[] buf2 = Files.readAllBytes(fp2);

        //출력할 데이터 확인
        for(int e : buf2)
            out.println(e+" ");
        out.println();

        //------------- 4)  ----------------
    }
}