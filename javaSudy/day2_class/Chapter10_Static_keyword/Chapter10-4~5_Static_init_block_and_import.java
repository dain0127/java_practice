/*
 * Chapter10 static keyword
 * 10-1 static 맴버변수
 * 10-2 static 메소드
 * 10-3 System.out.println()과 public static void main()의 정체
 * 10-4 static 초기화 블록
 * 10-5 static import
 * 
 * 
 * 10-4 static 초기화 블록
 * static 초기화 블록은, static 변수를 초기화하는 블록이다.
 * 이것이 별도로 필요한 이유는, static 변수의 경우, 인스턴스가 생성되기 이전에, class loading과정에서 초기화가 필요하기 때문.
 * 만약에 static 변수의 초기화 과정이, static int num = 0;처럼 단문장으로 충분하다면 상관이 없지만...
 * 
 * 하술할 예제처럼, static변수의 초기화 과정이 단문장으로 표현이 안되는 경우에는 '코드 블럭'을 확보해야한다.
 * 
 * 그리고 그것이 static 초기화 블록(static initialization block)이다.
 * 
 * 
 * 10-5 static import
 * static import는 별거 없다.
 * 
 * 앞서 import가 특정 package에 있는 class를 using(영역의 경로를 전부 적었다고 간주)할 수 있도록 하지 않았는가?
 * static import는 거기에 한 발 더 앞서가서, static 변수와 메소드를 using하는 것이다.
 * 
 * 자세한건 아래 예제 참고.(겁나 자주쓰이는 static 클래스 변수인, System.out이 using된걸 보면 이해가 될 것이다.)
 */ 


//10-5 staic import. 맴버 변수나 메소드를 using(풀네임을 썼다고 간주)해준다.
import static java.lang.System.out;
import static java.lang.Math.*; //PI, E. Math class의 모든 static 변수, 메소드가 using되었다.


import java.time.LocalDate;

class test{
    //10-4 static initailzation block.
    //날짜를 static 변수로서 저장할때, 코드라인이 길어 static 초기화 변수가 필요하다.
    static String date = "";
    static {
        LocalDate Ldate = LocalDate.now();
        date = Ldate.toString();
    }

    public static void main(String args[]){
        //static import practice
        out.println("hello static import");
        out.println("Math.PI : " + PI);
        out.println("Math.E : " + E + '\n');

        //static initalization block practice
        out.println("today is : " + date); 
    }
}