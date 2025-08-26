import static java.lang.System.out;

class Man{
    String name;
    
    Man(String n){
        this.name = n;
    }
    
    public void tellYourInfo(){
        out.println("My name is " + name);
    }
}

class BusinessMan extends Man{
    String company;

    BusinessMan(String n, String c){
        super(n); //하위 클래스의 생성자, 맨 위에 반드시 부모 생성자를 호출해서,
        //부모 필드에 대한 적절한 초기화 작업을 거치도록, 컴파일러가 문법적으로 제한을 했다.
        this.company = c;
    }

    public void tellYourInfo(){
        super.tellYourInfo();
        out.println("My company is " + company);
    }
}

class DeveloperMan extends BusinessMan{
    String itPosition;

    DeveloperMan(String n, String c, String p){
        super(n, c);
        this.itPosition = p;
    }

    public void tellYourInfo(){
        super.tellYourInfo();
        out.println("my position is "+itPosition);
    }
}

class test{
    public static void main(String args[]){
        //14-1 상속의 기본 문법
        Man man1 = new Man("changin");
        man1.tellYourInfo();
        BusinessMan bMan1 = new BusinessMan("changin", "hello");
        bMan1.tellYourInfo();
        DeveloperMan dBMan1 = new DeveloperMan("changin", "hello", "developer");
        dBMan1.tellYourInfo();
        out.println();


        //14-1-2 상속과 참조 변수의 관계
        man1 = bMan1;
        Man man2 = dBMan1;
        String temp1 = man1.name;
        String temp2 = man1.company; //compile error
        //상위 클래스 참조변수가 하위 클래스를 가리키더라도, 컴파일러는 type 중심으로 판단한다.

        //배열 또한. Man[]이 BusinessMan을 요소로하는 배열을 가리킬 수 있다.
        Man[] mans1 = new BusinessMan[5];
        for(Man b : mans1)
            b = new BusinessMan("dongin", "bye");


        //14-2 메소드 오버라이딩
    }
}