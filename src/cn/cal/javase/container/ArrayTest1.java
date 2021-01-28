package cn.cal.javase.container;

import java.util.Arrays;

/**
 * 
 * 描述： 数组相关的测试
 * 
 * @author 曹启龙
 * @date 2019-03-18 10:25
 */
public class ArrayTest1 {

	public static void main(String[] args) {
		// 1. 一维数组初始化测试
		init();
		System.out.println("--------------------------");
		// 2.二维数组初始化测试
		init2();
		// TODO 3.数组算法之
	}

	// Java数组变量一定要初始化吗？不一定。Java数组变量是引用数据类型变量，它并不是数组对象本身，只要让数组变量指向有效的数组对象，即可使用该数组变量。对数组执行初始化，并不是对数组变量进行初始化，而是对数组对象进行初始化——也就是为该数组对象分配一块连续的内存空间，这块连续的内存空间就是数组的长度。
	private static void init() {
		// 当申明数组变量类型时，为其指定了32位引用空间，由于未赋值，所以不指向任何空间
		int[] a1;
		int a2[];
		int[] a3 = new int[3];
		int a4[] = new int[] { 1, 2, 3, 4 };
		// 这种常量的赋值方式只能在声明的时候就赋值，不能先声明，后赋值
		int[] a5 = { 5, 6, 7, 8, 9 };
		// 当创建了一个数组对象（也就是new出来的）并将其地址赋值给了变量，其中创建出来的那几个数组元素相当于引用类型变量，因此各自占用（32位的）引用空间并按其默
		// 认初始化规则被赋值为null
		a1 = new int[1];
		a2 = new int[] { 1, 2, 3 };
		// 程序继续运行，当创建新的对象并（将其地址）赋值给各数组元素，此时堆内存就会有值了
		System.out.println("a1:" + Arrays.toString(a1));
		System.out.println("a2:" + Arrays.toString(a2));
		System.out.println("a3:" + Arrays.toString(a3));
		System.out.println("a4:" + Arrays.toString(a4));
		System.out.println("a5:" + Arrays.toString(a5));
	}

	// 二维数组的初始化-Java中多维数组的声明和初始化应从高维到低维的顺序进行。
	private static void init2() {
		// 二维数组可以看成以数组为元素的数组
		int[][] a1;
		int a2[][];
		int[] a3[] = null;
		int[][] a4 = new int[2][3] ;
		int a5[][] = new int[2][];
		int [] a6[] = new int[][] {};
		int [][] a7 = new int[][] {{1,2},{3}};
		int [][] a8 = {{1,2,3},{4,5},{6}};
		a1 = new int[][] {};
		a2 = new int[][] {{1,2,3},{4,5},{6}};
		System.out.println("a1:" + Arrays.toString(a1));
		System.out.println("a2:" + Arrays.toString(a2));
		System.out.println("a3:" + Arrays.toString(a3));
		System.out.println("a4:" + Arrays.toString(a4));
		System.out.println("a5:" + Arrays.toString(a5));
		System.out.println("a6:" + Arrays.toString(a6));
		System.out.println("a7:" + Arrays.toString(a7));
		System.out.println("a8:" + Arrays.toString(a8));
	}

}
