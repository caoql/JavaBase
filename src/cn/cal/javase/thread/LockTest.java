package cn.cal.javase.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 描述： 1.可重入锁: 锁可以延续使用 2.不可重入锁: 锁不可以延续使用 3.可重入锁: 锁可以延续使用 + 计数器 4.可重入锁: 锁可以延续使用 +
 * 计数器
 * 
 * @author 曹启龙
 * @date 2019-03-15 15:56
 */
public class LockTest {

	public static void main(String[] args) throws InterruptedException {
		// new LockTest01().test();

		// test2();

		// test3();
		
		test04();
	}

	// 可重入锁
	static class LockTest01 {
		public void test() {
			// 第一次获得锁
			synchronized (this) {
				while (true) {
					// 第二次获得同样的锁
					synchronized (this) {
						System.out.println("ReentrantLock!");
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void test2() throws InterruptedException {
		LockTest02 test = new LockTest02();
		test.a();
		// test.doSomething();
	}

	// 不可重入锁
	static class LockTest02 {
		Lock lock = new Lock();

		public void a() throws InterruptedException {
			lock.lock();
			doSomething();
			lock.unlock();
		}

		// 不可重入
		public void doSomething() throws InterruptedException {
			lock.lock();
			System.out.println("do something ...");
			lock.unlock();
		}

		// 不可重入锁实现
		class Lock {
			// 是否占用
			private boolean isLocked = false;

			// 使用锁
			public synchronized void lock() throws InterruptedException {
				while (isLocked) {
					wait();
				}
				isLocked = true;
			}

			// 释放锁
			public synchronized void unlock() {
				isLocked = false;
				notify();
			}
		}

	}

	public static void test3() throws InterruptedException {
		LockTest03 test = new LockTest03();
		test.a();
		Thread.sleep(1000);
		System.out.println(test.lock.getHoldCount());
	}

	// 可重入锁
	static class LockTest03 {
		ReLock lock = new ReLock();

		public void a() throws InterruptedException {
			lock.lock();
			System.out.println(lock.getHoldCount());
			doSomething();
			lock.unlock();
			System.out.println(lock.getHoldCount());
		}

		// 可重入
		public void doSomething() throws InterruptedException {
			lock.lock();
			System.out.println(lock.getHoldCount());
			// ...................
			lock.unlock();
			System.out.println(lock.getHoldCount());
		}

		// 可重入锁
		class ReLock {
			// 是否占用
			private boolean isLocked = false;
			private Thread lockedBy = null; // 存储线程
			private int holdCount = 0;

			// 使用锁
			public synchronized void lock() throws InterruptedException {
				Thread t = Thread.currentThread();
				while (isLocked && lockedBy != t) {
					wait();
				}

				isLocked = true;
				lockedBy = t;
				holdCount++;
			}

			// 释放锁
			public synchronized void unlock() {
				if (Thread.currentThread() == lockedBy) {
					holdCount--;
					if (holdCount == 0) {
						isLocked = false;
						notify();
						lockedBy = null;
					}
				}
			}

			public int getHoldCount() {
				return holdCount;
			}
		}
	}

	public static void test04() throws InterruptedException {
		LockTest04 test = new LockTest04();
		test.a();
		Thread.sleep(1000);
		System.out.println(test.lock.getHoldCount());
	}

	// 可重入锁: 锁可以延续使用 + 计数器
	static class LockTest04 {
		ReentrantLock lock = new ReentrantLock();

		public void a() throws InterruptedException {
			lock.lock();
			System.out.println(lock.getHoldCount());
			doSomething();
			lock.unlock();
			System.out.println(lock.getHoldCount());
		}

		// 不可重入
		public void doSomething() throws InterruptedException {
			lock.lock();
			System.out.println(lock.getHoldCount());
			// ...................
			lock.unlock();
			System.out.println(lock.getHoldCount());
		}

	}
}
