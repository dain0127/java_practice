import java.util.Arrays;

class ToyInfo{
    private String model; //모델명
    private int price; //가격

    public ToyInfo(String m, int p){
        this.model = m;
        this.price = p;
    }

    public int getPrice(){return this.price;}
    public String getModel(){return this.model;}
}

public class Quiz_29_2{
    public static void main(String[] args) {
        ToyInfo[] arr = {new ToyInfo("AAA", 200)
                        ,new ToyInfo("BBBBBBBBBBBBBBBBBBB", 350)
                        ,new ToyInfo("CCCCCCCCCCCCCCCCCCC", 550)};


        Arrays.stream(arr)
        .filter(i -> i.getModel().length() > 10)
        .map(i -> i.getModel())
        .forEach(System.out::println);
    }
}