
class BankAccount{
    String name;
    int balance = 0;

    BankAccount(String n, int m){
        name = n;
        balance = m;
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