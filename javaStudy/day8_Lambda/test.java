/*
 * Chapter28 메소드 참조와 Optional 클래스
 * 
 * 28-1 메소드 참조
 * 28-2 Optional 클래스
 *
 * 
 * 
 * 
 * 28-2 Optional 클래스
 * Optional 클래스는 null pointer를 체크하기위해, 예외처리. 내지는 if-else문을 이용한 프로그램의 흐름을 분기할때
 * 보다 간단한 코드로, null pointer에 대한 조건 분기를 가능하게 해준다.
 * 
 * 원리는 간단하다. Optional 클래스는 일종의 Wrapper class이며.
 * Optional의 몇몇 메소드에, 참조변수의 null pointer 여부에 따라 반환값이 달라지게 하면 된다.
 * 
 * 자세한건 코드를 참고.
 */

import static java.lang.System.out;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

class Friend{
    String name;
    Company com; //null 가능. 백수 친구.

    Friend(String n, Company c){
        this.name = n;
        this.com = c;
    }

    public String getName() {return name;}
    public Company getCompany() {return com;}
}

class Company{
    String cName;
    ConInfo cInfo; //null 가능. 직무 배정 이전 친구.

    Company(String cN, ConInfo cI){
        this.cName = cN;
        this.cInfo = cI;
    }

    public String getCompanyName(){return this.cName;}
    public ConInfo getCompanyInfo(){return this.cInfo;}
}

class ConInfo{
    String phone;
    String address;

    ConInfo(String pN, String addr){
        this.phone = pN;
        this.address = addr;
    }

    public String getPhoneNumber(){return this.phone;}
    public String getAddress(){return this.address;}
}


class test{
    public static void main(String[] args) {
        ConInfo i1 = new ConInfo("010-1234-4321", "Korea");
        Company c1 = new Company("LG", i1);
        Friend f1 = new Friend("changin", c1);

        //static 메소드의 정의를 보고, if-else문이 매우 많아 복잡하다는 것을 인지하라.
        showFriendCompanyInfo(f1);

        /* 1. Optional 메소드의 소개 */
        //개념적으로 Optional 클래스는 래퍼 클래스이다.
        //다만. null pointer check가 간편한 래퍼 클래스이다.
        //그래서 기본적으로 상자이다. 그 상자가 유용한 기능을 가지고 있을 뿐이다.
        Optional<String> o1 = Optional.of("hello");
        Optional<String> o1Null = Optional.ofNullable(null); //빈 상자 전달
        Optional<String> o2 = Optional.ofNullable("bye");
        Optional<String> o2Null = Optional.empty(); //빈 상자 전달

        if(o2.isPresent())
            out.println("o2 content is present!");
        else
            out.println("there is no o2 content");

        if(o2Null.isPresent())
            out.println("o2 content is present!");
        else
            out.println("there is no o2 content");

        
        o1.ifPresent(s->out.println("o1 content is " + s + " from ifPresent"));
        out.println("o2 content is " + o2.get() + " from get()");


        out.println("=============map()=============");


        Optional<Integer> intO1 = o1.map(s -> s.length());
        intO1.ifPresent(n -> out.println("intO1 content is " + n));
        
        
        out.println("=============orElse()=============");


        String result1 = o1.map(s -> s.toString()).orElse("Empty");
        out.println("result1 : "+ result1);
        String result2 = o1Null.map(s -> s.toString()).orElse("Empty");
        out.println("result2 : "+ result2);
        out.println();

        ConInfo con1 = new ConInfo("010-2323-3232", null);
        Optional<ConInfo> oCon1 = Optional.of(con1);
        
        String phone1 = oCon1.map(o -> o.getPhoneNumber()).orElse("there is no phone number");
        String addr1 = oCon1.map(o -> o.getAddress()).orElse("there is no address");

        out.println("phone 1 : " + phone1);
        out.println("addr 1 : " + addr1);


        out.println("=============flatMap()=============");

        Optional<String> strTempO1 = Optional.of("test");

        //Optional<T> :: ☆☆☆Optional<T>☆☆☆ map(Function<? super T, ? extends T>)
        //결과를 Optional<T>의 인스턴스로 packaging을 한채로 반환한다.     
        Optional<String> strO1 = strTempO1.map(s -> s.toUpperCase());
        strO1.ifPresent(out::println);
        
        //Optional<T> :: ☆☆☆T☆☆☆ flatMap(Function<? super T, ? extends T>)
        //결과 자체를 내보내기 때문에, 람다식 자체를, 반환하려는 결과값이 Optional<String>의 인스턴스가 되어야한다.
        //flatMap의 시그니처를 보면 알겠지만, 반환형이 반드시 Optinal의 인스턴스이다.
        Optional<String> strO2 = strTempO1.flatMap(s -> Optional.of(s.toUpperCase())); 
        strO2.ifPresent(out::println);

        /* 2. Optional 메소드를 활용한 null poionter 여부에 따른 손쉬운 조건 분기. */
        out.println("/* 2. Optional 메소드를 활용한 null poionter 여부에 따른 손쉬운 조건 분기. */");
        ConInfo i2 = new ConInfo("010-1234-4321", "Korea");
        Company c2 = new Company("LG", i2);
        Friend f2 = new Friend("changin", c2);

        //두 함수의 정의를 비교하라.
        //그리고 무엇이 가독성이 좋은지. 이어서 코드를 짜기 좋은지. 비교하라.
        showFriendCompanyInfo(f2);
        showFriendCompanyInfoOptional(f2);

        /* 3. OptionalInt OptionalDouble ... 기타 등등 기초 자료형 전용 클래스 */
        //그냥 래퍼클래스로 생기는 오버헤드로 성능 저하되는거 개선 하는 용도

    }

    static void showFriendCompanyInfo(Friend f){
        String addr = null;

        if(f.getCompany() != null){
            Company c = f.getCompany();
            if(c.getCompanyInfo() != null){
                ConInfo i = c.getCompanyInfo();
                if(i.getAddress() != null){
                    addr = i.getAddress();
                }
            }
        }


        if(addr != null)
            out.println("address : " + addr + " by if-else");            
        else
            out.println("no address" + " by if-else");
    }

    static void showFriendCompanyInfoOptional(Friend fArg){
        Optional<Friend> o = Optional.of(fArg);

        String addr =
        o.map(f->f.getCompany()).map(c->c.getCompanyInfo()).map(i->i.getAddress()).orElse("there is no address");

        out.println(addr + " by optional");
    }
}