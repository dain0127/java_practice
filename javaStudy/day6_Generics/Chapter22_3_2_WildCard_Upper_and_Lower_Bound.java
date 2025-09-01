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
 * 22-3-2 와일드 카드의 상한/하한 제한(extends/super)
 * 와일드 카드 또한, 상한/하한 제한이 있다.
 * 어려운 개념은 아니니 아래 코드를 참고하자.
 * 
 * 참고로 와일드 카드는 상한(extends)과 하한(super)둘다 존재한다.
 * 반면 제네릭 메소드는 상한(extends)만 존재한다.
 * 
 * 
 * 중요한건 와일드 카드의 상한과 하한이 쓰이는 기발한 상황에 대한, 깊은 이해가 필요하다는 것이다.
 * 이는 이번 챕터에서 가장 중요한 부분이며,
 * 앞으로 프레임 워크를 배울때, 이 부분에 대한 깊은 이해가 없으면, 메소드의 의미를 파악하기 어려워진다.
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


class UnboxerUpperBound{
    public static <T> T unbox(Box<T> box){
        return box.get();
    }

    public static void peekBox(Box<? extends Number> box){
        out.println(box);
    }
}


class UnboxerLowerBound{
    public static <T> T unbox(Box<T> box){
        return box.get();
    }

    public static void peekBox(Box<? super Integer> box){
        out.println(box);
    }
}

class test{
    public static void main(String[] args){
        //와일드 카드의 상한/하한 제한 실습 코드
        Box<Integer> iBox1 = new Box<>();
        iBox1.set(100);
        Box<Double> dBox1 = new Box<>();
        dBox1.set(Math.PI);

        Box<Number> nBox1 = new Box<>();
        Box<Object> oBox1 = new Box<>();

        UnboxerUpperBound.peekBox(iBox1);
        UnboxerUpperBound.peekBox(dBox1);
        UnboxerUpperBound.peekBox(nBox1);
        UnboxerUpperBound.peekBox(oBox1); //compile error.

        UnboxerLowerBound.peekBox(iBox1);
        UnboxerLowerBound.peekBox(dBox1); //compile error.
        UnboxerLowerBound.peekBox(nBox1);
        UnboxerLowerBound.peekBox(oBox1); 
    }
}