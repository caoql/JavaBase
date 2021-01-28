package cn.cal.javase.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 描述： 参考博客【别人的】：https://www.cnblogs.com/0201zcr/p/4703061.html
 * 
 * @author 曹启龙
 * @date 2019-03-15 14:07
 */
public class TimerTest {

	public static void main(String[] args) {
		Timer timer = new Timer();
		// 立即执行
		timer.schedule(new MyTask(), 0);
		// 5秒后执行
		timer.schedule(new MyTask(), 5000);
		// 过5秒后，每两秒执行一下
		timer.schedule(new MyTask(), 5000, 2000);
		// 立即执行,可以指定一个具体的时间
		timer.schedule(new MyTask(), new Date());
		// 过5秒后，每两秒执行一下
		timer.schedule(new MyTask(), new Date(new Date().getTime() + 5000), 2000);
		// 过5秒后，每两秒执行一下
		// 调度一个task，在delay(ms)后开始调度，然后每经过period(ms)再次调度，貌似和方法：schedule是一样的，其实不然，后面你会根据源码看到，schedule在计算下一次执行的时间的时候，是通过当前时间（在任务执行前得到）
		// + 时间片，而scheduleAtFixedRate方法是通过当前需要执行的时间（也就是计算出现在应该执行的时间）+
		// 时间片，前者是运行的实际时间，而后者是理论时间点，例如：schedule时间片是5s，那么理论上会在5、10、15、20这些时间片被调度，但是如果由于某些CPU征用导致未被调度，假如等到第8s才被第一次调度，那么schedule方法计算出来的下一次时间应该是第13s而不是第10s，这样有可能下次就越到20s后而被少调度一次或多次，而scheduleAtFixedRate方法就是每次理论计算出下一次需要调度的时间用以排序，若第8s被调度，那么计算出应该是第10s，所以它距离当前时间是2s，那么再调度队列排序中，会被优先调度，那么就尽量减少漏掉调度的情况。
		timer.scheduleAtFixedRate(new MyTask(), new Date(new Date().getTime() + 5000), 2000);
		// 每天定时执行需要变通自己实现，注意控制起始执行时间就好了
	}

}

// 任务类
class MyTask extends TimerTask {

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("放空大脑休息一会");
		}
		System.out.println("------end-------");
	}

}
