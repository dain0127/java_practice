/*
 * Chapter07 class and instance
 * 7-1 class의 정의와 instance의 생성
 * 7-2 Constructor와 String class
 * 7-3 java의 Naming Rule
 * 
 * 7-2-1 String.
 * String또한 class이다. C++에도 있지 않는가.
 * 각종 연산자 오버로딩, 유용한 메소드 등등. 다 구현되어있다. 끝.
 * 
 * 7-2-2 Constructor(생성자) 
 * 얘 또한 C++하고 똑같다.
 * 디폴트 생성자의 개념 또한 똑같다. 끝.
 */

class BankAccount{
    String name;
    int balance = 0;

    BankAccount(String n, int m){
        name = n;
        balance = m;
        System.out.println("call constructor!");
    }

    public int deposit(int money){
        balance+=money;
        return balance;
    }

    public int withdraw(int money){
        balance-=money;
        return balance;
    }

    public void showInfo(){
        System.out.println("name : " + name);
        System.out.println("balance : " + balance);
    }

    public int getBalance(){
        return balance;
    }

    public String getName(){
        return name;
    }
}

class test{
    public static void main(String args[]){
        BankAccount acc1 = new BankAccount("changin", 10000);
        acc1.showInfo();
    }
}