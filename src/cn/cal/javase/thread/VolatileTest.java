package cn.cal.javase.thread;

/**
 * 描述： volatile关键字学习【现在实际不多用了，因为机器的性能提升了很多】
 * 1.用于保证数据的同步，也就是可见性
 * 2.不能保证数据操作的原子性
 * 3.可以保证指令不重拍
 * @author 曹启龙
 * @date 2019-03-14 21:34
 */
public class VolatileTest {
	//private static int num = 0;
	private volatile static int num = 0;
	public static void main(String[] args)throws InterruptedException {
		new Thread(()->{
			while(num==0) { //此处不要编写代码，让CPU忙的停不下来
				
			}
		}) .start();
		
		Thread.sleep(1000);
		num = 1;
	}

}
