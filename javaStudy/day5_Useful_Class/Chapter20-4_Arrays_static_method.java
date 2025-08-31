/* Chapter 20 자바의 기본 클래스
 * 20-1 Wrapper 클래스
 * 20-2 BigInteger 클래스와 BigDecimal 클래스
 * 20-3 Math 클래스와 난수의 생성, 그리고 문자열 토큰(Token)의 구분
 * 20-4 Arrays 클래스
 * 
 * 20-4 Arrays
 * 1) 복사
 * 2) 비교
 * 3) 정렬
 * 4) 탐색
 * 
 * 자세한건 아래 코드 참조.
 * 
 */

import static java.lang.System.out;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

class Person implements Comparable{
    private int age;
    private String name;

    Person(int a, String n){
        this.age = a;
        this.name = n;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.age == ((Person)obj).age
        && this.name.equals(((Person)obj).name))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return name + " : " + age + '\n';
    }

    @Override
    public int compareTo(Object o) {
        return this.age - ((Person)o).age;
    }
}

class test{
    static <T> void printArray(T[] arr){
        for(T obj : arr){
            out.print(obj + " ");
        }
        out.println();
    }

    public static void main(String[] args) {
        //===========array의 복사 copyOf(), copyOfRange(),arraycopy()=========
        Double[] dArr1 = {1.1, 2.3, 3.1, 4.2, 5.4};
        Double[] dArr2 = Arrays.copyOf(dArr1, 4);
        Double[] dArr3 = Arrays.copyOfRange(dArr1, 1, 3);
        
        printArray(dArr1);
        printArray(dArr2);
        printArray(dArr3);

        Double[] dArr4 = new Double[5];
        System.arraycopy(dArr1, 1, dArr4, 2, 3);

        printArray(dArr4);
        out.println();

        //===============array의 비교==========================
        Character[] chArr1 = {'h','e','l','l','o'};
        Character[] chArr2 = Arrays.copyOf(chArr1, 5);

        if(Arrays.equals(chArr1, chArr2) == true)
            out.println("same array");
        else
            out.println("diff array");
        out.println();

        

        Person[] n1 = new Person[3];
        Person[] n2 = new Person[3];
        
        n1[0] = new Person(1, "aaa"); n2[0] = new Person(1, "aaa");
        n1[1] = new Person(2, "aaa"); n2[1] = new Person(2, "aaa");
        n1[2] = new Person(3, "aaa"); n2[2] = new Person(3, "aaa");

        //Object로도 오버로딩 되어있음.
        //그리고 내부적으로 Object의 equals()를 오버로딩한 걸 이용해 값을 비교함.
        if(Arrays.equals(n1, n2))
            out.println("same array");
        else
            out.println("diff array");
        out.println();

        //===============array의 정렬======================
        Integer[] iArr1 = new Integer[10];
        Random rand = new Random();

        for(int i=0;i<10;i++)
            iArr1[i] = rand.nextInt(0, 10);

        printArray(iArr1);
        Arrays.sort(iArr1);
        printArray(iArr1);
        out.println();

        //직접 정의한 class의 sort
        //Compareable 인터페이스를 구현하여, compareTo(Object o)의 반환값을 잘 반환하도록 정의해야함
        //o보다 크면 양수, o보다 작으면 음수, o랑 같으면 0이 반환되도록 해야함
        //그러면 Arrays.sort()라는 함수가 알아서 오름차순 정렬로 기능함.
        Person[] pArr1 = new Person[7];
        for(int i=0;i<7;i++)
            pArr1[i] = new Person(rand.nextInt(1,20),"bbb");
        printArray(pArr1);
        Arrays.sort(pArr1);
        printArray(pArr1);

        //===============array의 탐색======================
        //binarySearch()도 Compareable의 메소드인 compareTo()에 구현에 따라 결정된다.
        //즉 compareTo()의 반환값이 0인걸 key에 매치 되었다고 판단하여 검색을 한다.
        Person[] pArr2 = new Person[7];
        for(int i=0;i<7;i++)
            pArr2[i] = new Person(rand.nextInt(1,4),"ccc");
        printArray(pArr2);
        Arrays.sort(pArr2); //정렬 먼저
        printArray(pArr2);
        int idx = Arrays.binarySearch(pArr2, new Person(5, "adc"));
        out.println("idx : "+idx);
        if(idx >= 0)
            out.println("found obj : " + pArr2[idx]);
        else
            out.println("not found");
    }
}

