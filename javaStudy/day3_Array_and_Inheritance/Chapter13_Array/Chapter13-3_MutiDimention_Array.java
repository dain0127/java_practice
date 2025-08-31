/*
 * Chapter 13 Array
 * 13-1 Array에 대한 각종 문법
 * 13-2 enhanced for문
 * 13-3 다차원 배열
 *
 * 13-3 다차원 배열.
 * java또한 2차원 배열이, 1차원 배열을 요소로하는 1차원 배열에 불과하다는건 다르지 않다.
 * 다만, java의 2차원 배열은 C/C++과 다르게, 각 요소인 1차원 배열의 길이가 달라도 된다.
 * 이는 2차원 배열의 참조형인 java가 실은, '1차원 배열의 참조형 변수를 배열로 저장하는' 1차원 배열 참조형이기 때문이다.
 * (2차원 배열 참조형 변수) ㅁ ===>ㅁ ---> ㅁㅁㅁ
 *                              ㅁ ---> ㅁㅁㅁㅁㅁ
 *                              ㅁ ---> ㅁ
 * 이런식으로 heap영역 상에 존재할 수 있다.
 */

import static java.lang.System.out;

class test{
    public static void main(String[] args){
        int[][] arr = new int[][]{{1},{2,3},{4,5,6}};
        for(int[] ar1 : arr){
            for(int e : ar1 ){
                out.print(e+" ");
            }
            out.println();
        }
    }
}


