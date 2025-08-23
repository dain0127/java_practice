/*
 * Chapter 06 Methode and Scope
 * 6-1 Methode
 * 6-2 Scope
 * (6-3 Recursion Methode) C와 같음
 * 
 * 6-3 재귀 함수
 * C++과 개념은 같다.
 * 그러다보니 여기서는 재귀 함수로 문제해결하는 방법에 대해서 알려주려고 한다.(3학년 1학기 algoritm 강의 참고)
 * 
 * 재귀함수를 사용할 수 있는 경우는,
 * scale이 큰 복잡한 문제를, scale이 작은 간단한 문제로 치환할 수 있을때 사용한다.
 * 그리고 scale이 가장 작은 문제는 해결될거라고 '믿고' 재귀함수로 문제를 해결한다.
 * 
 * scale이 가장 작은 문제는 base case라고 불리운다. base case는 재귀 호출의 마지막 종착지이며,
 * 무한 호출에 빠지지 않기위한 종료조건이다.
 * 
 * 
 * 아래 문제는 10진수를 2진수로 출력하는 과정이다.
 * 재귀함수에는 호출하는 시점이 있고, 재귀함수의 기능을 실행하는 시점이 있다.
 * 이것의 전, 후 시점을 어떻게 정하냐따라, 재귀함수 전체의 기능이 달라진다.
 * (알고리즘에서 tree순회를 재귀함수로 구현할때를 참고해라. 그저 순서의 차이가 기능의 차이가 된다.)
 */

//10진수를 2진수로 변환하는 코드
class test{
    public static void main(String args[]){
        showDeciToBin(10000);
    }

    public static void showDeciToBin(int n){
        int bin=0;
        if(n>0){
            bin = n%2;
        }
        else
            return;
        
        
        showDeciToBin(n/2);
        System.out.print(bin);
    }
}
