/*
 *               자기자신 클래스 내부    같은 패지키     자식 클래스     어디든지
 * private   :          O                 X             X           X
 * defualt   :          O                 O             X           X
 * protected :          O                 O             O           X
 * public    :          O                 O             O           O
 */

package B;

class ExtendedClass extends A.Test_A{
    public void justMethod(){
        pri=0; //X 다른 클래스
        def=0; //X 다른 패키지
        pro=0; //O 상속 클래스
        pub=0; //O
    }
}

class test_A{
    public static void main(String args[]){
        ExtendedClass obj = new ExtendedClass();
        obj.pri=0; //X 다른 클래스
        obj.def=0; //X 다른 패키지
        obj.pro=0; //X 별개 클래스
        obj.pub=0; //O 외부 패키지
    }
}

