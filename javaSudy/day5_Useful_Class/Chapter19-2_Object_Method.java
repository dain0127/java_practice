/* 
 * Chapter19-2_Object_Method
 * 일단 Object라는 class자체가 모든 class의 상위 class이기 때문에,
 * 
 * 기본적으로 class들이 가지고 있으면 유용한 method들을 미리 정의해둔 것이다.
 * 
 * equls()는 본래 두 오브젝트의 내용물이 같은지 확인하는 용도로 쓰라고 Object에서 남겨둔 것이다.
 * Object class에서는 단순히 두 참조 변수의 참조값이 같은지만 확인한다.
 * 
 * clone()은 해당 인스턴스가 스스로의 내용을 복사하여, 힙에 할당한 참조값을 반환한다.
 * 이떄 clone()에서 깊은 복사가 필요한 맴버 변수는 잘 정의해줘야한다.
 * (String의 경우는 immutable 하기 때문에, 얕은 복사로 해도 된다. 어차피 내용은 수정 불가하니깐.
 * (심지어 실제로도 같은 걸 가리키게 한다.)
 * Object에서는 얕은 복사만 해준다.
 * 
 * clone은 Cloneable 인터페이스(마커 인터페이스임)를 구현한 class만이,
 * throw CloneNotSupportedException를 안하도록 Object의 clone()에서 정의되어있다.
 * 
 */

import static java.lang.System.out;

class Point implements Cloneable{
    private int x, y;

    Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    public void showInfo(){
        out.println("[" + x +","+ y +"]");
    }

    @Override
    public boolean equals(Object obj) {
        if(this.x == ((Point)obj).x && this.y == ((Point)obj).y)
            return true;
        else
            return false;

        //return super.equals(obj);
        //이건 그냥 참조값 비교이다. 궁금하면 peek definition을 보라.
    }
    
    @Override
    public Point clone() throws CloneNotSupportedException {
        return (Point)super.clone(); //sallow copy
    }
}

class Rectangle implements Cloneable{
    private Point upLeftPos, downRightPos;

    Rectangle(int x1, int y1, int x2, int y2){
        this.upLeftPos = new Point(x1, y1);
        this.downRightPos = new Point(x2, y2);
    }

    public void showInfo(){

        out.print("upLeftPos : "); upLeftPos.showInfo();
        out.print("downRightPos : "); downRightPos.showInfo();
    }


    @Override
    public boolean equals(Object obj) {
        if(this.upLeftPos.equals(((Rectangle)obj).upLeftPos)
        && this.downRightPos.equals(((Rectangle)obj).downRightPos)){
            return true;
        } else{
            return false;
        }
    }

    
    @Override
    protected Rectangle clone() throws CloneNotSupportedException {
        Rectangle copy = (Rectangle)super.clone(); //sallow copy
        copy.upLeftPos = copy.upLeftPos.clone();    //deep copy
        copy.downRightPos = copy.downRightPos.clone();  //deep copy

        return copy;
    }
}


class test{
    public static void main(String[] args) {
        //equals() test
        Point pos1 = new Point(1,2);
        Point samePos1 = new Point(1,2);
        Point diffPos1 = new Point(4, 8);

        if(pos1.equals(samePos1))
            out.println("same pos.");
        else
            out.println("diff pos.");


        if(pos1.equals(diffPos1))
            out.println("same pos.");
        else  
            out.println("diff pos.");

        
        Rectangle rec1 = new Rectangle(1, 1, 3, 3);
        Rectangle sameRec1 = new Rectangle(1, 1, 3, 3);
        Rectangle diffRec1 = new Rectangle(2, 2, 4, 4);

        if(rec1.equals(sameRec1))
            out.println("same rec.");
        else
            out.println("diff rec.");


        if(rec1.equals(diffRec1))
            out.println("same rec.");
        else  
            out.println("diff rec.");


        System.out.println();


        //clone() test
        try{
            Point copyPos1 = pos1.clone();
            Rectangle copyRec1 = rec1.clone();

            copyPos1.showInfo();
            copyRec1.showInfo();

        } catch (CloneNotSupportedException e){
            out.println("exception handling");
            e.getMessage();
        }

    }
}
