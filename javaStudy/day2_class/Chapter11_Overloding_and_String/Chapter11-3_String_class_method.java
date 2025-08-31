/*
 * 11. Method Overloading과 String Class
 * 
 * 11-1. Method Overloading
 * 11-2. String class
 * 11-3. String class method
 * 
 * 11-3. String class method
 * String에 여러 method를 실습한다.
 * StringBuilder class. String data를 조작하기 쉽게 하기 위한 class이며,
 * 내부적으로 문자열을 저장할 수 있는 공간이 있다.
 */

import static java.lang.System.out;

public class test {
    public static void main(String args[]){
        String str1 = "Hello";
        String str2 = "World";
        String result;
        
        //concatenating. 이어붙이기. 당연하지만, chaining rule 적용 가능.
        result = str1.concat(str2);
        System.out.println(result);
        result = str1.concat(str2).concat(str2);
        System.out.println(result);
        System.out.println();

        //substring. 자르기
        result = str1.substring(3);
        System.out.println(result);
        result = str1.substring(1,4);
        System.out.println(result);
        System.out.println();

        //comparing. 문자열 '내용' 비교하기.
        str1 = "Hello";
        str2 = "Hello";
        //같은지 다른지만 따짐
        if(str1.equals(str2))
            System.out.println("same string! ");
        else
            System.out.println("diff string! ");

        //사전순여부로(compareTo) 양수, 0, 음수 반환.
        str1 = "ABC";
        str2 = "XYZ";
        if(str1.compareTo(str2)> 0)
            System.out.println("str1 is first");
        else
            System.out.println("str1 is not first");

        //대소문자 무시 사전순으로(compareToIgnoreCase), 이하동문.
        str1 = "abc";
        str2 = "XYZ";
        if(str1.compareTo(str2)> 0)
            System.out.println("str1 is first");
        else
            System.out.println("str1 is not first");
        System.out.println();

        if(str1.compareToIgnoreCase(str2) > 0)
            System.out.println("str1 is first");
        else
            System.out.println("str1 is not first");
        System.out.println();
        
        //다른 기본 자료형의 값을 문자열로 바꾸기(valueOf)
        String tStr = String.valueOf(true);
        String fStr = String.valueOf(false);
        System.out.println(tStr);
        System.out.println(fStr);
        out.println();

        //+연산자와 +=연산자의 오버로딩의 정체는 String.concat()
        str1 = "hello ";
        str2 = str1+" world"; //str2 = str1.concat("world");
        System.out.println(str2);
        str2 += str1;
        out.println(str2);
        out.println();

        //Quiz1 990925-101299에서 삽입된 -를 지우고 공백을 출력하게 해라.
        String input = "990925-101299";
        result = input.substring(0, 6) + ' ' + input.substring(7,13);
        out.println(result);
        out.println();

        //StringBuilder class. String data를 조작하기 쉽게 하기 위한 class이며,
        //내부적으로 문자열을 저장할 수 있는 공간이 있다.
        StringBuilder strBulider = new StringBuilder("123");
        strBulider.append(456); //123456
        out.println(strBulider.toString()); //3456
        strBulider.delete(0,2);
        out.println(strBulider.toString());
        strBulider.replace(0,2,"ABC");//ABC56
        out.println(strBulider.toString());
        strBulider.reverse();//65CBA
        out.println(strBulider.toString());
        str1 = strBulider.substring(1,3);
        out.println(str1);
        out.println();

        //Quiz2 StringBuilder를 이용해서 990925-101299에서 삽입된 -를 지우고 공백을 출력하게 해라.
        strBulider = new StringBuilder("990925-101299");
        strBulider.replace(6,7," ");
        out.println(strBulider.toString());
        
    }    
}
