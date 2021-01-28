package cn.cal.javase.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 
 * 描述： 线程创建的三种方式学习
 * 
 * @author 曹启龙
 * @date 2019-03-15 16:25
 */
public class ThreadTest1 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println(Thread.currentThread().getName() + ":这是主线程");
		new MyThread().start();
		// 2.通过实现runnable接口来创建的线程
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + ":这是通过实现runnable接口来创建的线程");
		}).start();
		// 3.2）创建Callable实现类的实例，使用FutureTask类来包装Callable对象，该FutureTask对象封装了该Callable对象的call()方法的返回值。（FutureTask是一个包装器，它通过接受Callable来创建，它同时实现了Future和Runnable接口。）
		MyCallable mc = new MyCallable();
		FutureTask<String> ft = new FutureTask<>(mc);
		// 3.3）使用FutureTask对象作为Thread对象的target创建并启动新线程。
		new Thread(ft, "有返回值的线程").start();
		// 3.4）调用FutureTask对象的get()方法来获得子线程执行结束后的返回值
		System.out.println("子线程的返回值：" + ft.get());
	}

	// 1.通过继承Thread类来创建的线程
	static class MyThread extends Thread {

		@Override
		public void run() {
			System.out.println(getName() + ":这是通过继承Thread类来创建的线程");
		}

	}

	// 3.通过Callable和Future创建线程,有返回值，也能抛出异常
	static class MyCallable implements Callable<String> {

		// 3.1）创建Callable接口的实现类，并实现call()方法，该call()方法将作为线程执行体，并且有返回值。
		@Override
		public String call() throws Exception {
			System.out.println(Thread.currentThread().getName() + ":这是通过实现Callable接口来创建的线程");
			return "Callable";
		}

	}

}
