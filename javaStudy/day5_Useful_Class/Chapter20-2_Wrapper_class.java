/*
 * Chapter 20 자바의 기본 클래스
 * 20-1 Wrapper 클래스
 * 20-2 BigInteger 클래스와 BigDecimal 클래스
 * 20-3 Math 클래스와 난수의 생성, 그리고 문자열 토큰(Token)의 구분
 * 20-4 Arrays 클래스
 * 
 * 20-1 Wrapper 클래스
 * 20-1-1 Wrapper 클래스
 * Wrapper 클래스 : 기본 자료형을 감싸며, 여러 유용한 기능을 제공하는 클래스이다.
 * 그리고 중요한건 ★★★값을 '인스턴스화' 할 수 있다는 것이다!★★★
 * int => Integer
 * boolean => Boolean
 * char => Character
 * double => Double
 * ...
 * 
 * 래퍼 클래스에는 두가지 기능이 있다
 * 1) boxing : 값을 인스턴스로 감싸는 것. (WrapperClassName.valueOf("123"))
 * 2) unboxing : 인스턴스에서 값을 반환하는 것. (기본형이름Vlaue()로 구현됨)
 *  
 * WrapperClass에는 여러개의 유용한 기능이 있다.
 * 자세한건 아래 코드의 주석을 읽으며 확인해보자.
 * 
 * 20-1-2 auto boxing/unboxing
 * auto boxing이 있고, auto unboxing이 있는데,
 * Wrapper 클래스의 인스턴스를, 기본 자료형 계산하듯이 연산자를 적용했을때,
 * 컴파일러가 알아서 해당 코드를, '적절하게' 박싱과 언박싱을 통해 연산하는걸 의미한다.
 * 
 * 20-1-3 Number class 그리고 Wrapper class의 여러 static method
 * Number class는 아래와 같은 추상 메소드들이 존재한다.
 * 
 * public abstrct int intValue()
 * public abstrct long longValue()
 * public abstrct double doubleValue()
 * 
 * 그리고 정수형 Wrapper class들도 유요한 static method들이 아래와 같이 존재한다.
 * valueOf(여러 인자형)
 * max(), min(), sum(), toBinaryString(), toOctalString(), toHexString()
 * 자세한건 아래 참고
 */

import static java.lang.System.out;
import java.lang.Number; //숫자형 Wrapper class가 상속받는 메소드.

class test{
    static void printObj(Object obj) {
        out.println(obj.toString() + ": it's instance!");
    }

    public static void main(String[] args) {
        /* 20-1-1 Wrapper class */
        Integer iObj = Integer.valueOf("123"); //Integer형 반환. String -> Integer
        int num1 = iObj.intValue(); //int형 반환. Unboxing.
        int num2 = Integer.parseInt("456"); //int형 반환. String -> int
        out.println(num1);
        out.println(num2);
        
        Character cObj = Character.valueOf('A');
        char ch1 = cObj.charValue();
        out.println(ch1);
        out.println();

        //래퍼 클래스의 값 증가 방법
        iObj = Integer.valueOf((iObj.intValue()+1000));

        //값을 인스턴스화 해야하는 이유.
        printObj(iObj); //toString() 호출 가능한 형태로 값을 넘겨줌
        out.println();
        
        /* 20-1-2 auto boxing/unboxing */
        iObj++; //iObj = Integer.valueOf((iObj.intValue()+1));
        iObj+=10; //iObj = Integer.valueOf((iObj.intValue()+10));
        int num3 = iObj + 3;
        Integer iObj2 = iObj + 4;
        out.println(num3);
        out.println(iObj2);
        out.println();

        /* 20-1-3 Number class 그리고 Wrapper class의 여러 static method*/
        Integer n1 = Integer.valueOf(10);
        Integer n2 = Integer.valueOf("999");

        int intn1 = n1.intValue();
        double doun1 = n1.doubleValue();
        long lonn1 = n1.longValue();

        out.println(intn1);
        out.println(doun1);
        out.println(lonn1);

        double a = 3.1;
        double b = 5.4;

        out.printf("%f vs %f = max : %f, min : %f\n",
        a,b, Double.max(a,b), Double.min(a,b));
        out.printf("bin : %s, oct : %s, hex : %s\n",
            Integer.toBinaryString(n2), Integer.toOctalString(n2), Integer.toHexString(n2));
    }
}
