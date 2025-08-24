//zoo라는 package에 묶여있다는 소리.
package bin.zoo;

class Duck{
    //empty
}

public class Cat{
    public void makeCat(){
        //Duck과 같은 패키지로 묶여있기 때문에 Duck 인스턴스를 생성할 수 있다.
        Duck duc = new Duck();
    }
}
