/*
 * Chapter 13 Array
 * 13-1 Array에 대한 각종 문법
 * 13-2 enhanced for문
 * 13-3 다차원 배열
 * 
 * 13-2 enhanced for문
 * python에서 쓰는 그 문법하고 완전히 같다.
 * 배열에 있는 모든 요소에 순차적으로 접근하고 싶을때.
 * for(Type e : arr){...} 로 접근하는 것이다.
 * (이는 for(int i=0;i<arr.length;i++){...}과 완전히같다.)
 * 
 */

import static java.lang.System.out;

//int[]에서 최대값과 최소값을 반환하는 함수를 각각 만드시오.
//단, enhanced for을 이용해야함.
class test{
    public static void main(String[] args){
        int[] arr = new int[]{1,3,2,5,9,4};
        out.println(maxValue(arr));
        out.println(minValue(arr));
    }

    static int maxValue(int[] arr){
        int max = arr[0];
        for(int e : arr)
            max = max < e ? e : max;
        return max;
    }
    
    static int minValue(int[] arr){
        int min = arr[0];
        for(int e : arr)
            min = min > e ? e : min;
        return min;
    }
}


