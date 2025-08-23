/*
 * 
 */

//vscode에서 제공하는 compile error는 무시하자. 소스코드가 곧 바이트코드의 위치에 있는 패키지여야한다고 주장하는 멍청한 놈이다.

/*
 * 두가지 방식이 있다.
 * 1. package 전체를 import하는 방식
 * 2. 특정 package의 특정 class만 import하는 방식
 */

import java.awt.Rectangle;

import diagram.circle.*; //1. package 전체를 import하는 방식
import diagram.polygon.Rectangular;//2. class명을 풀네임으로 썼다고 가정할 수 있다.


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