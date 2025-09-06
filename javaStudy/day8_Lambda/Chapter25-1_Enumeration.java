/* Chapter 25. 열거형, 가변 인자, 어노테이션
 * 
 * 25-1 열거형
 * 25-2 가변 인자
 * 25-3 어노테이션
 * 
 * 25-1 열거형
 * C/C++에서 배운 열거형을 생각해보라.
 * 열거형이란 관련있는 상수들을 모아둔 것을 일컫는다. 
 * 
 * java에서도 마찬가지이다.
 * 열거형이 있기 전, java에서 관련있는 상수를 묶어두는 방법은, interface에 상수(public, static final)을 선언하면서 정의할 수 있다.
 * 하지만 어떤 특정 열거형만 받을 수 있도록 type으로서 제한하는 방법이 없었다.
 * 
 * 열거형은 이러한 문제점을 해결하기 위해 존재한다.
 * 
 * 밑에서 열거형에 대한 내용을 적어두었다. 참고하여라.
 * 
 * 주목해야하는 것은, enum은 사실 class라는 것이다.
 * 때문에, 모든 enum은 Enum<E>라는 클래스를 상속한다.
 * 
 * 리터럴 문자열을 String으로 컴파일러가 자동 처리하는 것과 결을 같이한다.
 * 
 */


import static java.lang.System.out;


enum Animal{
    DOG, CAT, COW
}

//class 안에 enum을 원하는로 선언할 수 있다.
//이후에 배울 nested class와 같다고 볼 수 있다.
//왜냐하면, enum이 사실상 java에서 특정한 조건을 가진 class처럼 작동하는 까닭이다.
class Music{
    static enum Scale{
        DO, RE, MI, FA, SO, RA, SI
    }

    public static void songScale(Scale sc){
        switch (sc) {
            case DO:
                out.println("~도.");
                break;
            case RE:
                out.println("~레.");
                break;
            case MI:
                out.println("~미.");
                break;
            default:
                out.println("~파솔라시.");
                break;
        }
    } 
}

enum Person{
    MAN(22), WOMAN(30);

    int age;
    private Person(int a){
        this.age = a;
    }

    @Override
    public String toString() {
        return "I'm "+this.age+" old years.";
    }
}

//열거형이 사실상 클래스와 기능적, 문법적으로 같다라는걸 보여주기 위한 클래스 정의 코드.
class PersonClass{
    public static final PersonClass MAN = new PersonClass(22);
    public static final PersonClass WOMAN = new PersonClass(30);

    int age;
    private PersonClass(int a){
        this.age = a;
    }

    @Override
    public String toString() {
        return "I'm "+this.age+" old years.";
    }
}


class test{    
    public static void main(String[] args) {
        Music.Scale sc = Music.Scale.DO;
        Animal ai = Animal.CAT;
        Music.songScale(sc);
        //Music.songScale(ai); //compile error. type mismatch.
        out.println();

        //Person 열거형은, 내가 정의한 PersonClass와 문법적, 기능적으로 같음을 주목하라.
        //포인트는. enum의 열거된 변수명은, 단순한 int형 상수가 아니라,
        //클래스의 참조값을 public static final의 형태로 저장한 것임을 의미한다.

        //때문에 각각의 enum 맴버들은 클래스처럼 필드와 메소드를 가지고 있을 수 있다.
        out.println(Person.MAN);
        out.println(Person.WOMAN);

        out.println(PersonClass.MAN);
        out.println(PersonClass.WOMAN);
        out.println();

        
        //모든 열거형이 Enum<E> class를 상속받음을 주목하라.
        //모든 열거형 E는 Enum<E>라는 추상클래스를 상속받았으며,
        //Enum<E>는 여러가지 인터페이스를 구현한다.(Comparable도 있고... 자세한건 코드 참고)
        //당연히 Object의 메소드또한 가지고 있다.
        //아래 코드는 Scale 열거형이 Enum<Scale>을 상속받았음을 보여주고 있다.
        out.println(Music.Scale.RE.ordinal());
        out.println(Music.Scale.MI.ordinal());
        out.println(Music.Scale.DO.ordinal());
        if (Music.Scale.RE.compareTo(sc)>0)
            out.print("RE is fornt of DO");
        else if (Music.Scale.RE.compareTo(sc)<0)
            out.print("RE is back of DO");
        else
            out.print("RE is DO");            
    }
}