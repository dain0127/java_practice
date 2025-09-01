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
 * 22-3-1 와일드 카드란 무엇인가
 * 와일드 카드란. (java 언어에서) 타입의 이름을 동시에 지정할 목적으로 사용하는 특수기호를 말한다.
 * 와일드 카드를 인자값으로 사용하면, 마치 ?에 들어갈 타입을 전부 오버로딩한 것 처럼 사용할 수 있다.
 * 
 * ※※※와일드 카드를 사용한 메소드는, 제네릭 메소드가 아니다!※※※
 * 하지만 가능적으로는,(컴파일러에 의해 타입이 자동 추측된) 제네릭 메소드와 기능적으로 완전히 같다.
 * 
 * 그럼에도 불구하고 둘은 완전히 다른 개념임을 상기해야한다.
 * 
 * 왜냐하면 이후에 확장될 상한/하한 제한에서 와일드 카드를 사용한 메소드는, 제네릭 메소드와 다른 문법적 규칙을 적용받기 때문이다.
 * 
 * 또한 와일드 카드 + 제네릭 메소드를 동시에 사용하는 상황이 생긴다.
 * 이는 (안정성 + 확장성) 측면에서 뛰어난 문법적 효과를 누릴 수 있게 된다.
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

class UnboxerGeneric{
    public static <T> T unbox(Box<T> box){
        return box.get();
    }
    public static <T> void peekBox(Box<T> box){
        out.println(box);
    }
}

class UnboxerWildcard{
    public static <T> T unbox(Box<T> box){
        return box.get();
    }

    //이 메소드는 제네릭 메소드가 아니다!
    //그냥... 오버로딩이 하나의 코드로 간결하게 되었다고 보면 된다!
    public static void peekBox(Box<?> box){
        out.println(box);
    }
}

class test{
    public static void main(String[] args){
        //22-3-1 와일드 카드란 무엇인가
        Box<String> box = new Box<>();
        box.set("hello world");
        UnboxerGeneric.peekBox(box); //인자값의 타입에 따라, 제네릭 메소드의 타입 인자값이 결정됨을 상기하라. (Chapter21_1_2_1)
        UnboxerWildcard.peekBox(box); //Box<?>가 Box<Double>을 커버한다. 오버로딩된 메소드를 호출하듯, 해당 메소드를 호출한다.
    }
}