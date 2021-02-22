package cn.cal.javase.core;

public class StringTest {
    public static void main(String[] args) {
        // 字符串相等判断
        String str1 = "abc";
        String str2 = new String("abc");
        String str3 = str2.intern();
        System.out.println((str1 == str2));
        System.out.println((str2 == str3));
        System.out.println((str1 == str3));


        String a = new String("abc").intern();
        String b = new String("abc").intern();

        if (a == b) {
            System.out.println("a==b");
        }

    }
}
