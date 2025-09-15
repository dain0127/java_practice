/*
 * Chapter 33 NIO 그리고 NIO.2
 * 33-1 파일 시스템을 다루는 nio의 기본
 * 33-2 nio.2
 * 33-3 nio
 * 
 * 
 * 33-3 nio
 * nio는 채널을 이용해 파일과 데이터를 송수신한다.
 * 체력이 달려서 여기서 이해한 내용을 다 적진 않겠다.
 * 
 * 자세한 내용은 교재 참고.
 * 
 */

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;



class test{
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        out.print("파일 복사 시작(버튼 아무거나 눌러)");
        sc.nextLine();


        Path src = Paths.get("nio_src_file_to_channel.txt");
        Path dst = Paths.get("nio_dst_file_to_channel.txt");

        //1KiB 버퍼 생성
        ByteBuffer buf1 = ByteBuffer.allocate(1024);

        //파일 복사
        //채널 2개에 버퍼 1개
        try (FileChannel ifc = FileChannel.open(src, StandardOpenOption.READ);
        FileChannel ofc = FileChannel.open(dst, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {

            int len;

            while (true) {
                len = ifc.read(buf1); //채널 ifc에서 버퍼로 읽어들임
                if(len == -1) //읽은 데이터가 없으면 탈출
                    break;
                
                buf1.flip(); //모드 변환
                ofc.write(buf1); //버퍼에서 채널 ofc로 데이터 전송
                buf1.clear(); //버퍼 비우기. (이것이 필요한 이유는, nio에서는 버퍼를 읽었다고 해서, 버퍼가 지워지는건 아니기 때문. )
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        out.print("파일 랜던 접근 시작(버튼 아무거나 눌러)");
        sc.nextLine();

        //파일 랜덤 접근
        //채널 1개 버퍼 2개

        ByteBuffer wb = ByteBuffer.allocate(1024);
        ByteBuffer rb = ByteBuffer.allocate(1024);

        //채널의 옵션을 보면 알겠지만, 읽기/쓰기가 모두 가능하도록 허용된 채널이 나오게 되었다.
        try (FileChannel ch = FileChannel.open(Paths.get("nio_random_file_to_channel.txt"),
        StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            wb.putInt(10);
            wb.putInt(20);
            wb.putDouble(3.14);
            wb.putChar('A');
            //[[4:int][4:int][8:dobule][2:char]] : wb에 저장된 데이터의 현황.

            wb.flip(); //버퍼에 넣기 -> 버퍼에서 꺼내기
            //filp은 설명을 읽어보면 알겠지만,
            //put 시리즈 메소드나 채널의 read 메소드를 통해 채워진, 버퍼의 마지막 offset을 limit으로 삼고.
            //쓰기 모드로 바뀐채, offset을 0으로 되돌린다.
            //그리고 다시 차근차근 get 시리즈 메소드나 채컬의 write 메소드를 통해, 버퍼에 있는 데이터를 '읽는다.'
            
            ch.write(wb);


            ch.position(0); //파일의 offset을 set할 수 있다.
            ch.read(rb);

            rb.flip(); //버퍼에 넣기 -> 버퍼에서 꺼내기

            //꺼내는 순서를 10,20,'A',3.14 순으로 꺼내려고 한다고 가정하면.
            out.println(rb.getInt());
            out.println(rb.getInt());
            rb.position(Integer.BYTES*2 + Double.BYTES); //버퍼의 offset을 set할 수 있다.
            out.println(rb.getChar());
            rb.position(Integer.BYTES*2);
            out.println(rb.getDouble());


        } catch (IOException e) {
            e.printStackTrace();
        }
        


        //실습 환경 리셋
        out.print("리셋하기 위해서 아무 버튼을 눌러주세요!!!");
        sc.nextLine();

        Path resetP1 = Paths.get("").toAbsolutePath();
        resetP1 = Paths.get(resetP1.toString() + "\\reset_batch_files\\reset_Chapter_33_3.bat");

        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", resetP1.toString());
        pb.redirectErrorStream(true); // 표준 에러를 표준 출력에 합치기
        Process p = pb.start();

        int exitCode = p.waitFor();
        System.out.println("프로세스 종료 코드: " + exitCode);


        sc.close();
    }
}