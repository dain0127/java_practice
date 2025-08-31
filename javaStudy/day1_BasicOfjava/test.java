class test{
    public static void main(String args[]){
        for(int i=0;i<=9;i++){
            for(int j=0;j<=9;j++){
                if((10*i + j) + (10*j + i) == 99)
                    System.out.println("i : "+i+","+"j : "+j);
            }
        }
    }
}
