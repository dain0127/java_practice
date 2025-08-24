/*
 * 11. Method Overloading과 String Class
 * 
 * 11-1. Method Overloading
 * 11-2. String class
 * 11-3. String class method
 * 
 * 11-3. String class method
 * 
 * 
 * 
 */

import static java.lang.System.out;

public class test {
    public static void main(String args[]){
        String str1 = "Hello";
        String str2 = "World";
        String result;
        
        //concatenating. 이어붙이기
        result = str1.concat(str2);
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
        
    }    
}
