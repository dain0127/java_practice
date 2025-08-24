/*
 * 11. Method Overloading과 String Class
 * 
 * 11-1. Method Overloading
 * 11-2. String class
 * 11-3. String class method
 * 
 * 11-2. String class
 * String class는 기본적으로 C++에서는 char* 꼴로 저장하며,
 * 갖가지 도움이 되는 메소드를 추가적으로 캡슐화하여 wrapper class로 만든 것이다.
 * 
 * java도 마찬가지일 것이다.
 * 
 * java에서 String은 class이긴 하지만, 고전적인 생성자 방식 뿐만아니라, 다른 방식의 초기화 방식도 존재한다.
 * 1. String str1 = new Strin("hello")
 * 2. String str2 = "hello"
 * 
 * 1번 방식은 heap segment에 생성되어. 변경할 수 있는 문자열이지만.
 * 2번 방식은 data segment에 생성되어. 변경할 수 없는 상수 문자열이 된다.
 * 
 * 
 * 번외로 String을 switch문의 option으로 삼을 수 있는 문법적 기능이 있다.
 * 
 */

public class test {
    public static void main(String args[]){
        String str1 = "hello"; //상수 문자열
        String str2 = "hello"; //상수 문자열. 같은 address를 가짐.

        String str3 = new String("hello");  //변수 문자열. heap에 별도로 할당되어 존재.
        String str4 = new String("hello");  //마찬가지.
        
        ///참조 변수의 ==연산자는 내용의 비교가 아닌 address의 비교이다.
        if(str1 == str2){
            System.out.println("same address"); //same
        }
        else{
            System.out.println("different address");
        }


        if(str3 == str4){
            System.out.println("same address");
        }
        else{
            System.out.println("different address"); //diff
        }
        System.out.println();

        //번외.
        //switch문은 String으로도 가능하다.
        String optStr = "yes";
        switch (optStr) {
            case "yes":
                System.out.println("YES!!!");
                break;
            case "no":
                System.out.println("NO!!!");
                break;
            default:
                break;
        }
    }    
}
