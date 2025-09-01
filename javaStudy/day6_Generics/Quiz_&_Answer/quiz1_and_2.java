/*
 * page 496.
 * 제네릭 문법 테스트.
 */

import static java.lang.System.out;

class DDbox<U, D>{
    private U up;
    private D down;

    public void set(U u, D d){
        this.up = u;
        this.down = d;
    }

    @Override
    public String toString(){
        return up + "\n" + down;
    }
}

class Dbox<L, R>{
    private L left;
    private R right;

    public void set(L l, R r){
        this.left = l;
        this.right = r;
    }

    @Override
    public String toString(){
        return left + " & " + right;
    }
}


class quiz1_and_2 {
    public static void main(String[] args){
        //Quiz 1
        Dbox<String, Integer> dBox1 = new Dbox<>();
        Dbox<String, Integer> dBox2 = new Dbox<>();
        DDbox<Dbox<String, Integer>, Dbox<String, Integer>> ddBox1 = new DDbox<>();

        dBox1.set("Apple", 1000);
        dBox2.set("Orange", 1500);
        ddBox1.set(dBox1, dBox2);

        out.println(ddBox1);
        out.println();

        //Quiz 2
        Dbox<Dbox<String, Integer>, Dbox<String, Integer>> dbox3 = new Dbox<>();
        dbox3.set(dBox1, dBox2);
        out.println(dbox3);
    }
}
