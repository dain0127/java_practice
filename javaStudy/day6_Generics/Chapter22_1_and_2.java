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
 * 
 * 22-1 제네릭 클래스와 상속
 * 제네릭 클래스도 상속이 가능하다.
 * 사실 제네릭 클래스라고 해봤자 T만 일반화 했을뿐, 얘도 클래스이다.
 * 그냥 클래스 상속하듯이 상속하면 된다.
 * 
 * 다만 주의해야하는 점은, 같은 타입 인자값 T에 대해서만 상속관계를 가진다는 것이다.
 * 예를 들어, class SteelBox<T> extends Box<T> 라면
 * SteelBox<String> -> Box<String>
 * SteelBox<Integer> -> Box<Integer> 라는 의미이다.
 * 
 * 
 * 22-2 타겟 타입
 * 이전 코드 Chapter21-1-2-1 제네릭 베이직 문법에 대해서 보면,
 * 우리가 타입 인자값을 생략해도 되는 경우는 두가지였다.
 * 1) 제네릭 class 참조값을 인스턴스 생성으로 초기화할때,
 * 좌변의 참조변수의 타입 인자값(T)을 보고, 다이아몬드 기호(<>)로 써도, 우변의 타입 인자값(T)을 컴파일러가 채워주는 경우.
 * 2) 제네릭 메소드를 호출할때, 
 * 메소드의 인자값(int or double or String ...)을 보고, 타입 인자값(Integer or Double or Stirng ...)에서 가능한 걸,
 * 컴파일러가 알아서 채워주는 경우
 * 
 * 그런데 java 7이 되면서 컴파일러가 이것까지 추측이 가능하게 해주었다.
 * Box<Integer> ibox1 = EmptyBoxFactory.makeBox();
 * //Box<Integer> ibox1 = EmptyBoxFactory.<Integer>makeBox();랑 같음.
 * 
 * 즉, 2)처럼 제네릭 메소드의 인자 타입이 없어, 어떤 제네릭 메소드를 해야할디 컴파일러가 추측이 불가능할때.
 * 1)처럼 좌변에 있는 참조 변수의 타입 인자값(T) '까지' 보고, 제네릭 메소드의 타입 인자값을 컴파일러가 결정해준다는 것이다.
 * 
 * 이때 컴파일러가 타입 인자값을 추측하기 위해, 참조한 타입(여기서는 Box<Integer>)를 '타깃 타입'이라고 한다.
 * 
 */

import static java.lang.System.out;
 
class Box<T>{
    protected T ob;
    public void set(T o){this.ob = o;}
    public T get(){return ob;}
}

class SteelBox<T> extends Box<T>{
    public SteelBox(T o){this.ob = o;}
}

class EmptyBoxFactory{
    public static <T> Box<T> makeBox(){
        Box<T> temp = new Box<>();
        return temp;
    }
}

class test{
    public static void main(String[] args){
        //22-1 제네릭 클래스와 상속
        Box<Integer> iBox = new SteelBox<>(10);
        Box<String> sBox = new SteelBox<>("hello");
        out.println(iBox.get());
        out.println(sBox.get());
        out.println();

        //22-2 타깃 타입
        Box<Double> dBox = EmptyBoxFactory.makeBox();//이제는 제네릭 메소드조차, 컴파일러가 왼편의 타깃 타입을 보고, 타입 인자값을 추측해서 넣어준다.
        dBox.set(0.1);
        out.println(dBox.get()); 
    }
}