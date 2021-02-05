package cn.cal.javase.jvm;

public class ByteCodeTest {
    public void testAdd() {
        byte i = 5;
        int j = 3;
        int k = i + j;
    }

    public static void a() {
        int i = 0;
        int j = i++;
        System.out.println(j);
    }

    public static void b() {
        int i = 0;
        int j = ++i;
        System.out.println(j);
    }

    public static void c() {
        int i = 0;
        i = i++;
        System.out.println(i);
    }

    public static void d() {
        int i = 0;
        i = ++i;
        System.out.println(i);
    }

    public static void main(String[] args) {
        a();

        b();

        c();

        d();

    }
}