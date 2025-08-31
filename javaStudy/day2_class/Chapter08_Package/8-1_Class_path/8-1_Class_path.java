/*
 * Chapter 08 Package and Class path
 * 8-1 Class path
 * 8-2 Package
 * 
 * 8-1 Class path
 * 상술했다시피, java가 소스코드에서 실행파일까지 가는 과정은 다음과 같다.
 * 1. .java 소스코드를 javac.exe라는 컴파일러가 바이트 코드(.class)로 변환
 * 2. 바이트코드를 java.exe라는 런처가 JVM위에 돌아가도록 한다.
 * 3. JVM은 운영체제에게 해당 바이트 코드(.class)를 인터프리트 방식으로 실행시킨다.
 * 
 * 이때 자바 런처가 class파일을 찾아 JVM을 실행시키기 위해서는, class가 존재하는 위치를 찾아야한다.
 * 이때 java.exe가 class를 찾는 경로를 class path라고 부른다.
 * 
 * main method가 있는 class파일을 런처가 실행시키는데,
 * 만약에 해당 class파일이 사용하는 다음 class type이 존재하지않으면, 해당 class의 정체를 알 수 없어 run time error가 나온다.
 * (이는 C++에서 링커가 다른 목적 파일을 찾지 못해, 외부에서 정의된 함수를 가져오지 못하는 것과 같다.)
 * 
 * 기본적으로 class path는 cmd창의 현재 디렉토리(.) 이다.
 * 이를 설정해주고 싶으면 set classpath=.;.\classDirName 방식으로 해주면 된다. (현재 디렉토라와 classDirName으로 class를 찾는다는 뜻.)
 * 
 * ===========================<실습 팁>=======================================
 * 실습을 위해서는, tset.java, "run_setClassPath.bat"의 내용을 확인한 다음,
 * ./run_setClassPath.bat 을 실행한다.
 * classpath 환경변수값을 set하고 java가 class파일을 잘 찾아서 실행하는 모습을 관찰하라.
 */

class Person{
    String name;
    int age;

    Person(String n, int a){
        name = n;
        age = a;
    }

    void showInfo(){
        System.out.println("name : "+name);
        System.out.println("age : " + age);
    }
}

class test{
    public static void main(String args[]){
        Person p1 = new Person("changin", 25);
        Person p2 = new Person("hello", 10);

        p1.showInfo();
        p2.showInfo();
    }
}