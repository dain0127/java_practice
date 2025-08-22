/*
 * Chapter 05 반복문과 조건문
 * 기본적으로 C와 동일하다. 사실상 완벽히 같다.
 * 그러니깐 설명을 줄인다.
 * 
 * 유일한 차이점이라면 조건문에서 java는 반드시 boolean 타입을 써야한다.
 * C는 아무거나 가능하다.
 */

class test{
    public static void main(String args[]){
        int count = 10;

        //compile error
        /* 
        while(count){
            count--;
        }
        */

        while(count > 0){
            count--;
            System.out.println(count);
        }
    }
}