/*
 * 17. Interface 와 Abstact Class
 * 17-1 Interface의 기본과 의미
 * 
 * 
 * 17-1 Interface의 기본과 의미
 * interface는 간단히 이야기해서, 메소드의 필요한 형식(이를 추상 메소드라 부른다)만 따로 모아둔 것을 의미한다.
 * interface는 본질적으로 메소드의 선언만 있는 '메소드 구현이 빠진 상위 클래스'와 다를게 없다.
 * (C++로 따지면 순수 가상함수만 있는 클래스이다.)
 * 
 * 왜냐하면, implement라는 키워드로 class에 오버라이딩해야하는 메소드를 물려줄 수 있고,
 * interface자체를 참조형 변수로 선언하여, 하위 class의 객체 참조값을 저장할 수 있다.
 * 그리고 함수를 구체화하는 과정에서 @Override라는 어노테이션도 붙일 수 있다.
 * (extends가 아닌 implement가 키워드인 이유는, interface는 상속이 아닌 '구현'이 본질이기 때문이다.)
 * 
 * 다만, interface는 인스턴스로 만들 수 없다. (당연하다. 메소드 정의가 없지 않는가.) (C++에서도 추상 클래스는 실체화 못한다.)
 * 
 * 그리고 class는 '여러 개'의 interface를 동시에 구현할 수 있다.
 * 
 * interface를 만드는 목적은, interface의 의미 자체에 충실하다.
 * 만약에 당신이 어떤 공통된 목적을 가진 제품을 여러 회사에서 만들어야할 때,
 * 공통 규약으로서 interface를 제공하면, 일관된 형식의 제품을 만들도록 '규약'을 제공할 수 있다.
 * 
 * 그러면 당신은 내부적으로 어떻게 구현되었는지 신경쓸 필요 없이,
 * interface를 통해 원하는 기능을 사용하면 된다. (OOP :추상화)
 * 
 */

import static java.lang.System.out;


//카메라를 만들거면 적어도 이런 형태의 인터페이스의 기능은 구현해주세요!
interface Camera {
    public void shot();
}

//음악 플레이어를 만들거면 적어도 이런 형태의 인터페이스의 기능은 구현해주세요!
interface MusicPlayer{
    public void songMusic();
}

//어떤 휴대폰을 만들거면 이러한 특성을 가진 객체여야하니, 이걸 상속 받아 써주세요!
class Phone{
    private String number;

    Phone(String n){this.number = n;}
    public void call(){ 
        out.println("calling from "+this.number);
    }
}

//그래. SmartPhone으로 LG에서 다 구현해주마. 휴대폰든 기본이로, 카메라, 뮤직플레이어도 함께 말이야.
class LGSmartPhone extends Phone implements Camera, MusicPlayer{
    private String model;

    LGSmartPhone(String n, String m){
        super(n);
        this.model = m;
    }

    @Override
    public void call(){
        super.call();
        out.println("it's "+this.model);
        out.println("LG is good!");
    }

    @Override
    public void shot(){
        out.println("shot!");
    }

    @Override
    public void songMusic(){
        out.println("playing music...");
    }
}

//Samsung인 나도 내방식대로 다 구현해줄게. 마찬가지로 휴대폰을 기본으로, 카메라, 뮤직플레이어도 구현할거야.
class SamSungSmartPhone extends Phone implements Camera, MusicPlayer{
    private String model;

    SamSungSmartPhone(String n, String m){
        super(n);
        this.model = m;
    }

    @Override
    public void call(){
        super.call();
        out.println("it's "+this.model);
        out.println("Samsung is good!");
    }

    @Override
    public void shot(){
        out.println("shot!");
    }

    @Override
    public void songMusic(){
        out.println("playing music...");
    }
}

public class test {
    public static void main(String args[]){
        LGSmartPhone lgSp = new LGSmartPhone("010-1234-5678", "Q8");
        lgSp.call();
        lgSp.shot();
        lgSp.songMusic();
        out.println();

        SamSungSmartPhone samSp = new SamSungSmartPhone("010-1234-5678", "Q8");
        samSp.call();
        samSp.shot();
        samSp.songMusic();
        out.println();
    
        //Camera ca = new Camera(); //compile error.
        Camera ca = new LGSmartPhone("010-9876-5432", "Galaxy10");
        ca.shot();
    }
    
}
