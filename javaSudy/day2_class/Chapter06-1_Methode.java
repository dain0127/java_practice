/*
 * Chapter 06 Methode and Scope
 * 6-1 Methode
 * 6-2 Scope
 * (6-3 Recursion Methode) C와 같음
 * 
 * 6-1 Methode.
 * C++과 개념이 같다. 
 * java에서는 class의 entry point가 main methode이므로,
 * main에서 사용할 function를 정의하기 위해서는 같은 methode를 정의하면 된다.
 * 
 * 마찬가지로 반환값과 매개변수를 설정해줄 수 있다.
 */

 //1~100 사이의 소수를 출력하는 프로그램을 짜기 위해, IsPrime이라는 함수를 짜둔다.
 //자주 재사용되는 코드는 프로시저로 만들어두는게 좋기 떄문이다.
class test{
    public static void main(String args[]){
        for(int i=1;i<=100;i++){
            if(IsPrime(i)){
                System.out.println("prime number : "+ i);
            }
        }
    }

    public static boolean IsPrime(int n){
        boolean IsPrime = true;
        if(n == 1)
            return false;
        if(n == 2)
            return true;

        for(int i=2;i<n;i++){
            if(n%i == 0){
                IsPrime = false;
                break;
            }
        }

        return IsPrime;
    }
}
