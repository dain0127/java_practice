/*
 * Chapter14 Inheritance and Overriding.
 * 14-1 상속(Inheritance)의 기본 문법
 *  14-1-1. 클래스 변수와 클래스 메소드의 상속(static 변수와 메소드)
 *  14-1-2. 상속과 참조변수의 참조 가능성
 * 14-2. 메소드 오버라이딩(Overriding)
 * 14-3. instanceof 연산자
 * 14-4. Object class
 * 14-5. Class와 method의 final 선언.
 * 14-6. @Override.
 * 
 * 
 * 
 * 14-4. Object class
 * Object class는 모든 class를 직접, 간접적으로 상속하고있다.
 * 즉, Object class는 모든 class의 조상이다.
 * 
 * 만약 아무것도 상속 받지 않은 BaseClass가 있다면, 이는 실은 Object를 상속받은 것이다.
 * 
 * Object 클래스는 toString을 method로 가지고 있다
 * (이전에 println(Object obj) 오버로딩 함수가 있음을 상기해라.)
 * (우리가 String이 아니라, 인스턴스 자체를 넘길때 내부적으로 toString()을 반환한 값을 출력해주지 않았는가.)
 * 
 * 14-5. Class와 method의 final 선언.
 * final의 의미대로이다.
 * Class에 final 선언을 하면, 더 이상 상속하지 않는다는 의미이다. 
 * method에 final 선언을 하면, 더 이상 오버라이딩하지 않는다는 의미이다.
 * 
 * 14-6. @Override. (어노테이션 중 하나)
 * 메소드 위에 @Override라는 걸 붙이면, 이 메소드는 @Override 하는 메소드임을 컴파일러에게 알리는 것이다.
 * 만약 메소드 오버라이딩을 개발자가 실수했다면, 컴파일러는 에러를 내어, 오버라이딩을 하라고 알려준다.
 * (@Override를 제외하면, 컴파일러 입장에서는 문법적으로 다른 함수로의 '오버로딩'으로 인식해 오류를 내지 않는다.)
 * 
 */


import static java.lang.System.out;

import java.lang.Object;

class BaseClass extends Object{
    //14-4 Object class의 toString method overridng
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    //14-5 final method
    final public void finalMethod(){}

    //14-6 @Override annotation
    public void annotationFun(){}
}

final class DrivedClass extends BaseClass{
    //14-5 final method
    public void finalMethod(){} //compile error

    //14-6 @Override annotation
    @Override
    public void annotationFun(int n){} //compile error
}


//14-5. final class

class DoubleDrivedClass extends DrivedClass //compile error
{}

class test{
    public static void main(String args[]){
    }
}