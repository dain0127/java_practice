/*
 * Chapter07 class and instance
 * 7-1 class의 정의와 instance의 생성
 * 7-2 constructor와 String class
 * 7-3 java의 Naming Rule
 * 
 * 7-1 class의 정의와 instance의 생성
 * 마찬가지로 C++과 많이 유사하다.
 * class라는건 메모리에 실체화된 instance를 만들기 위한 '틀'이며,
 * filed(맴버변수)와 methode(멤버함수)로 이루어져있다.
 * 
 * 7-1-1 java와 C++의 객체 생성 방식의 차이
 * C++와 조금 다른 점은, java는 class의 tpye이 반드시 참조형(reference)이다. (heap)
 * 즉, C++에서는 ClassName* obj = new ClassName() 이런 방식으로 주소값을 저장했다면은
 * java에서는 ClassName obj = new ClassName()으로 해도 참조형으로 인식된다.
 * 
 * C++에서는 object는 stack(value)와 heap(refernce)에 둘 다 저장하는 방식이 존재한다.
 * 배열또한 stack으로 저장하는 방식과, heap으로 저장하는 방식이 다르다.
 * 
 * 하지만 java에서는 오로지 'heap' 영역에만 객체를 저장하며, '객체 배열' 또한 하나의 '독립된 class'로 본다.
 * (primitive data type같은 경우에는 stack에 저장된다.)
 * 
 * ++ 아래 코드를 보면 알겠지만, C++ delete라는 키워드가 없다.
 * 이는 java의 Garbage Collection을 이해하면, 해당 객체의 소멸방식을 이해할 수 있다.
 * 
 * 7-1-2 java에서 객체의 call-by-refernce
 * 참조변수는 주소값을 저장하고 있기 때문에, method의 매개변수 또한 참조값으로 전달이 가능하다.
 * 다시 말하자면, call-by-refernce가 가능하다.
 * 
 * 7-1-3 null
 * java는 reference에 null을 대입할 수 있다.
 * 다른 객체와의 연결을 끊고 싶을때 사용할 수 있다.
 */


class BankAccount{
    int balance = 0;

    public int deposit(int money){
        balance+=money;
        return balance;
    }

    public int withdraw(int money){
        balance-=money;
        return balance;
    }

    public void showBalance(){
        System.out.println("balance : " + balance);
    }

    public int getBalance(){
        return balance;
    }
}

class test{
    public static void main(String[] args){
        //7-1-1 java와 C++의 객체 생성 방식의 차이
        System.out.println("===========7-1-1 class and instance============");
        BankAccount acc1; //stack에 value로서 저장된게 아니라, '참조형'변수가 선언된 것이다!
        acc1 = new BankAccount(); //반드시 heap영역에 객체 생성

        BankAccount acc2 = new BankAccount();
        BankAccount acc3 = acc2;

        acc1.deposit(100);
        acc2.deposit(10000);
        acc1.showBalance();
        acc2.showBalance();

        acc3.withdraw(100);
        acc2.showBalance();

        System.out.println("============7-1-2 call-by-reference=================");
        System.out.println("acc1 pay tax.");
        //7-1-2 java에서 객체의 call-by-refernce
        payTax(acc1);   
        acc1.showBalance();

        System.out.println("============7-1-3 null================");
        acc3 = null;
        if (acc3 == null)
            System.out.println("acc3 is null!");
    }

    public static void payTax(BankAccount acc){
        int balance = acc.getBalance();
        acc.withdraw(balance/100);
    }
}