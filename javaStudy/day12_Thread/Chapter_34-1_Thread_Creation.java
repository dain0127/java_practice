/*
 * Chapter 34 쓰레드와 동기화 (Thread and snychronization)
 * 34-1 쓰레드의 이해와 생성
 * 34-2 쓰레드의 동기화
 * 34-3 쓰레드를 생성하는 더 좋은 방법
 * 
 * 
 * 
 * 34-1 쓰레드의 이해와 생성
 * 쓰레드는 운영체제에서도 배웠다시피, 프로그램의 실행 단위이다.
 * 쓰레드는 스스로가 존재하는 프로세스와 독립된 스택을 가지고 실행된다.
 * 
 * 
 */

import static java.lang.System.out;

class test{
    public static void main(String[] args) throws InterruptedException {
        //Thread.currentThread() 현재 해당 코드에서 실행되는 쓰레드가 반환된다.
        //현 코드는 main 메소드 상에 있으므로,
        //main 메소드를 실행하는 쓰레드가 나오게 된다.
        //이 쓰레드의 이름을 'main 쓰레드'라고 한다.
        Thread ct = Thread.currentThread();
        String name = ct.getName();
        out.println(name);
        out.println();


        //쓰레드 생성 및, 쓰레드가 실행할 task를 정의하는 코드
        Runnable task = () -> {
            int n1 = 10;
            int n2 = 10;
            String name1 = Thread.currentThread().getName();
            out.println(name1 + ": " + (n1+n2));
        };

        Thread t = new Thread(task);
        t.start();
        Thread.sleep(10);
        out.println("End " + Thread.currentThread().getName());
        out.println();
        out.println();



        /*
         * Thread.sleep()이라는 메소드를 통해 thread별로 반복문을 돌때마다 실행을 멈추도록한다.
         * 이후 실행 결과를 보면, 번갈아가서 실행하게 되었으므로, 짝수와 홀수가 순차적으로 출력됨을 확인할 수 있다.
         */

        //홀수만 출력하는 메소드가 정의된, Runnable 인터페이스를 구현한 인스턴스
        Runnable taskOdd = () -> {
            try {
                for(int i=0;i<20;i++){
                if(i%2 == 1)
                    out.print(" "+i);
                Thread.sleep(100); //0.1초
            }    
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        };
        //짝수만 출력하는 메소드가 정의된, Runnable 인터페이스를 구현한 인스턴스
        Runnable taskEven = () -> {
            try {
                for(int i=0;i<20;i++){
                if(i%2 == 0)
                    out.print(" "+i);
                Thread.sleep(100); //0.1초
            }    
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        /* Thread를 생성하는 첫번째 방법. (교재에서는 총 두가지를 설명한다.) */
        Thread tOdd = new Thread(taskOdd);
        Thread tEven = new Thread(taskEven);

        out.println("쓰레드의 생성 첫번째 방법");
        out.println("서로 다른 두 쓰레드에서 홀수와 짝수 번갈아가며 출력하기");
        tOdd.start();
        tEven.start();
        out.println("tOdd name : " + tOdd.getName());
        out.println("tEven name : " + tEven.getName());

        tOdd.join(); //쓰레드의 종료를 기다림
        tEven.join(); //쓰레드의 종료를 기다림
        
        out.println();
        out.println();

        /*
         * 해당 코드는 sleep을 뺌으로서, t1과 t2가 실행될때, 코어의 사정에따라 반드시 이상적으로 실행되지 않음을 보인다.
         * 다시 말해, 하드웨어적 그리고 운영체제적인 사정으로 실행결과를 매번 예측할 수 없음을 의미한다.
         */
        
        //홀수만 출력하는 메소드가 정의된, Runnable 인터페이스를 구현한 인스턴스
        //sleep 제외.
        Runnable taskOdd1 = () -> {
            for(int i=0;i<20;i++){
                if(i%2 == 1)
                    out.print(" "+i);
                //Thread.sleep(100); //0.1초
            }
        };
        //짝수만 출력하는 메소드가 정의된, Runnable 인터페이스를 구현한 인스턴스
        //sleep 제외.
        Runnable taskEven1 = () -> {
            for(int i=0;i<20;i++){
                if(i%2 == 0)
                    out.print(" "+i);
                //Thread.sleep(100); //0.1초
            }
        };
        Thread tOdd1 = new Thread(taskOdd1);
        Thread tEven1 = new Thread(taskEven1);

        Thread tOdd2 = new Thread(taskOdd1);
        Thread tEven2 = new Thread(taskEven1);

        Thread tOdd3 = new Thread(taskOdd1);
        Thread tEven3 = new Thread(taskEven1);

        out.println("쓰레드의 출력 결과가 매번 모두 다른 것을 확인");

        out.print("first : ");
        tOdd1.start();
        tEven1.start();
        Thread.sleep(1000); //0.1초
        out.println();

        out.print("second : ");
        tOdd2.start();
        tEven2.start();
        Thread.sleep(1000); //0.1초
        out.println();

        out.print("thrid : ");
        tOdd3.start();
        tEven3.start();
        Thread.sleep(1000); //0.1초
        out.println();
        out.println("모두 다른 것을 확인 가능. 즉, 예측 불가");
        out.println();


        /* Thread를 생성하는 두번째 방법 */
        /* 
         * Thread 클래스를 상속한, 클래스를 정의하고. 이후 인스턴스를 생성하여, start()메소드로 실행한다.
         * (참고로 Thread는 Runable 인터페이스를 구현하고 있다.)
         * 
         * 이때, Thread 클래스에서 정의된 run()메소드를 오버라이딩 하여, 개발자가 프로그램 로직에 맞는 기능을 구현해야한다.
         * 이후 start()메소드 호출시, run() 메소드의 기능이 쓰레드로서 실행된다.
         * 
         */

        class MyTask extends Thread{
            @Override
            public void run() {
                int n1 = 10;
                int n2 = 20;
                out.println("result : " + (n1 + n2));
            }
        }

        out.println("쓰레드의 생성 두번째 방법");

        MyTask myTask1 = new MyTask();
        myTask1.start();
        myTask1.join();
        out.println();
    }
}