import static java.lang.System.out;

class Accumulator{
    private static int sum;
    static{
        sum = 0;
    }

    public static void add (int n){
        sum += n;
    }

    public static void showResult(){
        out.println("sum = " + sum);
    }
}

class Quiz {
    public static void main(String args[]){
        for(int i=0;i<10;i++)
            Accumulator.add(i);
        Accumulator.showResult();
    }    
}
