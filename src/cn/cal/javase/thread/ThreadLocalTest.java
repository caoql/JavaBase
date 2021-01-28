package cn.cal.javase.thread;

/**
 * 描述： 1. ThreadLocal:每个线程自身的存储本地、局部区域.get/set/initialValue
 * 2.每个线程自身的数据，更改不会影响其他线程 3.ThreadLocal:分析上下文 环境 起点3.1、构造器: 哪里调用 就属于哪里
 * 找线程体3.2、run方法:本线程自身的 4. InheritableThreadLocal:继承上下文 环境的数据
 * ，拷贝一份给子线程，原来线程的值并不会随子线称变化
 * 
 * @author 曹启龙
 * @date 2019-03-15 14:37
 */
public class ThreadLocalTest {
	private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 100);

	private static ThreadLocal<Integer> threadLocal2 = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 1;
		}
	};

	private static ThreadLocal<Integer> threadLocal3 = ThreadLocal.withInitial(() -> 1);

	private static ThreadLocal<Integer> threadLocal4 = new InheritableThreadLocal<>();// new ThreadLocal<Integer>();

	public static void main(String[] args) {
		// test1();

		// test2();

		// test3();

		test4();
	}

	public static void test1() {
		// 获取值
		System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
		// 设置值
		threadLocal.set(99);
		System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
		// 2个线程
		new Thread(new MyRun()).start();
		new Thread(new MyRun()).start();
	}

	static class MyRun implements Runnable {

		@Override
		public void run() {
			// 获取值
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
			// 设置值
			threadLocal.set((int) (Math.random() * 99));
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
		}

	}

	public static void test2() {
		for (int i = 0; i < 5; i++) {
			new Thread(new MyRun2()).start();
		}
	}

	static class MyRun2 implements Runnable {

		@Override
		public void run() {
			Integer left = threadLocal2.get();
			System.out.println(Thread.currentThread().getName() + "得到了-->" + left);
			threadLocal2.set(left - 1);
			System.out.println(Thread.currentThread().getName() + "还剩下-->" + threadLocal2.get());
		}

	}

	public static void test3() {
		new Thread(new MyRun3()).start();
		new Thread(new MyRun3()).start();
	}

	static class MyRun3 implements Runnable {
		public MyRun3() {
			threadLocal3.set(-100);
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal3.get());
		}

		public void run() {
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal3.get());
		}
	}

	public static void test4() {
		threadLocal4.set(2);
		System.out.println(Thread.currentThread().getName() + "-->" + threadLocal4.get());

		// 线程由main线程开辟
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal4.get());
			threadLocal4.set(200);
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal4.get());
		}).start();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "-->" + threadLocal4.get());
	}
}
