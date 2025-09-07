/* Chapter 25. 열거형, 가변 인자, 어노테이션
 * 
 * 25-1 열거형
 * 25-2 가변 인자
 * 25-3 어노테이션
 * 
 * 
 * 25-3 어노테이션(Annotation)
 * 어노테이션이란, 컴파일러에게 메시지를 보내는 것이다.
 * 즉, 코드의 안정성 및 개발의 편의성을 위한 기능을 컴파일러가 수행할 수 있도록 한다.
 * 
 * 1) @Override
 * 이 어노테이션이 메소드 위에 붙으면,
 * 해당 메소드는 상위 클래스(혹은 인터페이스)가 제공한 메소드를 오버라이딩 하는 것임을 명시하는 것이다. 
 * 때문에 컴파일러는 해당 메소드가, 정확히 상위의 그것과 같은 메소드를 재정의하는지 점검을 한다.
 * 
 * @Override 어노테이션이 존재하는 이유는, 오버로딩을 하려는 개발자의 의도와 다르게,
 * 실수로 오버로딩을 하였을경우, 컴파일러가 에러를 일으키도록 하기 위함이다.
 * 
 * 
 * 
 * 2) @Deprecated
 * 이 어노테이션이 메소드 위에 붙으면,
 * 해당 메소드가, 본인이 존재하는 프로젝트에서 옛것이되어 다른 걸로 대체되어야한다는 것을 컴파일러에게 알린다.
 * 컴파일러는 해당 메소드를 사용했을경우, 대체되었다는 경고를 알린다.
 * 
 * @Deprecated 어노테이션이 존재하는 이유는, 옛 버전의 메소드를 여기저기서 가져다 쓴 소소 코드에,
 * 일괄적으로 대체되었다는 메시지를 알리는 역할을 컴파일러에게 위임하기 위함이다.
 * 
 * 
 * 3) @SuppressWarnings
 * 이 어노테이션이 메소드 위에 붙으면,
 * 해당 메소드가 일으키는 경고를 무시하도록 컴파일러에게 지시한다.
 * 마치 C에서 #define CRT_NO_SECURE_WARNINGS 랑 일맥상통한다.
 * 
 * 
 */



import static java.lang.System.out;

class test{    
    @Deprecated
    public static void sayHello(){
        out.println("hello");
    }

    @Deprecated

    public static void sayBye(){
        out.println("bye");
    }

    @SuppressWarnings({"deprecation", "fallthrough"})
    public static void main(String[] args) {
        //sayHello()를 사용하려고 해보아라. 취소선이 그어져있다.
        sayHello();
        sayBye();

        int n = 3;
        switch (n) {
            case 1:
                out.println(1);
            case 2:
                out.println(2);
            case 3:
                out.println(3);
            default:
        }

    }

    /*
    @Override
    public String toString(int n) {
        return new String("hello");
    }
    */
    
}