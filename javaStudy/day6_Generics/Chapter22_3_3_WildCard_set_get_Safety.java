/*
 * Chapter 22 제네릭 2
 * 22-1 제네릭 클래스와 상속
 * 22-2 타겟 타입
 * 22-3 와일드 카드
 *      22-3-1 와일드 카드란 무엇인가
 *      22-3-2 와일드 카드의 상한/하한 제한(extends/super)
 *      22-3-3 와일드 카드의 상한/하한 제한의 ★★★★★목적★★★★★
 *      22-3-4 제한된 와일드 카드 선언을 갖는 제네릭 메소드
 * 22-4 제네릭 인터페이스의 정의와 구현
 * 
 * 
 * 22-3 와일드 카드
 * 22-3-2 와일드 카드의 상한 제한(extends). 그리고 ★목적★
 * 22-3-3 와일드 카드의 하한 제한(super). 그리고 ★목적★
 * 
 * 우선 아래와 같은 java코드가 어떤 상황에 어떤 이유로 쓰였는지 '깊이 이해하는 것'이 이번 챕터의 목표이다.
 * (사실 이해하는 것도 중요하지만. 동시에 외우고. 익숙해지기도 해야한다.)
 * Box<? extends Toy> : 상한 제한. 구체적인 것들은 준다. Toy의 set을 위한 것이다.
 * Box<? super Toy> : 하한 제한. 추상적인 것들은 받는다. Toy의 get을 위한 것이다.
 * 
 * 그리고 위와 같은 문법을 구사하는 이유는, 프로그램의 논리에 어긋나는 코드를, '컴파일러가 찾도록' 책임을 넘기기 위해서이다.
 * 즉, get과 set method에 해당하는 코드의 '안정성'을 보장해준다.
 * 
 * 
 * 그리고 '안전하게' 구현된 '제네릭' 클래스는 아래와 같이 구현된다.
 * <T> void outBox(Box<? extends T> box)
 * <T> void inBox(Box<? super T> box, T ob)
 * 
 * 
 */

import static java.lang.System.out;

class Box<T>{
    private T ob;
    public T get(){return ob;}
    public void set(T o){this.ob = o;}

    @Override
    public String toString(){
        return ob.toString();
    }
}

class Toy{
    @Override
    public String toString(){
        return "I'm a toy";
    }
}

class Robot{
    @Override
    public String toString(){
        return "I'm a Robot";
    }
}

//안전하지 않은 박스 핸들러
class BoxToyHandlerNoSafety{
    public static void outBox(Box<Toy> box){
        Toy toy = box.get();
        out.println(toy);
        box.set(toy); //이게 컴파일 에러가 안나?
    }

    public static <Toy> void inBox(Box<Toy> box, Toy o){
        box.set(o);
        Toy toy = box.get();//이게 컴파일 에러가 안나?
    }
}

//안전한 박스 핸들러
class BoxToyHandlerSafety{
    public static void outBox(Box<? extends Toy> box){
        Toy toy = box.get();
        out.println(toy);
        box.set(toy); //컴파일 에러! 실수를 찾아줘서 고마워!
    }

    public static void inBox(Box<? super Toy> box, Toy toy){
        box.set(toy);
        Toy toy = box.get(); //컴파일 에러! 실수를 찾아줘서 고마워!
    }
}

//get과 set을 안전하게 사용하기 위한, 와일드 카드 상한/하한 제한 문법의 활용 예시
class BoxStringConverter{
    public static void convertString(Box<? super String> to, Box<? extends String> from){
        to.set(from.get());
        to.get(from.set())//컴파일 에러! 실수를 찾아줘서 고마워!
    }
}


//★★★★★안전하게 정의된 메소드. 그리고 제너릭으로 일반화하는 방법★★★★★
class BoxHandlerSafety{
    public static <T> void outBox(Box<? extends T> box){
        T ob = box.get();
        out.println(ob);
        box.set(toy); //컴파일 에러! 실수를 찾아줘서 고마워!
    }

    public static <T> void inBox(Box<? super T> box, T ob){
        box.set(ob);
        Toy toy = box.get(); //컴파일 에러! 실수를 찾아줘서 고마워!
    }
}

class test{
    public static void main(String[] args){
        //toy 한정 안전하지 않은 클래스, 안전한 클래스
        Box<Toy> box1 = new Box<>();
        Toy toy1 = new Toy();

        BoxToyHandlerNoSafety.inBox(box1, toy1);
        BoxToyHandlerNoSafety.outBox(box1);

        BoxToyHandlerSafety.inBox(box1, toy1);
        BoxToyHandlerSafety.outBox(box1);
        out.println();



        //안전하게 String 주고받게 해주는 클래스
        Box<String> sBoxOld = new Box<>();
        Box<String> sBoxNew = new Box<>();
        sBoxOld.set("old String");
        sBoxNew.set("new String");
        BoxStringConverter.convertString(sBoxOld, sBoxNew);
        out.println(sBoxOld);
        out.println();


        //안전하게 일반화한 제너릭 클래스
        Box<Robot> rBox = new Box<>();
        BoxHandlerSafety.inBox(rBox, new Robot());
        BoxHandlerSafety.outBox(rBox);
    }
}