/*
 * Chapter 23. Collection FrameWork 1
 * 컬렉션 프레임워크란, '자료구조'를 구현한 클래스들의 집합이다.
 * (자료구조란 데이터를 저장, 검색, 삭제 등. 데이터를 다루는 하나의 방식을 모아둔 것을 말한다.)
 * 이 클래스들은 Set, List, Queue, Map이라는 네 개의 인터페이스 중 하나를 구현한다.
 * 
 * 그리고 인터페이스간 상속관계는 다음과 같다.
 * List<E>, Set<E>, Queue<E> -> Collection<E> -> Interable<E>  // Map<K, V>은 독립
 * 
 * 각각의 인터페이스를 구현한 클래스들은 아래 코드를 참고하자.
 * 그리고 그 클래스들의 내부적인 동작 방식과 그에 따른 자세한 특징은,
 * 자료구조를 따로 공부해서 이해하자. (사실 이미 알고있다.)
 * 
 * 아래 코드는 각각의 인터페이스들이 구현해놓은 메소드를 써서 실습하는 코드이다.
 * 
 * 
 * 자료구조에서 중요한건. 저장, 검색(더불어 모든 요소 순회), 삭제.
 * 이 세가지를 각자의 자료구조 방식대로 잘~ 작동되도록, 프레임워크 클래스에서 내부적으로 잘~ 구현되어있다.
 * 개발자는 내부적인 성능과 특징. 그리고 적절한 API(저장, 검색, 삭제)를 잘 가져다 쓰면 된다.
 * 
 * 
 * 23-1 컬렉션 프레임워크에서 구현한 '자료구조'
 * 1) list
 * list는 다음과 같은 특징을 지니고 있는 자료구조이다.
 * -중복된 데이터를 허락한다.
 * -저장된 데이터는 순서가 존재한다.
 * 
 * 구현 방식에 따른 List<E>의 구현 클래스는 대표적으로 두가지가 있다.
 * -ArrayList<E>
 * -LinkedList<E>
 * 
 * 2) set
 * set은 다음과 같은 특징을 지니고 있는 자료구조이다.
 * -중복된 데이터를 허락하지 않는다.
 * -저장된 데이터의 순서를 무시한다.
 * 
 * 구현 방식에 따른 Set<E>의 구현 클래스는 대표적으로 두가지가 있다.
 * -HashSet<E>
 * -TreeSet<E>
 * 
 * 3) queue & stack(deque로 구현)
 * queue는 FIFO. stack은 LIFO이다.
 * 현재 stack은 java에서 deque(FIFO, LIFO가 자유로움)로 구현하는걸 권장하고 있다.
 * 
 * 구현 방식에 따른 Queue<E>의 구현 클래스는 한가지이다.
 * -LinkedList<E>
 * 
 * 구현 방식에 따른 Deque<E>의 구현 클래스는 대표적으로 두가지가 있다.
 * -ArrayDeque<E>
 * -LinkedList<E>
 * 
 * 참고로 LinkedList<E>는 List, Queue, Deque를 모두 구현한, 다재다능한 클래스이다.
 * 
 * 
 * 
 * 4) Map
 * key-value로 쌍을 이룬 데이터를 저장하는 자료구조이다.
 * key를 통해 데이터를 검색하도록 구현되어있다.
 * 순서의 개념은 없다.
 * 
 * Map은 Collection은 물론 Itarable 인터페이스를 상속하지 않았다.
 * (애초에 Map의 데이터 저장 방식이 K:V 이다. 단순 E만 있지도 않다. 뭐 할라면 할 수 있겠지만. 암튼 구현이 안되어있다.)
 * 때문에 순회 방식을 다르게 해야한다.
 * 
 * Map은 Key를 기반으로 데이터를 참조한다.
 * 따라서 Map의 모든 Key를 모아둔 컬렉션에서 하나씩 Key값을 꺼내, Map에 하나씩 참조하여 순회하는 방식을 택한다.
 * Key를 전부 모아둔 컬렉션은 Set으로 한다. 왜냐하면 Set은 중복을 허용하지 않고, Key간의 순서가 그다지 중요하지 않기 때문이다.
 * 
 * 
 * 
 * 23-2 Iterable 인터페이스. 그리고 Interator(반복자) 인터페이스.
 * Iterable 인터페이스는 Collection이 상속받는다. (Collection <- List, Set, Queue)
 * 
 * 그리고 Iterable 인터페이스는 Interator 인터페이스를 구현한,
 * 해당 클래스의 '전용 반복자' 인스턴스 참조값을 반환하도록 한다.
 * 
 * 해당 Iterator계열의 인스턴스는 대표적으로 두가지 메소드를 가지고 있다.
 * -hasNext() (참고로 맨 처음에. 반복자는 가상의 노드를 가리키고 있다.)
 * -next()
 * (-remove()도 있긴한데. 알아서 잘 사용해보라.)
 * 
 * 이를 이용해 Interator를 이용한 순회가 가능하며, (아래 List에서 순회 두번째(2) 방법 참고)
 * 아예 '컴파일러 단'에서 enhanced for가 배열뿐만 아니라,
 * Iterable을 구현한 클래스에 한하여, 적절한 iterator 생성 및, hasNext(), next()메소드를 통한
 * element의 순차적 접근이 가능하도록 지원하고 있다. (아래 List에서 순회 세번째(3) 방법 참고)
 * 
 */



import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


class test{
    public static void main(String args[]){
        /* ====================== 1. List ========================== */
        /*
        * 1) list
        * list는 다음과 같은 특징을 지니고 있는 자료구조이다.
        * -중복된 데이터를 허락한다.
        * -저장된 데이터는 순서가 존재한다.
        *
        * 구현 방식에 따른 List<E>의 구현 클래스는 대표적으로 두가지가 있다.
        * -ArrayList<E>
        * -LinkedList<E>
        */
        out.println("/* ====================== 1. List ========================== */");
        List<String> list1 = new ArrayList<>(); //ArrayList는 메모리의 연속된 공간에 데이터를 저장한다.
        List<String> list2 = new LinkedList<>(); //LinkedList 메모라의 흩어진 공간에서 서로를 참조한 채로 데이터를 저장한다.

        list1.add("one");
        list1.add("two");
        list1.add("three");

        //collection에서 중요한건 모든 요소의 순차적 접근 방식이다.
        //list로 모든 요소에 접근하는 방식 (1). index 이용.
        for(int i=0; i<list1.size(); i++)
            out.print(list1.get(i)+"\t");
        out.println();

        list1.remove(0);

        //list(Collection 하위 클래스)로 모든 요소에 접근하는 방식 (2). 반복자.
        for(Iterator<String> it = list1.iterator(); it.hasNext(); ){
            String str = it.next();
            out.print(str+"\t");

            if(str.equals("two"))
                it.remove(); //next()가리키게된 요소를 삭제한다.
        }
        System.out.println();

        list1.add(1, "hello");
        
        //list(Collection 하위 클래스)로 모든 요소에 접근하는 방식 (3). enhanced for.
        //(이는 컴파일러가 2번 방식으로 치환해준다.)
        //(즉, Iterable을 상속받은... Collection 인터페이스를 구현한 클래스만이 가능하다.)
        //(따라서, List의 구현체인 ArrayList<E>는 가능하다.)(Set이나 Queue의 구현체도 마찬가지...)
        for(String e : list1)
            out.print(e+"\t");
            out.println();

        //List를 구현한 클래스들이 두줄만에 초기화를 하는 방법.
        //기존의 array 클래스(int[] ...)는 int[] arr = {1,2,3} 방식으로 초기화가 바로 되었다.
        //이에 질투가 난 컬렉션 프레임워크도 해당 방식으로 초기화가 가능하다.

        /* ------- List들은 생성자의 종류가 하나 더 있다. -------- */
        /* public ArrayList<E>(Collection<? extends E> c) */
        /* public LinkedList<E>(Collection<? extends E> c) */
        /* public HashSet<E>(Collection<? extends E> c) */
        /*... */
        //얘네들은 void생성자를 통해서 데이터가 비어있는, 컬렉션을 구현한 인스턴스를 생성할수도 있지만.
        //인스턴스 생성할때부터 다른 Collection의 데이터를 전부 "복사"해서, 초기화 하도록 할 수 있다.
        //(추측키로는, Iterable 인터페이스가 구현되어있기 때문에, 반복자를 호출하여 전체 순회. 후 getter를 이용해서 복사한듯 보인다.)

        //이 생성자들은 아래와 같은 상황에서 사용이 된다.

        //1. 원하는 데이터를 컬렉션에 초기화하면서 객체를 생성하고 싶을때.
        //이는 Arrays.asList()가 기본적으로 List<E>로서 반환하지만, 내부적인 인스턴스는 Imutable하게 구현이 되어있기 때문에,
        //필연적으로, 해당 List를 곧이곧대로 쓸 수는 없다.(뭔가 이걸가지고 활용을 하려면, 읽기만 가능.)
        //때문에 보편적인 List class의 인스턴스를 확보하기 위해서, ArrayList(LinkedList)에서,
        //ArrayList(List<E>) 생성자를 통해 데이터를 복사해여 Mutable한 인스턴스를 반환하도록 했다.
        List<Integer> immutableTempList = Arrays.asList(10, 20, 30, 10);
        List<Integer> arrList = new ArrayList<>(immutableTempList);

        for(Integer e : arrList)
            out.print(e+ "\t");
        out.println();


        //2. 이미 존재하는 Collection 객체의 요소값들을, 다른 Collection 객체에 복사하고 싶을때.
        List<Integer> linkList = new LinkedList<>(arrList);
        for(Integer e : linkList)
            out.print(e+ "\t");
        out.println();


        //3. List의 중복값을 Set을 이용해 지우고 싶을때.
        //----------------List의 중복값을 Set을 이용해 지우는 방법-------------------//
        out.println("//----------List의 중복값을 Set을 이용해 지우는 방법----------//");
        
        List<Integer> temp = Arrays.asList(10, 20, 30, 10, 20);
        List<Integer> arrList1 = new ArrayList<>(temp);

        out.println("---- arrList1 before input set----");
        for(Integer e : arrList1)
            out.print(e+ "\t");
        out.println();

        //자동으로 데이터 복사할때, 중복 데이터가 걸러진다.
        Set<Integer> tempSet = new HashSet<>(arrList1);
        arrList1 = new ArrayList<>(tempSet);

        out.println("---- arrList1 after input set----");
        for(Integer e : arrList1)
            out.print(e+ "\t");
        out.println();



        /* ====================== 2. Set ========================== */
        /*
        * 2) set
        * set은 다음과 같은 특징을 지니고 있는 자료구조이다.
        * -중복된 데이터를 허락하지 않는다.
        * -저장된 데이터의 순서를 무시한다.
        *
        *
        * 구현 방식에 따른 Set<E>의 구현 클래스는 대표적으로 두가지가 있다.
        * -HashSet<E>
        * -TreeSet<E>
        */

        //-------------2-1.HashSet--------------//
        /*
         * 해쉬 테이블 알고리즘을 그대로 따른다.
         * 그래서 내부적으로 hashcode()로 반환된 hash값에 따라 인스턴스를 특정 row에 분류한다.
         * 
         * set의 조건때문에 중복을 검사할때는,
         * 삽입된 인스턴스의 hashcode()로 나온 값에 해당하는 row로 간다음.
         * 정의된 equals()를 통해 중복을 판단한다.
         * 
         * hashcode()랑 equals()는 Object에 정의되어 있으며,
         * 직접 정의한 클래스의 경우에는, 해당 메소드들을 오버라이딩을 해야, HashSet이 잘 잘동된다.
         */

        out.println("/* ====================== 2. Set ========================== */");
        out.println("//-------------2-1. HashSet--------------//");
        Set<String> set1 = new HashSet<>();
        set1.add("one");
        set1.add("two");
        set1.add("three");
        set1.add("one");//중복.
        set1.add("two");//중복.

        //반복자를 이용한 순회 방식. (2)
        for(Iterator it = set1.iterator(); it.hasNext();)
            out.print(it.next() + "\t");
        out.println();

        //equals()와 hashcCode()가 구현이 안된 클래스
        class NumNotOverride{
            private int num;
            NumNotOverride(int n){this.num = n;}
            @Override
            public String toString(){return String.valueOf(num);}
        }

        Set<NumNotOverride> set2 = new HashSet<NumNotOverride>();
        set2.add(new NumNotOverride(100));
        set2.add(new NumNotOverride(200));
        set2.add(new NumNotOverride(100));//중복

        //중복이 허용된 것 처럼 보이는 결과값이 나오게 된다.
        for(NumNotOverride e : set2)
            out.print(e + "\t");
        out.println();

        
        //equals()와 hashcCode()가 구현이 된 클래스
        class Num{
            private int num;
            Num(int n){this.num = n;}

            @Override
            public String toString(){return String.valueOf(num);}

            //해당 메소드로 반환된 해쉬 값에 따라, 해쉬 테이블(HashSet안에서 구현됨)에서 어떤 row에 저장될지 결정됨.
            @Override
            public int hashCode() {
                return this.num%3;
            }

            //해당 메소드를 통해, 중복을 최종적으로 판단함.
            @Override
            public boolean equals(Object obj) {
                return this.num == ((Num)obj).num;
            }
        }

        Set<Num> set3 = new HashSet<Num>();
        set3.add(new Num(100));
        set3.add(new Num(200));
        set3.add(new Num(100));//중복

        //정상적으로 중복처리가 된다.
        for(Num e : set3)
            out.print(e + "\t");
        out.println();



        //두개 이상의 필드 값을 가진 경우, 그 모든걸 고려해서 해쉬값을 반환하도록 하는게 좋다.
        //근데 머리가 아프면 Objects.hash(...) 메소드에 해쉬값을 해결하도록 책임을 양도해도 된다.
        //Object's' 다! Object가 아니라. 
        class Person{
            private String name;
            private int age;

            public Person(String n, int a){
                this.name = n;
                this.age = a;
            }

            @Override
            public String toString() {
                return name+":"+age;
            }

            @Override
            public int hashCode() {
                return (name.hashCode() + age)%10;
            }

            @Override
            public boolean equals(Object obj) {
                Person p = (Person)obj;
                if(p.name.equals(this.name) && p.age == this.age)
                    return true;
                else
                    return false;
            }
        }

        Set<Person> pSet = new HashSet<Person>();
        pSet.add(new Person("changin", 25));
        pSet.add(new Person("hello", 2));
        pSet.add(new Person("hello", 19));
        pSet.add(new Person("changin", 25));//중복

        //정상적으로 중복처리가 된다.
        for(Person e : pSet)
            out.print(e + "  ");
        out.println();



        //-------------2-1. TreeSet--------------//
        /*
         * BST 알고리즘을 그대로 따른다.
         * TreeSet내부에서 값의 대소를 비교하기 위해서는 compareTo()라는 메소드가 정의가 되어야한다.
         *
         * compareTo(E o)라는 메소드는 Compareable<E>이라는 인터페이스를 구현해야한다.
         * (this.o > o ==> 양수 반환)
         * (this.o == o ==> 0 반환)
         * (this.o < o ==> 음수 반환)
         * (보통 int형의 경우 뺄셈으로 구현하며, 내림차순 정렬을 원하면, 반환값을 뒤집으면 된다.)
         * 직접 정의한 클래스의 경우에는, 해당 메소드들을 오버라이딩을 해야, TreeSet이잘 잘동된다.
         * 
         * 참고로 반복자의 참조는 중위순회이다. (출력이 오름차순임을 주목하라.)
         * 
         * 
         * 만약에 class에서 이미 정의된 compareTo(E o)를 바꾸지 않고,
         * 비교의 기준을 바꾸고 싶다면, Comparator<E> 인터페이스를 구현하여,
         * int compare(E o1, E o2)를 구현하면 된다.
         * 
         * 해당 인터페이스를 구현한 클래스의 인스턴스를, TreeSet<E>의 생성자.
         * TreeSet<E>(Comparator<? super E> comparator)에 대입하여 인스턴스를 호출하면 된다.
         *  
         */
        out.println("//-------------2-2. TreeSet--------------//");

        Set<Integer> tSet1 = new TreeSet<>();
        tSet1.add(1); tSet1.add(3); tSet1.add(2); tSet1.add(4);
        
        for(Integer e : tSet1)
            out.print(e + "\t");
        out.println();



        //Person CompareAble
        class PersonCA implements Comparable<PersonCA>{
            String name;
            int age;

            public PersonCA(String n, int a){
                this.name = n;
                this.age = a;
            }

            @Override
            public String toString() {
                return name+":"+age;
            }

            //TreeSet에게 비교의 기준을 오버라이딩을 통해 개발자가 제공.
            public int compareTo(PersonCA o) {
                return this.age - o.age;
            };
        }

        Set<PersonCA> tPSet2 = new TreeSet<>();
        tPSet2.add(new PersonCA("changin", 25));
        tPSet2.add(new PersonCA("bye", 50));
        tPSet2.add(new PersonCA("hello", 19));
        tPSet2.add(new PersonCA("changin", 25));//중복
        
        for(PersonCA e : tPSet2)
            out.print(e + "  ");
        out.println();




        //PersonCA에 있는 compareTo 메소드를 직접 변경하지 않고,
        //TreeSet의 비교 기준을 바꾸는 방법.
        //(이는 Integer나 String같이 이미 CompareTo가 잘 정의된 함수를 건들지 않고.)
        //(새로운 방식의 비교 기준을 제시할때 사용한다.)
        class PersonComparator implements Comparator<PersonCA>{
            @Override
            public int compare(PersonCA o1, PersonCA o2) {
                return o1.name.length() - o2.name.length();
            }
        }

        Set<PersonCA> tPSet3 = new TreeSet<>(new PersonComparator());
        tPSet3.add(new PersonCA("changin", 25));
        tPSet3.add(new PersonCA("bye", 2));
        tPSet3.add(new PersonCA("hello", 19));
        tPSet3.add(new PersonCA("changin", 25));//중복
        
        //출력 순서가 바뀜에 주목하라.
        for(PersonCA e : tPSet3)
            out.print(e + "  ");
        out.print("   :(Comparator)출력 순서가 바뀜에 주목하라.");
        out.println();


        /* ====================== 3. Queue/Deque(Stack 구현) ========================== */
        /*
        * 3) queue & stack(deque로 구현)
        * queue는 FIFO. stack은 LIFO이다.
        * 현재 stack은 java에서 deque(FIFO, LIFO가 자유로움)로 구현하는걸 권장하고 있다.
        * 
        * 구현 방식에 따른 Queue<E>의 구현 클래스는 한가지이다.
        * -LinkedList<E>
        * 
        * 구현 방식에 따른 Deque<E>의 구현 클래스는 대표적으로 두가지가 있다.
        * -ArrayDeque<E>
        * -LinkedList<E>
        * 
        * 참고로 LinkedList<E>는 List, Queue, Deque를 모두 구현한, 다재다능한 클래스이다.
        */
        //-----------------Queue---------------------
        out.println("/* ====================== 3. Queue/Deque(Stack 구현) ========================== */");
        out.println("//-----------------3-1. Queue---------------------");

        Queue<String> que1 = new LinkedList<>();//LinkedList()는 List, Queue, Deque 기능이 전부 구현되어있다.
        que1.offer("first");
        que1.offer("second");
        que1.offer("third");

        out.println("next(peek) : "+que1.peek());
        out.println("next(poll) : "+que1.poll());
        out.println("next(peek) : "+que1.peek());
        out.println("next(poll) : "+que1.poll());
        out.println("next(poll) : "+que1.poll());

        //-----------------Deque(Stack 구현)---------------------
        out.println("//------------3-2. Deque(Stack 구현)----------------");
        interface DIStack<E>{
            void push(E o);
            E pop();
            E peek();
        }

        class DCStack<E> implements DIStack<E>{
            private Deque<E> deq;
            DCStack (Deque<E> d){this.deq = d;}

            public void push(E o) {
                deq.offerFirst(o);
            };

            public E pop() {
                return deq.pollFirst();
            };

            public E peek() {
                return deq.peekFirst();
            };
        }

        Deque<String> deq1 = new ArrayDeque<>();
        Deque<String> deq2 = new LinkedList<>();//LinkedList()는 List, Queue, Deque 기능이 전부 구현되어있다.
        
        DCStack<String> stk1 = new DCStack<>(deq1);
        stk1.push("first");
        stk1.push("second");
        stk1.push("third");

        out.println("next(peek) : "+stk1.peek());
        out.println("next(pop) : "+stk1.pop());
        out.println("next(peek) : "+stk1.peek());
        out.println("next(pop) : "+stk1.pop());
        out.println("next(pop) : "+stk1.pop());

        /* ====================== 4. Map ========================== */

        out.println("/* ====================== 4. Map ========================== */");
        out.println("//---------------HashMap-------------------");
        Map<Integer, String> hashMap1 = new HashMap<>();

        hashMap1.put(45, "hello");
        hashMap1.put(37, "bye");
        hashMap1.put(33,"welcome");

        out.println("45 : "+hashMap1.get(45));
        out.println("37 : "+hashMap1.get(37));
        out.println("33 : "+hashMap1.get(33));

        //순회
        Set<Integer> ks1 = hashMap1.keySet();
        
        for(Integer n : ks1)
            out.println(hashMap1.get(n));
        out.println();


        out.println("//---------------TreeMap-------------------");
        Map<Integer, String> treeMap1 = new TreeMap<>();

        treeMap1.put(45, "hello");
        treeMap1.put(37, "bye");
        treeMap1.put(33,"welcome");

        out.println("45 : "+treeMap1.get(45));
        out.println("37 : "+treeMap1.get(37));
        out.println("33 : "+treeMap1.get(33));

        //순회
        Set<Integer> ks2 = treeMap1.keySet();
        
        for(Integer n : ks2)
            out.println(treeMap1.get(n));
        out.println();
    
    }
}