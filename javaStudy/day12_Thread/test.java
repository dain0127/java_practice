/*
 * Chapter 34 쓰레드와 동기화 (Thread and snychronization)
 * 34-1 쓰레드의 이해와 생성
 * 34-2 쓰레드의 동기화
 * 34-3 쓰레드를 생성하는 더 좋은 방법
 * 
 * 
 * 
 * 34-3 쓰레드를 생성하는 더 좋은 방법
 * 
 * 
 *  
 * 
 */

import static java.lang.System.out;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;



class test{
    static class Counter{
        int cnt = 0;

        synchronized public void reentrantIncrement(){
            ReentrantLock obj = new ReentrantLock();
            try {
                obj.lock();            
                this.cnt++;
            } finally {
                obj.unlock();
            }

        }

        synchronized public void reentrantDecrement(){
            ReentrantLock obj = new ReentrantLock();
            try {
                obj.lock();            
                this.cnt--;
            } finally {
                obj.unlock();
            }
        }

        public int getCount(){return this.cnt;}
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        //-------싱글 쓰레드 풀-------
        Runnable task1 = () -> {
            int n1 = 10;
            int n2 = 10;
            out.println(n1 + n2);
            out.println(Thread.currentThread().getName());
        };

        ExecutorService exr1 = Executors.newSingleThreadExecutor();
        exr1.submit(task1); //쓰레드 풀에 작업할 task를 넘겨준다.
        exr1.shutdown(); //쓰레드 풀에서 해당 쓰레드의 할당을 해제한다.
        out.println();

        //------여러개의 쓰레드 풀---------
        //출력 결과에서 번갈아가며 쓰레드가 task를 할당 받았음을 주목하라.
        Runnable task2 = () ->{
            out.println(Thread.currentThread().getName() + " : " + (10 + 12));
        };

        Runnable task3 = () ->{
            out.println(Thread.currentThread().getName() + " : " + (12 - 5));
        };

        ExecutorService exr2 = Executors.newFixedThreadPool(2);
        exr2.submit(task2);
        exr2.submit(task3);
        exr2.submit(()->{
            out.println(Thread.currentThread().getName() + " : " + (5*7));            
        });

        exr2.shutdown();

        out.println();
        out.println();

        //--------반환을 요구하는 task를 쓰레드에 할당시키기--------
        /*
         * Callable 인터페이스는...
         * V get() 라는 추상메소드를 가지고 있는, 기능형 인터페이스이다.
         * 
         */
        Callable<Integer> task4 = () ->{
            int result = 0;
            for(int i=1;i<=10;i++)
                result += i;
            return result;
        };

        ExecutorService exr3 = Executors.newSingleThreadExecutor();
        Future<Integer> fur1 = exr3.submit(task4);

        Integer num = fur1.get();
        out.println("result : " + num);
        out.println();

        exr3.shutdown();
        

        //----------Reentrant : lock() , unlock()-----------
        Counter c = new Counter();
        Runnable incTask = () -> {
            for(int i=0;i<1000;i++)
                c.reentrantIncrement();
        };

        Runnable decTask = () -> {
            for(int i=0;i<1000;i++)
                c.reentrantDecrement();
        };

        ExecutorService exr4 = Executors.newFixedThreadPool(2);
        exr4.submit(incTask);
        exr4.submit(decTask);

        exr4.shutdown();
        exr4.awaitTermination(100, TimeUnit.SECONDS);
        out.println("Count : " + c.getCount());
        out.println();
    }
}