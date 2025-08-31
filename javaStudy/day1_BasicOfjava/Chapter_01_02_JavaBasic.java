/*
* Chapter 01 and 02 Java_Basic
 * java의 철학.
 *  
 * java는 운영체제 플랫폼과 관계없이 같은 코드로 원활하게 작동하도록 하는 철학을 준수한다.
 *  한 번 작성하면 어디서나 실행된다 (Write Once, Run Anywhere)

    Java의 가장 유명한 철학이자 목표.

    프로그램을 Java Virtual Machine (JVM) 위에서 실행시키는 방식을 채택했기 때문에,
    Windows, Linux, macOS 등 어떤 운영체제에서도 동일한 바이트코드를 실행할 수 있다.

    하드웨어나 OS에 직접 종속되지 않고, 이식성을 최우선으로 삼는다.

    javac.exe 실행파일은 java compiler로서 java source code를 byte code로 변환시킨다. (컴파일 방식)
    java.exe 실행파일은 java Launcher로서 java의 byte code를 JVM에서 실행시키는 역할을 한다. (인터프리터 방식)
    
    참고로 바이트 코드라고 불리는 이유는, JVM에서 opcode의 크기가 1byte이기 때문이다.
 * 
 */

class test{
    public static void main(String args[]){
        /*
         * java의 기본 자료형(Primitive Data Type)
         */

        boolean a; //1byte

        char ch; //2byte for unicode

        byte b; //1byte
        short sh; //2byte
        int n; //4byte
        long ln; //8byte
        
        float f; //4byte
        double d; //8byte


        //부동소수점 방식의 정밀도 한계
        double n1 = 1.0000001, n2=2.0000001;
        double result1 = n1+n2;
        System.out.println(result1);

        
        //자바의 정수형 연산
        short n3 = 1;
        short n4 = 2;
        short result2 =  n3 + n4; //compile error.

        //JVM에서 정수연산은 반드시 int로 이루어지며, 그렇기 때문에 4byte의 int를 short로 변환시키는 과정에서 데이터 손실이 일어난다.
        //C언어에서 묵시적 형변환이 일어나는 것과 다르다. java에서는 그런게 없으며, 
        //따라서 아래와 같이 값을 저장해야하며, 정수를 저장하고 연산할 때에는 왠만하면 int로 하는 것이 좋다.

        int result2 = n3 + n4;
        System.out.println(result2);

        
        //java의 문자 저장 방식은 unicode(2byte)
        //c언어에서의 char형은 1byte이며 ascii를 상정하고 있기 때문에 64개로만 영어와 특수문자만 표현한다.
        //하지만 unicode는 2byte이므로 2^16(약 6만 4천개)를 표현할 수 있으며, 이는 전세계 언어를 커버칠 수 있는 정보량이다.
        //물론... 그만큼 앞으로 문자를 표현할때 느려지게 되지만 말이다.
        char ch1 = '헐';
        char ch2 = 0xD5D0;
        System.out.println(ch1 + "" + ch2);


        //boolean 자료형.
        //keyword로 true와 false가 할당되어있다. 이는 c++과 같다.
        boolean b1 = true;
        boolean b2 = false;

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(1<2);
        System.out.println(1>2);
    }
}