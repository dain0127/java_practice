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
 * 22-4 제네릭 인터페이스의 정의와 구현
 * 인터페이스도 문법적으로 보면 사실 클래스이다.
 * 인스터스화 안되고, 추상 메소드같은 것만 가지고 있는... 그런 종류의 클래스나 마찬가지다.
 * 
 * 그러니깐 여느때처럼, 클래스한테 가능하던 제네릭 클래스와 같이.
 * 인터페이스에서도 마찬가지로 제네릭 인터페이스가 가능하다.
 * 
 */

import static java.lang.System.out;

interface Getable<T> {
    public T get();
}

class Box<T> implements Getable<T>{
    private T ob;
    public T get(){return ob;}
    public void set(T o){this.ob = o;}

    @Override
    public String toString(){
        return ob.toString();
    }
}

class test{
    public static void main(String[] args){
        Box<String> box1 = new Box<String>();
        box1.set("one");

        Getable<String> ga1 = box1;
        out.println(ga1.get());

    }
}