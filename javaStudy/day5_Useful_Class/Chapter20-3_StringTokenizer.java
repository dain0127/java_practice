/* Chapter 20 자바의 기본 클래스
 * 20-1 Wrapper 클래스
 * 20-2 BigInteger 클래스와 BigDecimal 클래스
 * 20-3 Math 클래스와 난수의 생성, 그리고 문자열 토큰(Token)의 구분
 * 20-4 Arrays 클래스
 * 
 * 20-3 Math 클래스와 난수의 생성, 그리고 문자열 토큰(Token)의 구분
 * 1) Math는 예제 참고
 * 
 * 2) Random는 난수를 생성할때 사용하는 클래스이다.
 * seed를 통해 난수가 결정되는 판이 바뀐다는 것 또한 C와 똑같다.
 * java의 Random() void 생성자는 내부적으로 System의 시간을 나노단위로 seed를 받는다.
 * 
 * 3) 토크나이저. String을 구분자(Delimiter)로 구별하여,
 * 여러개의 String을 token으로서 반환받는 기능을 제공하는 클라스
 * 
 * 자세한건 예제 참고.
 * 
 */

import static java.lang.System.out;

import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;


class test{
    public static void main(String[] args) {
        //=============Math class==============
        Math.min(0, 0);
        Math.sqrt(10);
        Math.abs(-10);
        final double PI = Math.PI;
        final double E = Math.E;
        Math.sin(E);
        Math.cos(PI);
        Math.log(Math.pow(E, 10));

        //============Random class=============
        Random rand = new Random();

        out.println(rand.nextInt());
        out.println(rand.nextBoolean());
        out.println(rand.nextDouble());
        out.println(rand.nextFloat());
        out.println(rand.nextLong());
       
        //pseudo random을 구현하기 위한 나노단위 시간 적용.
        //seed가 시각마다 달라지기 때문에, 실행할때마다 달라짐.
        /*
         * public Random() {
         *      this(seedUniquifier() ^ System.nanoTime());
         * }
         */

        //Quiz
        Scanner sc = new Scanner(System.in);
        out.print("A : "); int a = sc.nextInt();
        out.print("B : "); int b = sc.nextInt();
        for(int i=0;i<10;i++)
            out.print(Math.abs(rand.nextInt()%(b-a+1)) + a + " ");
        out.println();

        //==============문자열의 토큰(token)구분=============
        StringTokenizer st = new StringTokenizer("hello world", " ");
        String temp;
        while (st.hasMoreTokens()) {
            temp = st.nextToken();
            out.println(temp);
        }

        //구분자를 여러개 쓰는 방법
        st = new StringTokenizer("a+b/c%d-e*f", "+-*/%");
        while (st.hasMoreTokens()) {
            out.println("token count : " + st.countTokens());
            out.println(st.nextToken());
        }
        out.println();

        //구분자를 생략하지 않고 토큰화 하는 방법
        st = new StringTokenizer("aa++bb//cc%%dd--ee**ff","+-*/%", true);
        while (st.hasMoreTokens()) {
            out.println("token count : " + st.countTokens());
            out.println(st.nextToken());
        }
    }
}
