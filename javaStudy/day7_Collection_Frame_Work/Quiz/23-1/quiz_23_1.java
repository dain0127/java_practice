import static java.lang.System.out;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Person{
    private String name;
    private int age;

    public Person(String n, int a){
        this.name = n;
        this.age = a;
    }

    @Override
    public String toString() {
        return name+":"+age;
    }

    @Override
    public int hashCode() {
        return (name.hashCode() + age)%10;
    }

    @Override
    public boolean equals(Object obj) {
        Person p = (Person)obj;
        if(p.name.equals(this.name) && p.age == this.age)
            return true;
        else
            return false;
    }
}


class Person2{
    private String name;
    private int age;

    public Person2(String n, int a){
        this.name = n;
        this.age = a;
    }

    @Override
    public String toString() {
        return name+":"+age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public boolean equals(Object obj) {
        Person2 p = (Person2)obj;
        if(p.name.equals(this.name) && p.age == this.age)
            return true;
        else
            return false;
    }
}


public class quiz_23_1 {
    public static void main(String[] args) {
        Set<Person> set1 = new HashSet<>();
        set1.add(new Person("changin", 25));
        set1.add(new Person("hello", 2));
        set1.add(new Person("hello", 19));
        set1.add(new Person("changin", 25));

        for(Person e : set1)
            out.print(e + "\n");
        out.println();


        Set<Person2> set2 = new HashSet<>();
        set2.add(new Person2("changin", 25));
        set2.add(new Person2("hello", 2));
        set2.add(new Person2("hello", 19));
        set2.add(new Person2("changin", 25));

        for(Person2 e : set2)
            out.print(e + "\n");
        out.println();
    }    
}
