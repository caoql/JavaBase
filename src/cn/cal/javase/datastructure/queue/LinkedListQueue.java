package cn.cal.javase.datastructure.queue;

import cn.cal.javase.datastructure.linked.LinkedList;

/**
 * 
 * 描述：链表队列，后期优化成双向队列
 * 
 * @author 曹启龙
 * @date 2019-03-27 15:43
 */
public class LinkedListQueue<E> implements Queue<E> {

	private LinkedList<E> list;
	
	public LinkedListQueue() {
		list = new LinkedList<>();
	}
	
	@Override
	public int getSize() {
		return list.getSize();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	// 进队
	@Override
	public void enqueue(E e) {
		list.addLast(e);
	}

	// 出队
	@Override
	public E dequeue() {
		return list.removeFirst();
	}

	// 获取队首元素
	@Override
	public E getFront() {
		return list.getFirst();
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("Queue: top ");
		res.append(list);
		return res.toString();
	}

}
