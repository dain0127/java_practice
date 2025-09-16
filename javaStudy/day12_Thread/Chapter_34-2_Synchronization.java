/*
 * Chapter 34 쓰레드와 동기화 (Thread and snychronization)
 * 34-1 쓰레드의 이해와 생성
 * 34-2 쓰레드의 동기화
 * 34-3 쓰레드를 생성하는 더 좋은 방법
 * 
 * 
 * 
 * 34-2 쓰레드의 동기화
 * 
 * 쓰레드의 동기화 같은 경우. 두 쓰레드가 실행하고자 하는 코드가, 같은 메모리에 접근하려고 할때.
 * 운영체제단에서 쓰레드간의 context swtich같은 것이 일어날 수 있다. 이때,
 * 하나의 쓰레드가 실행하던 코드를 코어가 수행하던 도중. 명령어 사이클을 깨뜨리는 흐름 변화가 일어나,
 * 미처 메모리에 계산값을 저장하기 전에, 다른 쓰레드의 코드가 수행되는 일이 일어난다.
 * 
 * 이를 race condtion이라고 부르며, 이러한 동기화 문제를 해결하기 위한 여러 해결방안이 고안되어있다.
 * 
 * 자바에서는 synchronized라는 키워드를 통해 해결하며, 내부적으로는,
 * critical point에서 한 쓰레드가 원자적으로 해당 코드를 실행하는 동안, 다른 쓰레드의 접근을 막는 것이다.
 * 
 * 두 개 이상의 쓰레드가 공유하고 있는 메모리에 읽기/쓰기 작업 따위가 일어날때, 해당 키워드를 사용하여 해결이 가능하다.
 * 
 * 해당 키워드를 사용하는 상황도 이해하는 것이 중요하지만,
 * 내부적으로 어떤 문제가 발생하여, 어떻게 해결이 되었는지 이해하는 것 또한 필요하다.
 *  
 * 
 */

import static java.lang.System.out;

class test{
    static class Count{
        int cnt = 0;

        public void asyncIncrement(){this.cnt++;}
        public void asyncDecrement(){this.cnt--;}


        /*
         * synchronized 키워드.
         * 해당 키워드가 '한 클래스 내부'에 있는 동기화 키워드가 붙은 코드나 메소드들은 전부,
         * 하나의 Thread가 접근하면, 다른 Thread는 접근하지 못하도록, 상호베타적으로 만든다.
         * 
         */

        synchronized public void syncIncrement_1(){
            this.cnt++;
        }

        synchronized public void syncDecrement_1(){
            this.cnt--;
        }
        
        /*
         * 동기화 블록.
         * '한 클래스 내부'에 있는 동기화 블록은 '전부',
         * 한 쓰레드가 동기화 블록 코드에 접근할때, 다른 쓰레드가 접근하지 못하도록 한다.
         * 
         * 즉, 쓰레드에 대해서 상호배제적인 기능을 가지고 있다.
         */
        public void syncIncrement_2(){
            synchronized(this){
                this.cnt++;
            }
        }
        public void syncDecrement_2(){
            synchronized(this){
                this.cnt--;
            }
        }

        public int getCount(){return this.cnt;}
    }

    public static void testThreadAsynchronization(int n) throws InterruptedException {
        Count cnt1 = new Count();
        Runnable incTask1 = () -> {
            for(int i=0 ; i<10000; i++)
                cnt1.asyncIncrement();
        };
        Runnable decTask1 = () -> {
            for(int i=0 ; i<10000; i++)
                cnt1.asyncDecrement();
        };

        Thread incT1 = new Thread(incTask1);
        Thread decT1 = new Thread(decTask1);
        incT1.start();
        decT1.start();

        incT1.join();
        decT1.join();
        out.println("count" + n + " : " + cnt1.getCount());
    }

    public static void testThreadSynchronization(int n) throws InterruptedException {
        Count cnt1 = new Count();
        Runnable incTask1 = () -> {
            for(int i=0 ; i<10000; i++)
                cnt1.syncIncrement_1();
        };
        Runnable decTask1 = () -> {
            for(int i=0 ; i<10000; i++)
                cnt1.syncDecrement_1();
        };

        Thread incT1 = new Thread(incTask1);
        Thread decT1 = new Thread(decTask1);
        incT1.start();
        decT1.start();

        incT1.join();
        decT1.join();
        out.println("count" + n + " : " + cnt1.getCount());
    }



    public static void main(String[] args) throws InterruptedException {
        //공유하는 메모리에 복수의 쓰레드가 접근할 시,
        //결과를 예측하지 못함을 보여주는 실습 코드.
        //결과가 0이 아님을 확인할 수 있는데,
        //이는 쓰레드가 공유 메모리에 접근하면 문제를 일으킬 수 있다는 의미이다.
        for(int i=1 ;i<=5 ;i++)
            testThreadAsynchronization(i);

        out.println();
        
        //동기화가 적용된 함수 호출
        for(int i=1 ;i<=5 ;i++)
            testThreadSynchronization(i);
    }
}