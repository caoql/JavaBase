package cn.cal.javase.datastructure.queue;

import java.util.Random;

/**
 * 
 * 描述：队列的性能比较
 * 
 * @author 曹启龙
 * @date 2019-03-27 15:51
 */
public class QueueCompare {

	public static void main(String[] args) {
		int opCount = 100000;
		ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
		double time1 = testQueue(arrayQueue, opCount);
		System.out.println("ArrayQueue, time: " + time1 + " s");

		ArrayLoopQueue<Integer> loopQueue = new ArrayLoopQueue<>();
		double time2 = testQueue(loopQueue, opCount);
		System.out.println("LoopQueue, time: " + time2 + " s");

		LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
		double time3 = testQueue(linkedListQueue, opCount);
		System.out.println("LinkedListQueue, time: " + time3 + " s");
	}

	// 测试使用q运行opCount个enqueueu和dequeue操作所需要的时间，单位：秒
	private static double testQueue(Queue<Integer> q, int opCount) {
		long startTime = System.nanoTime();
		Random random = new Random();
		for (int i = 0; i < opCount; i++)
			q.enqueue(random.nextInt(Integer.MAX_VALUE));
		for (int i = 0; i < opCount; i++)
			q.dequeue();
		long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000000.0;
	}

}
