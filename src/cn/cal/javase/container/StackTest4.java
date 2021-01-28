package cn.cal.javase.container;

import java.util.Stack;

/**
 * 
 * 描述： 堆栈 LIFO，这个是JDK设计不好的地方，因该用组合来封装栈的行为而不是继承
 * 1.底层数据结构： 动态数组，,直接继承Vector，是对Vector的封装
 * 2.性能如何： 比ArrayList差点儿，因为有同步保证线程安全 
 * 3.线程安全不安全：同步的，线程安全 
 * 4.常用API: 
 * 5.应用场景：
 * 
 * @author 曹启龙
 * @date 2019-03-21 14:47
 */
public class StackTest4 {

	public static void main(String[] args) {
		/** 初始化 */
		Stack<String> stack = new Stack<>();
		
		/** empty */
		System.out.println("Stack的元素：" + stack);
		System.out.println("empty: " + stack.empty());
		
		/** push压栈 */
		stack.push("oracle");
		stack.push("mysql");
		stack.push("redis");
		
		/** toString */
		System.out.println("Stack的元素：" + stack);
		System.out.println("empty: " + stack.empty());
		
		/** peek元素不删除 */
		System.out.println("peek: " + stack.peek());
		System.out.println("Stack的元素：" + stack);
		
		/** pop出栈 */
		System.out.println("pop: " + stack.pop());
		System.out.println("Stack的元素：" + stack);
	}

}
