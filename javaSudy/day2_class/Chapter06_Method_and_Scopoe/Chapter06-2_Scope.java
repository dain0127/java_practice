/*
 * Chapter 06 Methode and Scope
 * 6-1 Methode
 * 6-2 Scope
 * (6-3 Recursion Methode) C와 같음
 * 
 * 6-1 Scope
 * C에서는 내부 블록에 다시 같은 이름 변수를 선언하면 바깥 변수가 "가려짐(shadowing)" → 허용됨.
 * Java에서는 로컬 변수 중복 선언은 허용하지 않음 → 더 엄격한 규칙.
 * 
 * Java에서는 동일한 메서드 블록 안에서 같은 이름의 변수를 다시 선언할 수 없다.
 * 즉, 내부 블록(if, for 등)도 같은 메서드의 로컬 스코프에 포함되므로 중복 선언 불가 → 컴파일 에러 발생.
 * 
 * 
 * 같은 메소드 내에서도, 중괄호로 scpoe를 구분하는건 사실이나, 변수명이 가려지지는 않는다.
 * 하지만 지역변수로서 해당 scpoe에서 벗어나면 메모리에서 삭제된다.
 */

class test{
    public static void main(String args[]){
        int num1;

        if(true){
            int num1; //compile error!
        }
    }
}
