package cn.cal.javase.thread;

/**
 * 
 * 描述：死锁测试 死锁产生的4个必要条件： 1.互斥 2.占有且等待 3.不可抢占 4.循环等待
 * 
 * @author 曹启龙
 * @date 2019-03-19 18:47
 */
public class DeadLockTest {

	public static void main(String[] args) {
		// 注意，定要是线程共享这2把锁
		Object lock1 = new Object();
		Object lock2 = new Object();
		// 不同线程获得的锁顺序不同
		new Thread(new MyRunnable(lock1, lock2, true)).start();
		new Thread(new MyRunnable(lock1, lock2, false)).start();
	}

	static class MyRunnable implements Runnable {
		boolean flag;
		Object lock1;
		Object lock2;

		public MyRunnable(Object lock1, Object lock2, boolean flag) {
			this.flag = flag;
			this.lock1 = lock1;
			this.lock2 = lock2;
		}

		@Override
		public void run() {
			if (flag)
				t1();
			else
				t2();
		}

		public void t1() {
			synchronized (lock1) {
				System.err.println(Thread.currentThread().getName() + "拿到锁1了，休息1秒");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.err.println(Thread.currentThread().getName() + "拿到锁1了，休息1秒之后醒过来了，准备拿琐2");
				synchronized (lock2) {
					System.err.println(Thread.currentThread().getName() + "拿到锁2了，休息2秒");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				System.err.println(Thread.currentThread().getName() + "休息2秒后释放锁2");
			}
		}

		public void t2() {
			synchronized (lock2) {
				System.err.println(Thread.currentThread().getName() + "拿到锁2了，休息2秒");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.err.println(Thread.currentThread().getName() + "拿到锁2了，休息2秒之后醒过来了，准备拿琐1");
				synchronized (lock1) {
					System.err.println(Thread.currentThread().getName() + "拿到锁1了，休息1秒");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.err.println(Thread.currentThread().getName() + "休息1秒后释放锁1");
			}
		}

	}

}
