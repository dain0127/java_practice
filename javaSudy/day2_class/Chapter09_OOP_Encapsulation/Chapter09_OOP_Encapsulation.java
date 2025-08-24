/*
 * Chapter09 Encapsulation 
 * 
 * 정보은닉/캡슐화
 * 
 * <9-1 정보 은닉>
 * 정보 은닉이란, '외부'에서 field를 쉽게 수정하지 못하도록, 접근을 제한하는 것을 의미한다.
 * 그리고 해당 맴버 변수는 method를 통해 '안전하게' '정해진 방식으로' 접근 할 수 있도록 한다.
 * 
 * 정보 은닉은 java에서 제공하는 접근 수준 지시자 문법을 통해 구현된다.
 * 
 * <9-2 접근 수준 지시자>
 * 접근 수준 지시자(Acess-level Modifiers)
 * public, protected, defualt, private
 * 
 * class 대상 : public, defualt 가능.
 * field와 method 대상 : public, protected, defualt, private 가능.
 * (여기서 defualt는 키워드가 아니라, 접근지시자를 사용하지 않은 상태를 의미한다.)
 * (C++에서 class내부에서 자동으로 private 취급하는거랑 살짝 다름.)
 * 
 * <9-2-1 class 대상의 접근 수준 지시자> 
 * C++하고 다르게 java는 class에서도 접근 수준을 정할 수 있다. (public, defulalt)
 * 
 * class에서 defulat는 내부 패키지에서만 인스턴스 생성이 가능하다.
 * class에서 public은 모든 패키지에서 인스턴스 생성이 가능하다.
 * 
 * ===============실습 방법 9-2-1=================
 * (9-2-1_class_mutil-access_level 디렉토리.)
 * 1. src에 있는 소스코드를 분석한다.
 * 2. run_class_multi-access_level_test.bat 를 실행시키고, 컴파일 에러를 분석한다.
 * 
 * <9-2-2 field와 method 대상의 접근 수준 지시자>
 * public, protected, defualt, private가 가능하다.
 * 근데 별거 없다 C++랑 똑같다.
 * defulat또한 마찬가지. 
 * 
 * 표로 만들어주면 다음과 같다.
 *               자기자신 클래스 내부    자식 클래스     같은 패키지     어디든지
 * private   :          O                 X             X           X
 * protected :          O                 O             X           X
 * defualt   :          O                 O             O           X
 * public    :          O                 O             O           O
 * 
 * ===============실습 방법 9-2-2=================
 * 1. 코드를 분석한다. 그리고 그들간의 pacakge, 상속관계 등을 파악한다.
 * 2. run_field_method_muti-access_level.bat 을 분석하고 실행시켜, 컴파일 에러를 분석한다.
 * 3. 주석에 X쳐진 부분이 컴파일 에러가 났음을 확인한다.
 * 
 * <9-3 캡슐화>
 * 캡슐화랑 한가지 목적을 위해 class에 필요한 것을 모아둔 것을 의미한다.
 * OOP 개념.
 * 
 */

class Circle{
    private double rad = 0; //외부에서 접근 불가.
    final double PI = 3.14; //상수이므로 초기화시 수정 불가.

    public Circle(double r){rad = r;}
    public void setRad(double r){
        if(rad >= 0)
            rad = r;
        else
            rad = 0;
    }
    public double getRad(){return rad;}
    public double getArea(){return rad*rad*PI;}
}

class test{
    public static void main(String args[]){
        Circle cir = new Circle(10);
        System.out.println(cir.getArea());

        cir.setRad(20);
        System.out.println(cir.getArea());
    }
}