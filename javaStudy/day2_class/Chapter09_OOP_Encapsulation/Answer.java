class Point{
    private int xpos, ypos;
    public Point(int x, int y){
        xpos = x;
        ypos = y;
    }

    public void showInfo(){
        System.out.println("[" + xpos+","+ypos+"]");
    }
}

class Circle{
    private Point center;
    private int rad;

    public Circle(Point pos, int r){
        center = pos;
        rad = r;
    }

    public void showInfo(){
        center.showInfo();
        System.out.println("radious : " + rad);
    }
}

class Answer {
    public static void main(String args[]){
        Circle cir = new Circle(new Point(1, 2), 10);
        cir.showInfo();
    }    
}