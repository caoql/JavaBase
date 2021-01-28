package cn.cal.javase.core;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 
 * 描述： 1.java内存管理分为内存分配和内存回收，都不需要程序员负责，垃圾回收的机制主要是看对象是否有引用指向该对象。 2.java对象的引用包括
 * 强引用，软引用，弱引用，虚引用。3.Java中提供这四种引用类型主要有两个目的： 第一是可以让程序员通过代码的方式决定某些对象的生命周期；
 * 第二是有利于JVM进行垃圾回收。
 * 
 * @author 曹启龙
 * @date 2019-03-22 10:35
 */
public class ReferenceTest {

	public static void main(String[] args) {
		// testStrong();

		// testSoft();

		testWeak();
	}

	// 1.测试强引用
	// 强引用:是指创建一个对象并把这个对象赋给一个引用变量。
	@SuppressWarnings("unused")
	public static void testStrong() {
		System.out.println("---强引用测试---");
		// 强引用有引用变量指向时永远不会被垃圾回收，JVM宁愿抛出OutOfMemory错误也不会回收这种对象。
		Object object = new Object();
		// 当运行至Object[] objArr = new
		// Object[1000];这句时，如果内存不足，JVM会抛出OOM错误也不会回收object指向的对象。不过要注意的是，当testStrongReference方法运行完之后，object和objArr都已经不存在了，所以它们指向的对象都会被JVM回收。
		// Object[1000_0000_00]:Exception in thread "main" java.lang.OutOfMemoryError:
		// Java heap space
		Object[] objArr = new Object[1000];
		// 如果想中断强引用和某个对象之间的关联，可以显示地将引用赋值为null，这样一来的话，JVM在合适的时间就会回收该对象。
		// 比如Vector类的clear方法中就是通过将引用赋值为null来实现清理工作的
		object = null;
		// 通知系统执行gc
		System.gc();
	}

	// 2.软引用测试
	public static void testSoft() {
		System.out.println("---软引用测试---");
		Object object = new Object();
		System.out.println("强引用对象是:" + object);
		SoftReference<Object> softRef = new SoftReference<>(object);
		System.out.println("强引用和放入软引用中取出的对象判断是否相等：" + (object == softRef.get()));
		object = null;
		System.out.println("当强引用设为null之后,放入软引用中对象的值是：" + (softRef.get()));
		System.gc();
		// 给垃圾回收一点时间
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("当强引用设为null之后,然后调用系统的垃圾gc()5秒后，放入软引用中对象的值是：" + (softRef.get()));
		// 清除引用
		softRef.clear();
		System.out.println("当softRef.clear()，放入软引用中对象的值是：" + (softRef.get()));

		System.out.println("引用队列:");
		// 引用队列
		Object myObj = new Object();
		ReferenceQueue queue = new ReferenceQueue();
		SoftReference ref = new SoftReference(myObj, queue);
		System.out.println(myObj == ref.get());
		SoftReference ref2 = null;
		while ((ref2 = (SoftReference) queue.poll()) != null) {
			System.out.println(ref2);
			// 清除ref
			ref2 = null;
		}
	}

	// 3.弱引用测试
	// 弱引用也是用来描述非必需对象的，当JVM进行垃圾回收时，无论内存是否充足，都会回收被弱引用关联的对象
	@SuppressWarnings("unchecked")
	public static void testWeak() {
		System.out.println("---弱引用测试---");
		Object object = new Object();
		System.out.println("强引用对象是:" + object);
		WeakReference<Object> weakR = new WeakReference<>(object);

		System.out.println("强引用和放入弱引用中取出的对象判断是否相等：" + (object == weakR.get()));
		// 通知JVM回收资源
		object = null;
		System.out.println("当强引用设为null之后,放入弱引用中对象的值是：" + (weakR.get()));
		System.gc();
		// 只要JVM进行垃圾回收，被弱引用关联的对象必定会被回收掉。不过要注意的是，这里所说的被弱引用关联的对象是指只有弱引用与之关联，如果存在强引用同时与之关联，则进行垃圾回收时也不会回收该对象（软引用也是如此）。
		System.out.println("调用系统的垃圾gc()后，放入弱引用中对象的值是：" + weakR.get());
		// 弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被JVM回收，这个软引用就会被加入到与之关联的引用队列中。
		// 引用队列
		System.out.println("引用队列:");
		Object object2 = new Object();
		ReferenceQueue<Object> queue = new ReferenceQueue<>();
		WeakReference<Object> weakR2 = new WeakReference<>(object2, queue);
		System.out.println(object2 == weakR2.get());
		System.out.println("软引用: " + weakR2);
		// 弱引用所引用的对象被JVM回收
		object2 = null;
		System.gc();
		// 模拟一点点时间延迟
		System.out.println();
		WeakReference<Object> weakR3 = null;
		while ((weakR3 = (WeakReference<Object>) queue.poll()) != null) {
			System.out.println("队列中的软引用：" + weakR3);
			// 清除ref
			weakR3 = null;
		}
	}
}
