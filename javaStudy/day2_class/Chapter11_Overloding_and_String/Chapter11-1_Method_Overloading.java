/*
 * 11. Method Overloading과 String Class
 * 
 * 11-1. Method Overloading
 * 11-2. String class
 * 11-3. String class method
 * 
 * 11-1. Method Overloding
 * C++에서는 오버로딩이라는 의미는, 같은 함수명을 사용하지만,
 * paramater의 양식에 따라서, 전혀 다른 함수가 호출되도록 허용하는 문법을 이야기한다.
 * 
 * 오버로딩을 허용하는 언어에서는 함수를 구별할때, 두가지를 따진다.
 * 1. 함수의 이름
 * 2. 함수의 매개변수 양식
 * 
 * 그리고 이 챕터에서 this라는걸 설명하는데,
 * 1.기본적으로 자기자신을 참조하는 참조자로서의 this인건 C++과 똑같다.
 * 2.여기서 더해서 java는 this()가 해당 class의 생성자를 의미한다.(C++에서 ClassName()과 일맥한다.)
 * 
 * 그리고 당연하지만, 생성자도 오버로딩 된다.
 */


class Point{
    private int xpos, ypos;

    //생성자 오버로딩이 가능하다.
    Point(int xpos, int ypos){
        //1. this는 자기 자신을 참조하는 참조값이다.
        this.xpos = xpos;
        this.ypos = ypos;
    } 

    Point(int xpos){
        //2. this() 자기 자신의 생성자이다.
        this(xpos, 0);
    }

    Point(){
        this(0,0);
    }

    //메소드 오버로딩이 가능하다.
    public void addPoint(int x, int y){
        this.xpos+=x;
        this.ypos+=y;
    }
    public void addPoint(int n){
        this.xpos+=n;
        this.ypos+=n;
    }

    public void showInfo(){
        System.out.println("[" +xpos+ "," +ypos+"]");
    }
}

public class test {
    public static void main(String args[]){
        Point pos1 = new Point(10, 20);
        pos1.showInfo();
        pos1.addPoint(10);
        pos1.showInfo();
        pos1.addPoint(100, 0);
        pos1.showInfo();
        
        System.out.print('\n');

        Point pos2 = new Point(1);
        pos2.showInfo();
    }    
}
