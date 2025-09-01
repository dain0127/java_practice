#include <iostream>

using namespace std;


/*
 * java에서 extends로 타입 인자값을 제한함으로서, 오히려 호출 할 수 있는 메소드가 많아진다.
 * 하지만 불행히도 C++에는 그런 기능이 없다. 일단 허용하고, 템플릿 클래스 코드를 만들고 컴파일 가능을 판단한다.
 * 
 * (물론 연산자 오버로딩으로 다형성을 보장하면 모르겠지만, 일단은 특정 class에만 있는 method만 호출하도록 하지는 않고,)
 * (타입 인자값을 받으면, 그제서야 컴파일러가 템플릿을 따서 타입에 맞게 코드를 재생산한 다음, 컴파일 여부를 따진다.)
 * 
 * (C++과 java가 다른 방식으로 프로그램의 흐름과 다른 코드를, 컴파일러가 발견하도록 하고 있다는 점에 주목하라.)
 * 
 * 
 */

class Integer{
private:
    int num;

public:
    Integer(int n = 0){
        this->num = n;
    }

    int get(){
        return num;
    }

    void set(int n){
        this->num = n;
    }
};

class NonNegtiveInteger : public Integer{
public:
    NonNegtiveInteger(int n = 0):Integer(n){
        if(n < 0)
            Integer::set(-n);
    }

    int get(){
        return Integer::get();
    }

    void set(int n){
        Integer::set(-n);
    }

    void onlyNonNegtiveCallFun(){
        cout<<"onlyNonNegtiveCallFun()"<<endl;
    }
};

//C++에서는 <typename T extends NonNegtiveInteger>가 없어서 아쉬울 따름이다.
template <typename T>
void showNumber(T ob){
    cout<<ob.onlyNonNegtiveCallFun()<<endl;
}

int main(){
    Integer obI = Integer(10);
    showNumber<Integer>(obI);

    return 0;
}