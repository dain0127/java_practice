/*
 * 17. Interface 와 Abstact Class
 * 17-1. Interface의 기본과 의미
 * 17-2. Interface의 문법.
 *  [17-2-1. Interface안에서의 변수와 메소드]
 *  [17-2-2. Interface간의 상속]
 *  [17-2-3. Interface의 세가지 메소드]
 *  [17-2-4. Interface의 instanceof]
 *  [17-2-5. Maker Interface]
 * 17-3. 추상 클래스
 * 
 * <17-1. Interface의 기본과 의미>
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
 * 공통 규약으로서 interface를 제공하면, 일관된 형식의 제품을 만들도록 '규약'에 기반한 지시할 수 있다.
 * 
 * 제품이 완성되면 당신은 내부적으로 어떻게 구현되었는지 신경쓸 필요 없이,
 * interface를 통해 원하는 기능을 사용하면 된다. (OOP : 추상화)
 * 
 * <17-2. Interface의 문법>
 * [17-2-1. Interface안에서의 변수와 메소드]
 * interface에서 정의된 추상 메소드는 'public'이 있다고 가정한다.
 * interface에서 정의된 변수는 'public', 'static', 'final'이 있다고 가정한다.
 * 
 * 당연하다. interface는 일련의 클래스들의 공통된 규약이다.
 * 사용자와 상호작용되는걸 미리 정해두는거니, 공개되어있고(public), 공유되어있고(static), 정해져있는게(final) 당연하다.
 * 
 * + interface를 상속받은 class는 모든 추상 메소드를 완성할 의무가 있다.
 * 
 * [17-2-2. Interface간의 상속]
 * interface간에도 상속(extends로)이 가능하다. 코드의 '확장성'을 위해.
 * 상속이 필요한 이유는, 이전에 사용하던 interface를 상속받은 class의 코드를 건들지 않기 위해서이다.
 * 
 * [17-2-3. Interface의 세가지 메소드]
 * interface에는 메소드가 세가지 종류가 있다.
 * 1) 추상 메소드 2) 디폴트 메소드 3) static 메소드
 * 
 * 1) 추상 메소드 : 상속받은 class가 '반드시' 정의해야할, 메소드의 시그니처
 * 2) 디폴트 메소드 : 상속 받은 class가 '선택적으로' 정의할 수 있는, 메소드의 시그니처
 * (defualt (함수 시그니처)로 선언 가능하다.) (참고로 다른 defualt랑은 성격이 아예 다르다.)
 * 디폴트 메소드는 수많은 interface에 새로운 메소드를 추가해야할 때,
 * 상속으로 interface의 종류를 늘리는게 부담스러우면 사용한다.
 * 이 또한 코드의 '확장성' + '간결성'을 위해 존재한다.
 * 
 * 3) static 메소드 : 
 * static 메소드도 interface라고 다를게 없다.
 * 해당 interface 전용 전역 함수이다. 끝.
 * 
 * [17-2-4. Interface의 instanceof]
 * interface가 문법적으로, 개념적으로, 상위 class와 매우 유사함을 상기하다.
 * obj instanceof InterfaceName은 당연히, 해당 interface를 상속받은 인스턴스면 true를 반환한다. (아니면 false.)
 * 
 * [17-2-5. Maker Interface]
 * '이후 소스코드 참조.'
 * 
 * 17-3. 추상 클래스
 * '이후 소스코드 참조.'
 */



import static java.lang.System.out;

//카메라를 만들거면 적어도 이런 형태의 인터페이스의 기능은 구현해주세요!
interface Camera {
    int PICTURE_WIDTH = 120; //public, static, final이 있다고 간주
    int PICTURE_HEIGHT = 80; //public, static, final이 있다고 간주
    void shot(); //public이 있다고 간주.
    static void showSize(){
        out.println("(Camera) Picture of size : "+PICTURE_WIDTH +"*"+ PICTURE_HEIGHT);
    }
}

//17-2-2 interface간의 상속
interface ColorCamera extends Camera {
    void colorShot();
}

//음악 플레이어를 만들거면 적어도 이런 형태의 인터페이스의 기능은 구현해주세요!
interface MusicPlayer{
    int MAX_VOLUMN = 100;
    int MIN_VOLUMN = 0;
    void songMusic();
    default void rewind(){} //17-2-3 defulat method.
}

//어떤 휴대폰을 만들거면 이러한 특성을 가진 객체여야하니, 이걸 상속 받아 써주세요!
class Phone{
    private String number;

    Phone(String n){this.number = n;}
    public void call(){ 
        out.println("calling from "+this.number);
    }
}

//그래. SmartPhone으로 LG회사에서 다 구현해주마. 휴대폰든 기본이로, 카메라, 뮤직플레이어도 함께 말이야.
//LG는 카메라가 컬러인게 장점이야.
class LGSmartPhone extends Phone implements ColorCamera, MusicPlayer{
    private String model;

    LGSmartPhone(String n, String m){
        super(n);
        this.model = m;
    }

    @Override
    public void call(){
        out.println("LG is good!");
        super.call();
        out.println("it's " + this.model);
    }

    @Override
    public void shot(){
        out.println("shot!");
        out.println(PICTURE_WIDTH +"*"+ PICTURE_HEIGHT);
    }

    @Override
    public void colorShot(){
        out.println("colorShot!");
        out.println(PICTURE_WIDTH +"*"+ PICTURE_HEIGHT);
    }

    @Override
    public void songMusic(){
        out.println("playing music...");
        out.println("VOLUMN is in " + MAX_VOLUMN +"~"+MIN_VOLUMN);
    }
}

//Samsung회사인 나도 내방식대로 다 구현해줄게. 마찬가지로 휴대폰을 기본으로, 카메라, 뮤직플레이어도 구현할거야.
//Samsung은 음악이 되감기 되는게 장점이야.
class SamSungSmartPhone extends Phone implements Camera, MusicPlayer{
    private String model;

    SamSungSmartPhone(String n, String m){
        super(n);
        this.model = m;
    }

    @Override
    public void call(){
        out.println("Samsung is good!");
        super.call();
        out.println("it's "+this.model);   
    }

    @Override
    public void shot(){
        out.println("shot!");
        out.println(PICTURE_WIDTH +"*"+ PICTURE_HEIGHT);
    }

    @Override
    public void songMusic(){
        out.println("playing music...");
        out.println("VOLUMN is in " + MAX_VOLUMN +"~"+MIN_VOLUMN);
    }
    
    //17-2-3. 디폴트 메소드 오버로딩
    @Override
    public void rewind(){
        out.println("rewind music!(defualt method)" + "from SamSung");
    }
}

public class test {
    public static void main(String args[]){
        LGSmartPhone lgSp = new LGSmartPhone("010-1234-5678", "Q8");
        lgSp.call();
        lgSp.colorShot(); //lg만 컬러샷
        lgSp.songMusic();
        out.println("===========================");

        SamSungSmartPhone samSp = new SamSungSmartPhone("010-1234-5678", "Q8");
        samSp.call();
        samSp.shot();
        samSp.songMusic();
        samSp.rewind(); //삼성만 리와인드
        out.println("===========================");
        
        //interface형의 참조변수. 사실상 문법적으로는 클래스와 같다.
        //Camera ca = new Camera(); //compile error.
        Camera ca = new LGSmartPhone("010-9876-5432", "Galaxy10");
        ca.shot();
        out.println("===========================");

        //interface형 이름으로 접근하는 static변수
        out.println("PICTURE_WIDTH : " + Camera.PICTURE_WIDTH);
        out.println("PICTURE_HEIGHT : " + Camera.PICTURE_HEIGHT);
        out.println("MAX_VOLUMN : " + MusicPlayer.MAX_VOLUMN);
        out.println("MIN_VOLUMN : " + MusicPlayer.MIN_VOLUMN);
        out.println("===========================");

        //17-2-3-3) interface의 static 메소드
        Camera.showSize();
        out.println("===========================");

        //17-2-3 interface를 대상으로한 intanceof
        if(samSp instanceof Camera)
            out.println("samsung phone is camera!");
        if(lgSp instanceof MusicPlayer)
            out.println("lg phone is MusicPlayer!");
        out.println("===========================");

    }
}
