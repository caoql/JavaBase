package cn.cal.javase.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述： CAS:比较并交换
 * 
 * @author 曹启龙
 * @date 2019-03-15 15:12
 */
public class CasTest {

	// 模拟库存
	private static AtomicInteger stock = new AtomicInteger(5);

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				// 模拟网络延时
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Integer left = stock.decrementAndGet();
				if (left < 0) {
					System.out.println("抢完了...");
					return;
				}
				System.out.println(Thread.currentThread().getName() + "抢了一件商品"+"-->还剩" + left);
			}).start();
		}
	}

}
