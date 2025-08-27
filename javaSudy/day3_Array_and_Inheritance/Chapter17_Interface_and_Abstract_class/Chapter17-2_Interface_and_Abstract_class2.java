/*
 * 17. Interface 와 Abstact Class
 * 17-1. Interface의 기본과 의미
 * 17-2. Interface의 문법.
 *  [17-2-1. Interface안에서의 변수와 메소드]
 *  [17-2-2. Interface간의 상속]
 *  [17-2-3. Interface의 세가지 메소드]
 *  [17-2-4. Interface의 instanceof]
 *  [17-2-5. Maker Interface]
 * 17-3. 추상 클래스
 * 
 * 
 * [17-2-5. Maker Interface]
 * Maker interface는 말 그대로, 어떤 클래스에 '마크'를 새기는 용도로만 정의한 interface를 의미한다.
 * maker interface는 내부를 딱히 정의하지 않고, 상속만 한다.
 * 그리고 instanceof 연산자를 통해 어떤 class인지 maker interface를 통해 구현한다.
 * 
 * 
 * 17-3. 추상 클래스
 * 추상클래스는 추상메소드가 하나 이상 있는 class이다.
 * 이는 인스턴스 생성이 불가하며, abstract class, abstract method라는 키워드를 붙여야한다.
 * 즉, 상속받은 class가 하나 이상 overriding으로 구현해야하는 method가 있는 class를 말한다.
 */

import static java.lang.System.out;

interface Printable {
    String getContents();
}

//Maker interface
interface Upper{} //몸체 구현 안함. 
interface Lower{} //몸체 구현 안함.

class Printer implements Printable, Upper{
    String str;

    Printer(String s){this.str = s;}

    @Override
    public String getContents(){
        return str;
    }
}



abstract class AbstractClass{
    int x, y;
    void somethingMethod(){
        out.println("somethingMethod");
    }
    abstract void abstractMethod();
}

class DrivedClass extends AbstractClass{
    int a, b;

    @Override
    void abstractMethod() {
         out.println("abstractMethod is overriding");
    }
}


public class test {
    public static void main(String args[]){
        Printable p1 = new Printer("it's contents : ABC xyz");
        
        if(p1 instanceof Upper){
            out.println(p1.getContents().toUpperCase());
        }
        else if(p1 instanceof Lower){
            out.println(p1.getContents().toLowerCase());
        }
        else{
            out.println(p1.getContents());
        }

        out.println("================================");

        DrivedClass d1 = new DrivedClass();
        d1.abstractMethod();
    }
}
