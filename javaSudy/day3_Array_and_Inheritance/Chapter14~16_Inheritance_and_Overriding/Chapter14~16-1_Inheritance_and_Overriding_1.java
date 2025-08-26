/*
 * Chapter14 Inheritance and Overriding.
 * 14-1 상속(Inheritance)의 기본 문법
 *  14-1-1. 클래스 변수와 클래스 메소드의 상속(static 변수와 메소드)
 *  14-1-2. 상속과 참조변수의 참조 가능성
 * 14-2. 메소드 오버라이딩(Overriding)
 * 14-3. instanceof 연산자
 * 14-4. Object class
 * 14-5. Class와 method의 final 선언.
 * 14-6. @Override.
 * 
 * 
 * 
 * 14-1 상속(Inheritance)의 기본 문법
 * 저자는 상속을 일련의 클래스들에 대한 '공통된 규약'을 정의하기 위해서 사용한다고 한다.
 * C++에서는 :public BaseClass 로 상속한다면,
 * java에서는 extends BaseClass로 상속한다.
 * 
 * 자식 클래스의 생성자에서, 부모 맴버변수를 생성자로 초기화할때,
 * C++에서는 :BaseClass(arg)로 초기화했다면,
 * java에선 super(arg)로 생성자 코드 맨 위에 (반드시) 삽입하여 초기화한다.
 * 
 * 그 외에 C++과 상속의 개념은 같다.
 * 
 * 14-1-1. 클래스 변수와 클래스 메소드의 상속(static 변수와 메소드)
 * static은 사실상 클래스로 접근하는 '전역 변수'와 '전역 함수'이니 당연히 상속과 상관없다.
 * 끝.
 * 
 * 14-1-2. 상속과 참조변수의 참조 가능성
 * C++과 같다.
 * 상위 클래스의 참조 변수로, 하위 클래스를 가리킬 수 있다.
 * 그러나, 상위 클래스의 참조 변수로 무엇을 가리키던, 상위 클래스 type의 메소드만 접근이 가능하다.
 * (C++과 마찬가지로 컴파일러가 참조변수의 type으로만 판단하기 때문이다.)
 * 
 * 주목해야할 건 배열이다. 
 * java는 배열도 별도의 클래스이다. 그런데 배열에서도 상속 클래스간 참조에 대한 문법이 동일하다.
 * BaseClass[] baseArary = new DrivedClass[7]; 따위로 적용 가능하다.
 * 
 * 14-2. 메소드 오버라이딩(Overriding)
 * C++과 다르게, java는 virtual 키워드 없이, "그냥" 오버라이딩을 해도, ★★★★★★★"다형성"★★★★★★★이 보장된다!!!
 * (C++에서는 virtual을 붙여줘야 비로소, 다형성이 보장된다. 안그러면 걍 상위 type에 의존해서 상위 method를 곧이 곧대로 호출한다.)
 * 그리고 부모 class의 메소드 호출할때 super.method()로 호출한다.
 * 
 * 참고로 맴버변수는 오버라이딩이 불가능하다.
 * 
 * 14-3. instanceof 연산자
 * obj instanceof ClassName 식으로 사용한다.
 * 해당 객체가 Class 자체 혹은 그것에 상속받은 Class이면 true를 반환, 아니면 false를 반환한다.
 * 
 * instanceof 연산자의 사용 목적은, '명시적 형 변환의 가능성'을 판단해준다.
 */


import static java.lang.System.out;

class BaseClass{
    int baseData;

    BaseClass(int b){
        this.baseData = b;
    }

    public void Basefun(){
        out.println("Basefun");
    }

    public void showYourClassName(){
        out.println("I'm BaseClass");
    }

    public void showData(){
        out.println("baseData : " + baseData);
    }
}

class DrivedClass extends BaseClass{
    int drivedData;

    DrivedClass(int b, int d){
        super(b);
        this.drivedData = d;
    }

    public void Drivedfun(){
        out.println("Drivedfun");
    }

    public void showYourClassName(){
        out.println("I'm DrivedClass");
    }

    public void showData(){
        super.showData(); //부모의 메소드를 가져올때 쓰는 방법.
        out.println("drivedData : " + drivedData);
    }
}

class test{
    public static void main(String args[]){
        //14-1 상속의 기본 문법
        BaseClass b1 = new BaseClass(1);
        b1.showData();
        DrivedClass d1 = new DrivedClass(1,2);
        d1.showData();
        out.println();


        //14-1-2 상속과 참조 변수의 관계
        b1 = d1;
        //int temp = b1.drivedData; //compile error
        //상위 클래스 참조변수가 하위 클래스를 가리키더라도, 컴파일러는 type 중심으로 판단한다.

        //배열 또한. BaseClass[]가 DrivedClass를 요소로하는 배열을 가리킬 수 있다.
        BaseClass[] bArr1= new DrivedClass[10];
        for(BaseClass e : bArr1)
            e=new DrivedClass(10, 12);


        //14-2 메소드 오버라이딩
        DrivedClass d2 = new DrivedClass(10, 10);
        BaseClass b2 = new DrivedClass(20, 30);
        d2.showYourClassName(); //얘는 자기꺼 나오고.
        b2.showYourClassName(); //얘는 참조변수가 Base임에도, 오버라이딩된 method로 Drived method가 나온다.
        out.println();

        //14-3 instanceof 연산자
        BaseClass b3 = new BaseClass(1);
        BaseClass d3 = new DrivedClass(10, 20);
        Classfun(b3);
        Classfun(d3);

        //((DrivedClass)b3).Drivedfun(); //run time exception handling
    }

    static void Classfun(BaseClass obj){
        if(obj instanceof DrivedClass){
            ((DrivedClass)obj).Drivedfun();
        }else{
            obj.Basefun();
        }
    }
}