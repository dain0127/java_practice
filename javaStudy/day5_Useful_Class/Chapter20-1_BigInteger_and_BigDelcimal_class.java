/* Chapter 20 자바의 기본 클래스
 * 20-1 Wrapper 클래스
 * 20-2 BigInteger 클래스와 BigDecimal 클래스
 * 20-3 Math 클래스와 난수의 생성, 그리고 문자열 토큰(Token)의 구분
 * 20-4 Arrays 클래스
 * 
 * 
 * 20-2 BigInteger 클래스와 BigDecimal 클래스
 * BigInterger 클래스는 매우 큰 정수를 저장하기 위해 만들어진 클래스이다.
 * BigDecimal 클래스는 보다 정확한 실수를 저장하기 위해 만들어진 클래스이다.
 * 
 * 근본적으로 int형은 4byte이므로, 2^32가지로 표현할 수 있는 정수 범위만 커버가 가능하다. (2147483647 ~ -2147483648)
 * 또한 근본적으로 double형은 8byte이므로, 2^64가지로 표현할 수 있는 실수만큼이나 정확히 커버 가능하다. (1/11(e)/52(m))
 * 
 * 여러 static 변수나 method들은 아래 코드와 주석 참고
 * 
 */

import static java.lang.System.out;

import java.math.BigDecimal;
import java.math.BigInteger;

class test{
    public static void main(String[] args) {
        //Big Integer
        out.println("==========Big Integer==========");
        BigInteger bInt1 = new BigInteger(Long.valueOf(Long.MAX_VALUE).toString() + "0000");
        BigInteger bInt2 = new BigInteger(bInt1.toString());

        long n1 = Long.MAX_VALUE*10000; //프로그래머가 의도하지 않은 값이 나온다.
        out.printf("long n1 : %d\n", n1);

        out.printf("BigInteger bInt1 : %d\n", bInt1);
        out.println("add : " + bInt1.add(bInt2));
        out.println("subtract : " + bInt1.subtract(bInt2));
        out.println("multiply : " + bInt1.multiply(bInt2));
        out.println("divide : " + bInt1.divide(bInt2));
        out.println("remainder : " + bInt1.remainder(bInt2));
        out.println();

        //Big Decimal 
        out.println("==========Big Decimal==========");
        BigDecimal bDec1 = new BigDecimal("1.6");
        BigDecimal bDec2 = new BigDecimal("0.1");
        
        out.printf("double : %.16f\n", (1.6 + 0.1)); //1.7의도. 하지만 8byte의 한계 때문에 오차가 나옴.
        
        out.printf("BigDecimal : %.16f\n", bDec1.add(bDec2));
        out.println(bDec1.multiply(bDec2));

    }
}
