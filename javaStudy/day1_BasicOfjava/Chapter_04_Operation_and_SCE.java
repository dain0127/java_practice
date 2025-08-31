/*
 * Chapter 04 연산자
 * 기본적으로 C와 동일하다.
 * 다만, C와 다른 부분이 있는 java의 문법만을 여기에 소개하려고 한다.
 * 
 * 04-1 logical right shift와 arithmetic right shift
 * 04-2 Short-Circuit Evaluation (SCE)
 * 
 */

class test{
    public static void main(String args[]){
        //04-1 logical right shift와 arithmetic right shift
        //>>은 MSB를 고려하여 왼쪽 비트를 똑같이 채운다.
        //>>>은 무조건 0으로 채운다.

        int pos = 8;     // 00000000 00000000 00000000 00001000
        int neg = -8;    // 11111111 11111111 11111111 11111000 (2의 보수)

        System.out.println("양수 (8):");
        System.out.println("pos >> 2  = " + (pos >> 2));
        System.out.println("pos >>> 2 = " + (pos >>> 2));

        System.out.println("\n음수 (-8):");
        System.out.println("neg >> 2  = " + (neg >> 2));
        System.out.println("neg >>> 2 = " + (neg >>> 2));

        // 2진수 출력 (가독성 위해 32비트 포맷)
        System.out.println("\n2진수 확인:");
        System.out.println("neg       = " + String.format("%32s",
                Integer.toBinaryString(neg)).replace(' ', '0'));
        System.out.println("neg >> 2  = " + String.format("%32s",
                Integer.toBinaryString(neg >> 2)).replace(' ', '0'));
        System.out.println("neg >>> 2 = " + String.format("%32s",
                Integer.toBinaryString(neg >>> 2)).replace(' ', '0'));



        //04-2 Short-Circuit Evaluation (SCE)
        //SCE란.
        //true || (expresion)인 경우와 false && (expresion)의 경우
        //뒤에 있는 실행문을 생략하는걸 의미한다.

        int x = 10;

        System.out.println("check test");
        check();

        // && (AND) 단락 평가
        if (x < 0 && check()) {
            System.out.println("조건 만족!");
        } else {
            System.out.println("조건 불만족!");
        }

        // || (OR) 단락 평가
        if (x > 0 || check()) {
            System.out.println("조건 만족!");
        } else {
            System.out.println("조건 불만족!");
        }
    }    
    
    public static boolean check(){
        System.out.println("check() 실행됨");
        return true;
    }
}