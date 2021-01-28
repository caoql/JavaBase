package cn.cal.javase.datastructure.queue;

import cn.cal.javase.datastructure.array.DynamicArray;

/**
 * 
 * 描述：自己实现基于数组的普通队列
 * 
 * @author 曹启龙
 * @date 2019-03-26 17:35
 */
public class ArrayQueue<E> implements Queue<E> {

	private DynamicArray<E> array;

	public ArrayQueue() {
		array = new DynamicArray<>();
	}

	public ArrayQueue(int capacity) {
		array = new DynamicArray<>(capacity);
	}

	public int getCapacity() {
		return array.getCapacity();
	}

	// O(1)
	@Override
	public int getSize() {
		return array.getSize();
	}

	// O(1)
	@Override
	public boolean isEmpty() {
		return array.isEmpty();
	}

	// 进队,在动态数组的最后加入一个元素.O(1),扩容就是O(n)，均摊一下就是O(2)=>O(1)
	@Override
	public void enqueue(E e) {
		array.addLast(e);
	}

	// 出队,删除动态数组的第一个元素,O(n)复杂度，有点受不了，要改进
	@Override
	public E dequeue() {
		return array.removeFirst();
	}

	// 获取队首元素,O(1)
	@Override
	public E getFront() {
		return array.getFirst();
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Queue front：[");
		for (int i = 0; i < array.getSize(); i++) {
			sBuilder.append(array.get(i));
			if (i != array.getSize() - 1)
				sBuilder.append(" ");
		}
		sBuilder.append("] tail");
		return sBuilder.toString();
	}

}
