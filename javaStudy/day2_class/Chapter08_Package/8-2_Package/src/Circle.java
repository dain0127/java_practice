package diagram.circle;

public class Circle {
    int rad;
    final double PI;

    public Circle(int r, double pi){
        rad = r;
        PI = pi;
    }

    public void showAreaInfo(){
        System.out.println("Area : "+ (rad*rad*PI));
    }
}


