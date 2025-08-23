/*
 * Chapter07 class and instance
 * 7-1 class의 정의와 instance의 생성
 * 7-2 Constructor와 String class
 * 7-3 java의 Naming Rule
 * 
 * 7-3 java의 Naming Rule
 * class name : ClassName(카멜 케이스)
 * method 또는 variable name : showInfo() 또는 myName(변형된 카멜 케이스)
 * constant number(상수) name : PI (대문자)
 */


//클래스명은 카멜케이스
class CricleClass {
    final double PI = 3.14; //상수는 대문자
    int circleRadious = 10; //변수는 변형된 카멜 케이스

    //메소드 또한 변형된 카멜 케이스
    double calculateArea(int r){
        return r*r*PI;
    }
}

class test{
    public static void main(String args[]){
    }
}