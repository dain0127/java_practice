class test{
    static int count = 0;

    public static void main(String args[]){
        counter(10);
        System.out.println(count);
    }

    public static void counter(int n){
        count+=n;
    }
} 