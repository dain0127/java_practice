import java.util.Comparator;
import java.util.TreeSet;


//TreeSet이 Interger를 내림차순으로 출력하도록, Comparator를 이용해서 정렬 기준을 바꾸어라.
public class quiz_23_2 {
    static class IntegerComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.intValue() - o1.intValue();
        }
    }

    public static void main(String[] args) {
        TreeSet<Integer> tr1 = new TreeSet<>();
        tr1.add(30);
        tr1.add(10);
        tr1.add(20);
        System.out.println(tr1);

        TreeSet<Integer> tr2 = new TreeSet<>(new IntegerComparator());
        tr2.add(30);
        tr2.add(10);
        tr2.add(20);
        System.out.println(tr2);
    }
}
