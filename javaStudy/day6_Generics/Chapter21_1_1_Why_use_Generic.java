/*
 * Chapter 21 제네릭 1
 * 본 챕터에서는, 제네릭의 문법적인 이해도 겸하여,
 * 제네릭이 나타나게 된 배경. 제네릭의 용도. 를 중심으로 서술하고자 한다. (교재에서도 그 점을 강조하고있다.)
 * 
 * 21-1-1 제네릭이 필요한 이유
 * 21-1-2 제네릭의 기본 문법
 *      21-1-2-1 제네릭의 기본 문법
 *      ★★★21-1-2-2 제네릭의 타입 인자 제한 (extneds 키워드)★★★
 * 
 * 21-1-1 제네릭이 필요한 이유
 * 
 * 우선 문법적으로는,
 * C++의 템플릿과 크게 다를 바가 없어보인다. 오히려 java가 더 쉽다. (템플릿 특수화... 그런게 없어보인다.)
 * 마찬가지로 java에서 class와 method를 각각 '일반화된 type'으로 정의할 수 있다.
 * 
 * class의 문법적 제너릭 정의는 아래 Box class 코드 참고.
 * 
 * 아래 코드의 첫번째 부분은, 제너릭이 없을때의 문제점 두개를 지적한다.
 * Box라는 클래스는, 아무 데이터든 감싸서 다른 메소드의 인자로 전달할 수 있도록 해준다.
 * 그런데 제너릭이 되어있지 않으니, set하거나 get할때, 최상위 클래스인 Object로 저장하거나 반환할 수 밖에 없다.
 * 때문에, 두가지 문제점이 생긴다.
 * 1) 개발자가 직접 알맞는 자료형으로 인스턴스를 casting해야한다. (불편하다.)
 * 2) ※※※잘못해서 다른 자료형을 인자값으로 넘겨도, 컴파일러와 JVM이 에러와 예외를 던지지 않는다!!!※※※ (불안전하다.)
 * 특히 2번은 상당히 문제가 된다.
 * 
 * 때문에 우리는 프로그램의 논리에 맞지 않는 코드의 흐름을,
 * "컴파일러에게 검사하도록 양도"하기 위해, 제너릭이라는 문법을 사용할 수 있다.
 * 
 * 즉, 제너릭은 본래 자신이 원할때 class에서 사용하는 일반적인 자료형을 결정짓는다는 장점도 있지만,
 * 더불어, 컴파일러에게 프로그램의 논리적인 흐름에 대한 점검을 하도록 책임을 부과할 수도 있다는 말이기도 하다.
 * 
 */

 
import static java.lang.System.out;

class Apple{
    @Override
    public String toString() {
        return "I'm apple";
    }
}

class Orange{
    @Override
    public String toString() {
        return "I'm Orange";
    }
}

class BoxNotGeneric{
    private Object ob;
    public void set(Object o){
        this.ob=o;
    }
    public Object get(){
        return this.ob;
    }
}

class Box<T>{
    private T ob;
    public void set (T o){
        this.ob = o;
    }
    public T get(){
        return this.ob;
    }
}

class test{
    public static void main(String[] args){
        //제너릭이 없을 때의 코드
        BoxNotGeneric box1 = new BoxNotGeneric();
        BoxNotGeneric box2 = new BoxNotGeneric();

        box1.set(new Apple());
        box2.set(new Orange());

        //get()메소드의 반환형은 Object이다.
        Apple a1 = (Apple)box1.get(); //번거롭게 casting을 해야함
        Orange o1 = (Orange)box2.get(); //번거롭게 casting을 해야함
        //개발자가 해당 인스턴스의 클래스 타입을 머리로 알고 casting 해야한다.
        //즉, '실수'할 수 있다. '안정성'이 떨어진다.

        box1.set("Apple"); //실수!! 하지만 컴파일러도, 예외도 없이 실행되어버린다!
        //이 데이터를 다른 개발자가 받거나, 다른 클래스가 받아서 처리하는 순간, 프로그램이 폭발해버린다!

        out.println(a1);
        out.println(o1);




        //제너릭이 있을 때의 코드
        Box<Apple> box3 = new Box<Apple>();
        Box<Orange> box4 = new Box<Orange>();

        box3.set(new Apple());
        //box4.set(new Apple()); //compile error. 발견!
        box4.set(new Orange());

        Apple a2 = box3.get();
        Orange o2 = box4.get();

        out.println(a2);
        out.println(o2);
    }
}