package diagram.polygon;

public class Triangle {
    int length, height;

    public Triangle(int l, int h){
        length = l;
        height = h;
    }

    public void showAreaInfo(){
        System.out.println("Area : "+ (double)(length*height/2));
    }
}