package Quiz;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class quiz_27_1_2 {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("Robot");
        list.add("Lambda");
        list.add("Box");

        Collections.sort(list, (s1, s2) -> s1.length() - s2.length());
        
        for(String e : list)
            System.out.println(e);
    }
}