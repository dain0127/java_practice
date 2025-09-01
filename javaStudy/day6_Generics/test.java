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
 * 
 * 21-1-2 제네릭의 기본 문법
 * 21-1-2-2 ★★★제네릭의 타입 인자 제한 (extneds 키워드)★★★
 * -제네릭 클래스의 타입 인자 제한(extends) 
 *      타입 인자를 extends로 제한할 수 있다. 상위 클래스나, 인터페이스로 가능하다.
 *      <T ☆extends☆ ClassName(InterfaceName)>으로 표현되며, 해당 클래스(인터페이스)를 상속받은 하위 클래스만이
 *      타입 인자값으로 들어갈 수 있다.
 * 
 *      ==> 이 문법이 존재하는 이유는.
 *          타입을 제한함으로서, 해당 클래스가 가지고 있는 mehtod를 '제너릭 클래스 내부'에서
 *          사용하여 정의하기 위함이다.
 *          (자세한건 NumberBox와 Box의 차이점을 비교해보라.)
 *          (☆☆☆ 컴파일러는 프로그래머의 마음을 못읽는다.
 *              때문에 컴파일러는 현재 코드에서 언제 어떻게 실행되더라도 해당 코드가 가능한지를 따져서 에러를 판단한다. ☆☆☆)
 * 
 * 
 * -제네릭 클래스의 타입 인자 제한(extends) 
 *      상술한 "제네릭 클래스의 타입 인자 제한(extends)"와 방식이 같다.
 *      public static ☆<T extends ClassName>☆ void funName (int n){...} 따위로 표현
 * 
 * -다중 타입 인자 제한
 * <T extends Food & Eatable> 따위로 구현한다.
 * 
 */

 
import static java.lang.System.out;

class Box<T> {
    private T ob;
    public void set (T o){
        this.ob = o;
    }
    public T get(){
        return this.ob;
    }

    //T가 Number의 하위클래스임을 보장받지 못했기 때문에,
    //컴파일러는 intValue()메소드 호출에 대해서, 컴파일 에러를 낸다.
    //error : (The method intValue() is undefined for the type T)
    /*
    public int getIntValue(){
        return ob.intValue();
    }
    */
}

class NumberBox<T extends Number>{
    private T num;

    public void set(T n){
        this.num = n;
    }

    public T get(){
        return this.num;
    }
    
    public int getIntValue(){
        return num.intValue(); //T가 적어도 Number를 상속했다는 보장이 되어있기 때문에.
        //컴파일러 입장에서 Number의 (비록 추상 메소드지만, 어차피 인스턴스화 불가능하고, 완성된 하위 클래스가 인자값으로 올 것이다.)
        //intValue()를 호출해도 된다고 판단한 것이다. 
    }
}


/*============ 다중 타입 인자값 제한과 인터페이스 제한 예시를 보여주기 위한 코드 ===============*/
interface Eatable {
    void eat();
}

abstract class Food{
    int gram;
    Food(int g){this.gram = g;}

    public abstract void dish();
}


class Apple extends Food implements Eatable{
    Apple(int g){super(g);}
    @Override
    public void dish() {
        out.println("Apple is on dish");
    }

    @Override
    public void eat() {
        out.println("eat Apple : " + gram + " gram");
    }
}

//다중 타입 인자값을 제한하는 방법은 &를 사용하는 것이다.
//그리고 보다시피 인터페이스는 추상클래스마냥 타입의 제한이 가능하다. 늘 그렇듯이 말이다.
class Chef{
    public <T extends Food & Eatable> void cook(T food){
        out.println("Chef cooks food by himself and eat.");
        food.dish();
        food.eat();
    }
}



class test{
    public static void main(String[] args){
        NumberBox<Integer> iBox = new NumberBox<>();
        iBox.set(100);
        int iNum = iBox.getIntValue();
        out.println(iNum);

        NumberBox<Double> dBox = new NumberBox<>();
        dBox.set(3.14);
        double dNum = dBox.getIntValue();
        out.println(dNum);
        out.println();

        Chef chef1 = new Chef();
        chef1.cook(new Apple(100));
    }
}