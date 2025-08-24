/*
 * Chapter 08 Package and Class path
 * 8-1 Class path
 * 8-2 Package
 * 
 * 8-2 Package.
 * Package란 class file들의 집합이다.
 * Package를 사용하는 이유는 C++의 namespace와 일맥한다.
 * Package의 이름에 따라서 분류된 class들의 목적을 확실히 할 수 있고, 정리도 쉬워진다.
 * 
 * package는 class들의 접근 방법을 구분하며, '디렉토리'에 저장되는 '위치'또한 달라진다.
 * 
 * Package를 생성하는 방법은, 소스코드 맨 위에 package (package_name); 을 명시한다.
 * 이후, javac -d (생성 dir) (컴파일 대상 소스코드)로 directory를 생성해준다.
 * (-d 옵션은 패키지에 따라 디렉토리를 생성하고, 그 안에 class 파일을 넣는다.)
 * (자세한 실습 환경은 run_package.bat 참고)
 * 
 * 그리고 package 안에 있는 class를 쓰는 방식은 두가지가 있다.
 * 1. package 전체를 import하는 방식
 * 2. 특정 package의 특정 class만 import하는 방식
 * 
 * 이후 java.exe를 실행하면, class path를 훑어보며, package에 있는 class의 정의를 가져와, JVM에 작동시킨다.
 */



//지금 vscode에서 제공하는 compile error는 무시하자. 소스코드가 곧 바이트코드의 위치에 있는 패키지여야한다고 주장하는 멍청한 놈이다.
import diagram.circle.*; //1. package 전체를 import하는 방식
import diagram.polygon.Rectangular;//2. class명을 풀네임으로 썼다고 간주할 수 있다.


class test{
    public static void main(String args[]){
        diagram.polygon.Triangle tri = new diagram.polygon.Triangle(10, 2);
        Circle cir = new Circle(10, 3.14);
        Rectangular rec = new Rectangular(10, 4);

        tri.showAreaInfo();
        cir.showAreaInfo();
        rec.showAreaInfo();
    }
}