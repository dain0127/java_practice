/*
 * Chapter 03 Constant_and_Casting
 * 3-1 상수
 * 3-2 casting
 * C언어에서는 상수를 const라는 키워드로 선언한다. 그리고 선언 즉시 초기화를 해야한다.
 * 하자만 java에서는 final이라는 키워드로 선언한다. 그리고 선언 즉시 초기화를 할 필요가 없다. 
 */


class test{
    public static void main(String args[]){
        /* 3-1 상수 */
        //3-1-0 java의 상수 선언 방식(final)
        final double PI = 3.14;
        final char FISRT_NAME = '백';
        final int CONST_NUMBER;

        CONST_NUMBER = 10;

        System.out.println(PI);
        System.out.println(FISRT_NAME);
        System.out.println(CONST_NUMBER);


        //3-1-1 정수형 상수
        //정수형 byte, short, long 자료형 변수에 값을 저장하는 방식
        
        //java에서는 리터럴이 기본적으로 int형이다.
        //C에서는 리터럴이 표현되는 방식이 기본적으로 int이나 4byte가 넘어가면 long long으로 처리해준다.
        //하지만 java에서는 그런게 없다. java는 리터럴은 반드시 4byte가 할당되는 int형이다.
        int n1 = 0x12_34_56_78;
        int n2 = 0x12_34_56_78_9;//compile error! too large number for int type.

        //따라서 byte, short형 변수에 값을 저장하는 방식은 아래와 같아야한다.
        byte n3 = 0x12;
        short n4 = 0x12_34;
        //그리고 long형 변수에 값을 저장하는 방식은 아래와 같아야한다.
        long n5 = 99999999999L;

        //참고로 8진수와 2진수로 표현하는 방식도 있다. (verilog와 비슷)
        int temp1 = 0123123; int temp2 = 0B010101;



        //3-1-2 실수형 상수
        //java에서 실수 리터럴같은 경우 기본적으로 8byte인 double로 저장된다. (이는 C와 같다.)
        
        double f1 = 3.0;
        double f2 = 3.;
        double f3 = 3.0D; //double 형 명시.
        float f4 = 3.0; //compile error! 마찬가지로 실수 리터럴 상수 같은 경우에는, 기본적으로 double형으로 저장되기 때문.

        //따라서 float형 변수에 값을 저장하는 방식은 아래와 같아야 한다.
        float f5 = 3.0f;

        //참고로 부동소수점에서 지수부분과 가수부분을 명시하여 표현하는 방식도 있다.
        double temp3 = 0.1234e3; float temp4 = 0.4321e-2f;
        System.out.println(temp3 + ", " +temp4);

        
        //3-1-3 부울형 상수, 문자형 상수
        
    }
}