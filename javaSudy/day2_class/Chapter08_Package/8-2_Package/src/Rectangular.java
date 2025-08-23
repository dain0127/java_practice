package diagram.polygon;

public class Rectangular {
    int length, height;

    public Rectangular(int l, int h){
        length = l;
        height = h;
    }

    public void showAreaInfo(){
        System.out.println("Area : "+ (length*height));
    }
}