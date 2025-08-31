//animal이라는 package에 묶여있다는 소리
package bin.animal;

public class Dog{
    public void makeCat(){
        //Cat은 public class이므로 어디서든지 생성 가능        
        zoo.Cat cat = new zoo.Cat();
    }

    public void makeDuck(){
        //Duck은 default class이므로 해당 package 외부에서는 생성 불가능.
        zoo.Duck duk = new zoo.Duck(); //compile error
    }
}
