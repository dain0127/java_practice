/*
 *               자기자신 클래스 내부    같은 패지키     자식 클래스     어디든지
 * private   :          O                 X             X           X
 * defualt   :          O                 O             X           X
 * protected :          O                 O             O           X
 * public    :          O                 O             O           O
 */

package A;

public class Test_A{
    private int pri;
    int def; //defualt
    protected int pro;
    public int pub;

    public void justMethod(){
        pri=0; //O 같은 클래스 내부
        def=0; //O
        pro=0; //O
        pub=0; //O
    }
}

class ExtendedClass extends Test_A{
    public void justMethod(){
        pri=0; //X 다른 클래스
        def=0; //O 같은 패키지
        pro=0; //O
        pub=0; //O
    }
}

class test{
    public static void main(String args[]){
        ExtendedClass obj = new ExtendedClass();
        obj.pri=0; //X 다른 클래스
        obj.def=0; //O 같은 패키지
        obj.pro=0; //O
        obj.pub=0; //O
    }
}

