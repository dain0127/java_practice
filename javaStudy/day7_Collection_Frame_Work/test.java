/*
 * Chapter 24. Collection FrameWork 2
 * 
 * 사실상 제네릭에 제한에 대한 문법적 고찰이 주된 내용이다.
 * 
 * 1. extands나 super는 단순히 제한을 하기 위해 존재한다.
 * 특히 extands T는 Object는 못쓰는 T의 메소드를 사용 할 수 있게 해준다.
 * 
 * 2. getter 전용과 setter 전용으로 제한이 가능하다.
 * 
 * 3. Compareable 따위의 특정 인터페이스는,
 * 인터페이서를 간접적으로 구현받은 하위 클래스에 대해서도,
 * compareTo나 compare따위의 메소드를 사용할 수 있도록 하기 위해서,
 * ? super T 로서 제한한다.
 * 자세한건 내가 직접 정의한 아래 코드들을 참조하라.
 * 
 * 참고로 Collections에 있는 sort(), binarysearch(), copy()는 덤이다.
 * 
 */

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class MyUtil {
    //삽입정렬 내가 직접 구현한거
    static public <T extends Comparable<? super T>> void mySort(List<T> list){
        //out.println(list.size());

        //삽입 정렬 구현
        for(int i=1;i<list.size();i++){
            //정렬된 배열에 삽입할 타겟 요소.
            T target = list.get(i);
            //out.print("target : "+ target+" ");
            for(int j=0;j<=i-1;j++){
                T key = list.get(j);
                //out.print("key : "+ key+" ");
                //타겟 요소보다 큰 첫번째 요소.
                if(target.compareTo(key) < 0){
                    //out.println("find position");
                    //j를 비워둘때까지 한칸씩 밀기
                    for(int k=i-1;k>=j;k--){
                        T temp = list.get(k);
                        list.set(k+1, temp);
                        //out.print("temp : " +temp+" ");
                    }
                    //j에 타겟 요소 삽입하기
                    list.set(j, target);
                    break;
                }
            }
            //out.println();
        }
    }

    //삽입정렬 내가 직접 구현한거. comprator 버전
    static public <T> void mySort(List<T> list, Comparator<? super T> comp){
        //out.println(list.size());

        //삽입 정렬 구현
        for(int i=1;i<list.size();i++){
            //정렬된 배열에 삽입할 타겟 요소.
            T target = list.get(i);
            //out.print("target : "+ target+" ");
            for(int j=0;j<=i-1;j++){
                T key = list.get(j);
                //out.print("key : "+ key+" ");
                //타겟 요소보다 큰 첫번째 요소.
                if(comp.compare(target, key) < 0){
                    //out.println("find position");
                    //j를 비워둘때까지 한칸씩 밀기
                    for(int k=i-1;k>=j;k--){
                        T temp = list.get(k);
                        list.set(k+1, temp);
                        //out.print("temp : " +temp+" ");
                    }
                    //j에 타겟 요소 삽입하기
                    list.set(j, target);
                    break;
                }
            }
            //out.println();
        }
    }

    static public <T> int binarySearch(List<? extends Comparable<? super T>> list, T key){
        int endIdx = list.size() - 1;
        int startIdx = 0;

        while(startIdx <= endIdx){
            int middleIdx = (startIdx + endIdx) >>> 1;
            int comResult = list.get(middleIdx).compareTo(key);
            
            if(comResult < 0)
                startIdx = middleIdx + 1;
            else if(comResult > 0)
                endIdx = middleIdx - 1;
            else if(comResult == 0)
                return middleIdx;

        }

        return -1;
    }

    static public <T> int binarySearch(List<? extends T> list, T key,
    Comparator<? super T> comp){
        int endIdx = list.size() - 1;
        int startIdx = 0;
        int middleIdx = (startIdx + endIdx) >>> 1;

        while(startIdx <= endIdx){
            int comResult = comp.compare(list.get(middleIdx), key);
            
            if(comResult < 0)
                startIdx = middleIdx + 1;
            else if(comResult > 0)
                endIdx = middleIdx - 1;
            else if(comResult == 0)
                return middleIdx;

            middleIdx = (startIdx + endIdx)/2;
        }

        return -1;
    }

    static public <T> void printArray(List<T> list){
        for(T e : list)
            out.print(e+" ");
        out.println();
    }
}



class test{
    public static void main(String args[]){ 
        List<String> temp = Arrays.asList("hello", "apple", "cat", "banana");
        List<String> list1 = new LinkedList<>(temp);
        List<String> list2 = new LinkedList<>(temp);

        MyUtil.mySort(list1);
        MyUtil.printArray(list1);

        Collections.sort(list2);
        MyUtil.printArray(list2);
        out.println();


        class Car implements Comparable<Car>{
            protected int disp; //배기량

            public Car(int d){this.disp = d;}

            @Override
            public String toString() {return "disp : " + disp;}

            @Override
            public int compareTo(Car o) {return this.disp - o.disp;};
            
            @Override
            public boolean equals(Object obj) {
                return this.disp == ((Car)obj).disp;
            }
        }

        class ECar extends Car{
            private int battery;

            public ECar(int d, int b){super(d); this.battery = b;}
            
            @Override
            public String toString(){
                return super.toString() + " battery : " +battery;
            }
        }

        //일반적인 Car에 대한 정렬.
        List<Car> listCar1 = new LinkedList<>();
        listCar1.add(new Car(10));
        listCar1.add(new Car(30));
        listCar1.add(new Car(20));
        listCar1.add(new Car(40));

        List<Car> listCar2 = new LinkedList<>(listCar1);

        //Comparable 인터페이스를 구현한 객체 넘겨주는 방식.
        //내부적으로는 해당 객체의 compareTo를 이용.
        MyUtil.mySort(listCar1);
        MyUtil.printArray(listCar1);
    
        Collections.sort(listCar2);
        MyUtil.printArray(listCar2);

        out.println();


        //하위 클래스인 Ecar에 대한 정렬.
        List<ECar> listECar1 = new LinkedList<>();
        listECar1.add(new ECar(10, 1));
        listECar1.add(new ECar(30,1));
        listECar1.add(new ECar(20,1));
        listECar1.add(new ECar(40,1));

        List<ECar> listECar2 = new LinkedList<>(listECar1);
        List<ECar> listECar3 = new LinkedList<>(listECar1);
        List<ECar> listECar4 = new LinkedList<>(listECar1);

        //Comparable 인터페이스를 구현한 객체 넘겨주는 방식.
        //내부적으로는 해당 객체의 compareTo를 이용.
        MyUtil.mySort(listECar1);
        MyUtil.printArray(listECar1);

        //Comprator를 구현한 객체를 넘겨주는 방식.
        //내부적으로는 해당 객체의 compare를 이용.
        MyUtil.mySort(listECar2, (o1, o2) -> o1.disp - o2.disp);
        MyUtil.printArray(listECar2);

        //Collections의 sort (1). E extends Comparable<E>.
        Collections.sort(listECar3);
        MyUtil.printArray(listECar3);

        //Collections의 sort (2). 두번째 인자값에 Comparable<E>의 인스턴스를 넘겨줌
        Collections.sort(listECar4, (o1, o2) -> o1.disp - o2.disp);
        MyUtil.printArray(listECar4);

        out.println();

        
        //검색
        int resultIdx = Collections.binarySearch(listECar1, listECar1.get(1));
        out.println(resultIdx);

        resultIdx = Collections.binarySearch(listECar1, listECar1.get(3),
        (o1, o2) -> o1.disp - o2.disp);
        out.println(resultIdx);

        resultIdx = MyUtil.binarySearch(listECar1, listECar1.get(3));
        out.println(resultIdx);

        resultIdx = MyUtil.binarySearch(listECar1, listECar1.get(2),
        (o1, o2) -> o1.disp - o2.disp);
        out.println(resultIdx);

        out.println();

        //복사
        List<Integer> temp2 = Arrays.asList(1,3,2,4);
        List<Integer> list3 = new ArrayList<>(temp2);
        List<Integer> list4 = new LinkedList<>(temp2);

        Collections.sort(list3);//얘만 정렬

        Collections.copy(list4, list3);
        MyUtil.printArray(list4);
        out.println();

    }
}