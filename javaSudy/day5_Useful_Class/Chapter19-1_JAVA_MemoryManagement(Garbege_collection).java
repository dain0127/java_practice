/*
 * 번외. java의 메모리 모델 
 * 그냥 우리가 알고있는... 운영체제에서 메모리를 세그먼트로 분리하는걸 말한다. (text, data, heap, stack)
 * 근데 이거는 OS에서 HW 자원을 효율적으로 분할해서 프로그램한테 제공하는 방식이야.
 * 
 * java는 바이트 코드를 인터프리트방식으로 JVM에 돌린다. JVM도 결국 OS위에서 돌아가는 응용 프로그램의 일종이다.
 * JVM이 OS에 메모리를 할당받으면, 그걸 가지고 바이트 코드를 받아서, 메모리 모델에 따라 데이터를 관리한다.
 * 
 * method 영역: method 코드 + static 변수 (text 영역이랑 data영역 합친거임)
 * heap 영역 : instance.
 * stack 영역 : 지역변수(기본형 변수 + 참조 변수), 매개변수, 
 * 
 * ★★★★★★★★★★★★★★★★★
 * GC (garbege collection) : 얘는 heap에 존재하는 instance를 아무도 가리키지 않을때, 할당을 적절한 타이밍에 JVM에 해제해준다.
 * (C++이나 C에서 굳이 delete나 free()로 할당 해제를 개발자의 책임으로 돌리지 않는다.)
 * JVM의 GC 알고리즘이 합리적이기 때문에, 개발자가 JVM의 할당 해제 작업 타이밍에 직접적 관여는 하지 못한다.
 * 
 */

/*
 * 실습코드는, java의 heap영역 할당은 전체 시스템(네가 가지고있는 컴퓨터의 물리적 자원의 총량)에서
 * heap에 기본적으로 1/64 만큼 할당하고,
 * maximun으로는 1/4만큼 할당하는걸 보이는 것이다.
 * 
 * 지금 집에있는 컴퓨터가 16GB였으니 최대 4GB 힙에 할당이 가능하다.
 * 10^30 개의 int형 배열까지만, heap에 딱 알맞게 할당이 가능하다.
 * 
 * 이걸 넘어서 OutOfMemory라는 Error 클래스가 throw되는걸 확인할 수 있다.
 * (Error를 상속받은 종류의 예외 클래스는 하드웨어적인 것도 확인 할 수 있다.)
 *  
 */

import java.lang.OutOfMemoryError;

class MemoryCheck {
    public static void main(String[] args) {
        long heapMax = Runtime.getRuntime().maxMemory();
        long heapInit = Runtime.getRuntime().totalMemory();
        System.out.println("초기 Heap 크기: " + heapInit / (1024*1024) + " MB");
        System.out.println("최대 Heap 크기: " + heapMax / (1024*1024) + " MB");

        
        if (new OutOfMemoryError() instanceof Error){
            System.out.println("OutOfMemoryError is one of Error");
        } else{
            System.out.println("OutOfMemoryError is not one of Error");
        }


        int[][][] arr = new int[1024][1024][1024]; //4*1024^3 byte = 4GB = 4096MB
        //OutOfMemory


    }
}
