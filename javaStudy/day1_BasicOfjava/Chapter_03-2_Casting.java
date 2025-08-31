/*
 * Chapter 03 Constant_and_Casting
 * 3-1 상수
 * 3-2 casting
 * 
 */


class test{
    public static void main(String args[]){
        /* =========== 3-2 캐스팅 ============ */
        //C와 같으므로 설명을 줄인다.
        //다만. 대입연산자시 java는 묵시적 형변환이 일어나지 않는다.

        //목시적 형변환.
        //크기 작은거 -> 큰거
        //정수 -> 실수
        //정보 손실이 일어나지 않는 쪽으로 형변환이 된다.
        System.out.println(1000000L + 3); //long + int->long
        System.out.println(1000000L + 0.14); //long->double + double
        System.out.println(3.14f + 2.17D); //float->double + double


        //명식적 형변환.
        int num1 = 3;
        int num2 = 4;
        short num3 = (short)(num1+num2); //이러면 컴파일 에러가 안일어남.
        //단, 상위 4바이트가 잘릴때, 알수 없는 값이 될 수 있는 잠재적 위협이 있다.

        //잘못된 명시적 캐스팅 예시
        System.out.println("=============wrong casting==============");
        int intNum1 = 40000;//0b_0000_0000_0000_0000_1001_1100_0100_0000
        short shortNum1 = (short)intNum1;//0b_1001_1100_0100_0000
        System.out.println("intNum1 : " + intNum1);
        System.out.println("shortNum1 : " + shortNum1);
    }
}