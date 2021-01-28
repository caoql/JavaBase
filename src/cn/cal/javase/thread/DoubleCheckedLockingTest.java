package cn.cal.javase.thread;

/**
 * 描述： DCL单例模式: 懒汉式套路基础上加入并发控制，保证在多线程环境下，对外存在一个对象 1、构造器私有化 -->避免外部new构造器
 * 2、提供私有的静态属性 -->存储对象的地址 3、提供公共的静态方法 --> 获取属性
 * 
 * @author 曹启龙
 * @date 2019-03-14 21:51
 */
public class DoubleCheckedLockingTest {

	public static void main(String[] args) {
		// 模拟多个线程并发访问
		Thread t1 = new Thread(() -> {
			System.out.println(DoubleCheckedLocking.getInstance());
		});
		Thread t2 = new Thread(() -> {
			System.out.println(DoubleCheckedLocking.getInstance());
		});
		Thread t3 = new Thread(() -> {
			System.out.println(DoubleCheckedLocking.getInstance());
		});
		t1.start();
		t2.start();
		t3.start();
		System.out.println(DoubleCheckedLocking.getInstance());
	}

}

class DoubleCheckedLocking {
	// 1、构造器私有化
	private DoubleCheckedLocking() {
		// 模拟创建对象的耗时操作
		/*try {
			// 100ms
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}

	// 2、提供私有的静态属性
	// 没有volatile其他线程可能访问一个没有初始化的对象,这是由于指令重拍造成的
	private static volatile DoubleCheckedLocking instance;

	// 3、提供公共的静态方法 --> 获取属性
	public static DoubleCheckedLocking getInstance() {
		// 再次检测,避免不必要的同步 ，已经存在对象
		if (null == instance) { 
			System.out.println(Thread.currentThread().getName());
			synchronized (DoubleCheckedLocking.class) {
				if (null == instance) {
					// 模拟延时
					/*try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}*/
					// 创建对象的三个步骤：1、开辟空间 //2、初始化对象信息 //3、返回对象的地址给引用
					instance = new DoubleCheckedLocking();
				}
			}
		}
		return instance;
	}
}
