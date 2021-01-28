package cn.cal.javase.jvm;


import java.util.ArrayList;
import java.util.List;

class Person {

}

public class OOMTest {
    public static void main(String[] args) {
        System.out.println("OOM test");
        List list = new ArrayList();
        while (true) {
            list.add(new Person());
        }
    }

}
