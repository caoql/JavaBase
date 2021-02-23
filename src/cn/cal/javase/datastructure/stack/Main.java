package cn.cal.javase.datastructure.stack;

/**
 * 描述：栈的测试
 *
 * @author 曹启龙
 * @date 2019-03-26 16:54
 */
public class Main {

    public static void main(String[] args) {
        // 顺序栈
        Stack<String> stack = new ArrayStack<>();
        stack.push("java");
        stack.push("python");
        stack.push("js");
        stack.push("go");
        System.out.println(stack);
        System.out.println(stack.getSize());
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);

        System.out.println("--------");
        // 链式栈
        LinkedListStack<Integer> stack2 = new LinkedListStack<>();
        for (int i = 0; i < 5; i++) {
            stack2.push(i);
            System.out.println(stack2);
        }
        System.out.println(stack2.pop());
        System.out.println(stack2);
    }


}
