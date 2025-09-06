/* Chapter 25. 열거형, 가변 인자, 어노테이션
 * 
 * 25-1 열거형
 * 25-2 가변 인자
 * 25-3 어노테이션
 * 
 * 25-2 가변 인자
 * 가변인자. 어렵지 않다. 그냥 같은 자로형을 여러개 받을 수 있도록 하는 것이다.
 * 사실 코드의 간결성을 위해 컴파일러에서 추가한 기능일 뿐이다.
 * 
 * 가변인자를 선언하면, 해당 자료형의 배열참조형으로 매개변수형이 결정된다.
 * 
 * 차이점이 있다면, 가변인자는 메소드의 인자로 받을때, 해당 값을 여러개 받을 수 있다는 것이다.
 * 가변인자가 아닌, 배열형 참조변수같은 경우, 배열을 만들어 놓은 상태에서 배열참조값을 넘겨줘야한다.
 * 
 * 허나 결과적으로, 이 둘은 기능적으로 같다.
 * 
 * 다만 가변인자를 사용하는 이유는, 가독성과 간결성이다.
 * 
 */


import static java.lang.System.out;

class test{    
    //가변인자를 활용한 메소드 정의
    static void showVarArgs(String ... vargs){
        for(String e : vargs)
            out.print(e + "\t");
        out.println("\nString 개수 : " + vargs.length);
        out.println();
    }

    //가변인자를 활용하지 않은 메소드 정의
    static void showStringArr(String[] arr){
        for(String e : arr)
            out.print(e + "\t");
        out.println("\nString 개수 : " + arr.length);
        out.println();      
    }

    public static void main(String[] args) {
        //가변인자 메소드 활용
        showVarArgs("A","B","C");
        showVarArgs("hello","bye");
        showVarArgs("welcome");

        System.out.println();

        //가변인자를 안쓰는 메소드 활용
        showVarArgs(new String[]{"A","B","C"});
        showVarArgs(new String[]{"hello","bye"});
        showVarArgs(new String[]{"welcome"});
    }
}