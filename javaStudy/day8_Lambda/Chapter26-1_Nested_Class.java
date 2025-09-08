/* Chapter 26 네스티드 클래스
 * 네스티드 클래스 :
 * 네스티드 클래스는, 클래스 내부에 정의된 클래스를 의미한다.
 * 네스티드 클래스는 static 여부에 따라 두개로 나뉜다.
 * 그리고 non-static 클래스. 즉, 이너 클래스는 세가지로 나뉜다.
 * 
 * 1. static 네스티드 클래스
 * 아우터 클래스에서 static 키워드가 붙은 클래스이다.
 * 그냥 static이 가지고 있는 문법적 맥락에 따라 생각하면,
 * OuterClassName.NestedClassName 처럼, 클래스 이름에 맞춰 접근할 뿐인 독립적인 클래스임을 이해할 수 있다.
 * 
 * 자세한건 코드 참조.
 * 
 * 
 * 
 * 2. inner 클래스 (non-static 네스티드 클래스)
 * 이너 클래스 :
 * 이너 클래스(inner class)는 외부 클래스와 긴밀하게 연결된 기능을 구현할 때 유용하다.
 * 예를 들어, 외부 클래스의 필드나 메서드에 쉽게 접근해야 하는 작은 보조 객체를 만들 때 쓰면 코드가 간결하다.
 * 
 * ★★★ 이너 클래스는 외부 클래스의 맴버 변수를 참조할 수 있다. ★★★
 * 자세한건 코드 참고.
 * 
 * 맴버 클래스 - 필드와 메소드와 동일한 위치에서 정의 
 * 로컬 클래스 - 메소드 내에서 정의
 * 익명 클래스 - 메소드 내에서 이름 생략 채로 정의
 * 
 */



import static java.lang.System.out;

interface Printable{
    void print();
}

class Outer{
    private static int sNum = 0;
    private int num = 0;
    
    public Outer(int n){this.num = n;}
    public void showNum(){
        out.println("num show from outer : "+num);
    }
    //맴버 클래스 반환
    public Printable getMemberPrinter(){
        return new MemberPrinter();
    }

    public Printable getLocalPrinter(){
        //로컬 클래스
        class LocalPrinter implements Printable{
            @Override
            public void print() {
                out.println("num show from LocalPrinter : "+num);                    
            }
        }

        return new LocalPrinter();
    }

    public Printable getAnonymousPrinter(){
        //익명 클래스
        return new Printable() {
            //익명 클래스는 람다가 아니다.
            //람다가 익명클래스의 일부(메소드 하나있는 인터페이스로서만 정의)로 생각되어질 수 있다.
            //즉, 익명클래스는 맴버도 가질 수 있고, 추가적인 메소드도 구현이 가능하다.
            //이는 익명 클래스가 이름이 없을뿐, 본질은 필드와 메소드로 이루어졌다는 클래스인 까닭이다.

            //다만, 해당 익명 클래스는 이름이 없기에, 다른 코드에 지명되어 재언급되지 못한채
            //해당 라인이 끝나면 사라지기에, 추가적인 메소드의 정의에 대한 유용성은 회의적이다.
            String s;
            public void setS(String s) {
                this.s = s;
            }
            public void printS(){
                out.println("s is " + this.s + " from AnonymousClass.");
            }

            @Override
            public void print() {
                out.println("num show from AnonymousPrinter : "+num);
            }
        };
    }



    //static 네스티드 클래스
    static class StaticNestedClass{
        public void addSNum(int n){sNum+=n;}
        public void showSNum(){out.println("sNum : " + sNum);}
    }

    //맴버 클래스
    class MemberClass{
        private int addNum = 0; //add할때 수
        MemberClass(int a){this.addNum = a;}

        public void addNum(){num+=this.addNum;}
        public void showNum(){out.println("num show from inner : " + num);}
    }

    //맴버 클래스
    //private를 붙였다는 말은. public method를 통해 안전하게 다루어진다는 의미이다.
    private class MemberPrinter implements Printable {
        @Override
        public void print() {
            out.println("num show from MemberPrinter : "+num);    
        }
    }
}

class test{    
    public static void main(String[] args) {
        //===============static nested class====================
        Outer.StaticNestedClass s = new Outer.StaticNestedClass();
        s.addSNum(10);
        s.showSNum();
        out.println();

        //===============inner class====================
        Outer o1 = new Outer(10);
        Outer o2 = new Outer(20);

        Outer.MemberClass o1m1 = o1.new MemberClass(1);
        Outer.MemberClass o1m2 = o1.new MemberClass(2);
        Outer.MemberClass o2m1 = o2.new MemberClass(1);
        Outer.MemberClass o2m2 = o2.new MemberClass(2);

        Printable p1;
        Printable p2;



        o1m1.addNum(); o1.showNum();
        o1m2.addNum(); o1.showNum();

        p1 = o1.getMemberPrinter();
        p1.print();
        p1 = o1.getLocalPrinter();
        p1.print();
        p1 = o1.getAnonymousPrinter();
        p1.print();
        out.println();

        o2m1.addNum(); o2.showNum();
        o2m2.addNum(); o2.showNum();
        
        p2 = o2.getMemberPrinter();
        p2.print();
        p2 = o2.getLocalPrinter();
        p2.print();
        p2 = o2.getAnonymousPrinter();
        p2.print();


        out.println();        

    }

}