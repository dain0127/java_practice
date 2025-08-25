/* 
 * 12. Console
 * Console이란 사용자가 시스템과 정보를 주고받는 장치를 의미한다.
 * 12-1 Console output
 * 12-2 Console input
 * 
 * 12-1. Console output
 * System.out.println()
 * System.out.print()
 * System.out.printf()
 * 대표적으로 세가지가 있다. 
 * 
 * println은 개행기능 추가
 * print은 개행기능 없음
 * printf은 서식문자 추가해서 값 전달 인자있는 것. C랑 같다.
 * 
 * 상기할만한 내용은... println(obj);처럼 객체 자체를 전달할 경우,
 * 내부적으로 알아서 toString()메소드의 반환값을 출력한다.
 * 
 * 12-2. Console input
 * Scanner라는 클래스를 이용해서 input을 받을 수 있다.
 * Scanner의 생성자가 여러 type으로 오버로딩 되어있는데, File, String, InputStream(키보드 관련) 등등 수많이 오버로딩 되어있다.
 * 용도에 알맞게 Scanner에 연결하고싶은 input을 연결하여, 이용하면 될 것이다.
 * 
 * 만약에 키보드로 입력받고 싶으면
 * 1) Scanner sc = new Scanner(System.in) //키보드에 연결
 * 2) String inputStr = sc.nextline() //한줄 문자열로 입력받기
 * 
 * ... 등등. 그 외에서 Scanner의 입력 method는 많이 있으니 필요할때 검색해서 사용할 것.
 * (ex nextInt(), nextByte())
 */

import java.util.Scanner;

class Simple{
    private int num;

    Simple(int n){this.num = n;}

    //toString이 있으면 println따위의 메소드에서 해당 객체에 어울리는 Stirng값을 뽑아내는 메소드로 믿고 사용.
    public String toString(){
          return new String(String.valueOf(num));
    }
}

class test{
    public static void main(String args[]){
        //12-1. console output
        System.out.println("println");
        System.out.print("print");
        System.out.printf("num is %d", 10);

        System.out.println();
        System.out.println();

        Simple sim = new Simple(20);
        System.out.println(sim); //toString()이 정의되어있으리라 믿고 객체 참조값을 인자값으로 넘김.
        System.out.println();

        //12-2. console input
        String str = "1 3 5"; //white space로 input을 구별한다.
        Scanner sc = new Scanner(str);
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int num3 = sc.nextInt();
        int sum = num1 + num2 + num3;
        System.out.printf("%d + %d + %d = %d", num1, num2, num3, sum);
        System.out.println();

        //키보드로 콘솔창에서 입력받는 방법.
        sc = new Scanner(System.in);

        System.out.print("문자열 입력 : ");
        String str1 = sc.nextLine();
        System.out.print("문자열 입력 : ");
        String str2 = sc.nextLine();

        System.out.println(str1);
        System.out.println(str2);
    }
}

