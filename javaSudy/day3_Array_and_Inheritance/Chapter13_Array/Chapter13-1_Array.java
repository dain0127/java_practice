/*
 * Chapter 13 Array
 * 13-1 Array에 대한 각종 문법
 * 13-2 enhanced for문
 * 13-3 다차원 배열
 * 
 * 13-1 Array
 * java에서는 Array도 일종의 '독립된 클래스'이다.
 * C처럼 point로 접근하고 뭐고 없다. 그냥 진짜로 독립된 클래스이며, 가지고 있는 field또한 상이하다.
 * 정 해당 배열의 element로 접근하고 싶으면, []연산자로 접근하라.
 * 그외 설명은 소스 코드의 주석과 함께 남긴다.
 * 읽으면서 이해해라.
 */

import static java.lang.System.out;
import java.util.Scanner;
import java.util.Arrays;

class StringBox{
    private String str;
    StringBox(String str){this.str = str;}
    
    public String toString(){return this.str;}
}

class test {
    public static void main(String args[]){
        //배열도 사실 인스턴스이다. (C++ 에서 포인터 취급하는 거랑 다르다.)
        int[] arr1 = new int[10]; //길이가 5인 int형 배열 객체.
        out.println(arr1.length);

        StringBox[] boxArr = new StringBox[5];
        out.println(boxArr.length);
        out.println();

        //배열의 저장과 참조
        arr1[0] = 0;
        arr1[1] = 1;

        boxArr[0] = new StringBox("hello");
        boxArr[1] = new StringBox("world");

        out.println(arr1[0]);
        out.println(boxArr[0]);
        out.println();

        //배열 생성과 동시에 초기화하기.
        int[] arr2 = new int[] {1,2,3,4,5};
        for(int i=0;i<arr2.length;i++)
            out.println(arr2[i]);
        out.println();

        //참조 변수 선언 방식의 두가지.
        int[] temp1;
        int temp2[];

        //배열의 참조값으로서 인자값 전달과 반환값 받기
        int[] arr3 = makeIntArray(5);
        revertIntArray(arr3);
        printIntArray(arr3);
        System.out.println();

        //배열의 초기화와 복사
        //배열간의 복사를 for문으로 해도 되지만, 당연히 기본 패키지 클래스에 제공되어있다.
        //초기에는 0으로 초기화 된다.
        int[] arr4 = new int[10];
        int[] arr5 = new int[10];
        Arrays.fill(arr4, 7); //배열을 9로 초기화
        System.arraycopy(arr4, 0, arr5, 4, 3);
        printIntArray(arr4);
        out.println();
        printIntArray(arr5);
        out.println();

        //main에서 String args[]를 받는 방식
        //C/C++과 같다. int argc, char* args[]랑 다를게 없다.
        //java test "원하는 문자열들"을 출력하면 된다.
        printStringArray(args);
    }

    static int[] makeIntArray(int len){
        int[] arr = new int[len];
        for(int i=0;i<len;i++)
            arr[i] = i;
        return arr;
    }

    static int[] revertIntArray(int[] arr){
        int temp;
        for(int i=0;i<arr.length/2;i++){
            temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        return arr;
    }

    static void printIntArray(int[] arr){
        for(int i=0;i<arr.length;i++){
            out.print(arr[i]+" ");
        }
    }

    static void printStringArray(String[] arr){
        for(int i=0;i<arr.length;i++){
            out.print(arr[i]+" ");
        }
    }
}
