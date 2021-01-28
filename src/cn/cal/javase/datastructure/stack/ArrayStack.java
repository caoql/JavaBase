package cn.cal.javase.datastructure.stack;

import cn.cal.javase.datastructure.array.DynamicArray;

/**
 * 
 * 描述：自己实现的的栈，基于动态数组的实现,用组合模式
 * 
 * @author 曹启龙
 * @date 2019-03-26 16:54
 */
public class ArrayStack<E> implements Stack<E> {

	private DynamicArray<E> array;

	public ArrayStack(int capacity) {
		array = new DynamicArray<>(capacity);
	}

	public ArrayStack() {
		array = new DynamicArray<>();
	}

	// 动态数组栈特有的方法
	public int getCapacity() {
		return array.getCapacity();
	}

	@Override
	public int getSize() {
		return array.getSize();
	}

	@Override
	public boolean isEmpty() {
		return array.isEmpty();
	}

	// 在动态数组的末尾加入元素
	@Override
	public void push(E e) {
		array.addLast(e);
	}

	// 删除动态数组的末尾元素
	@Override
	public E pop() {
		return array.removeLast();
	}

	// 获取动态数组的末尾元素
	@Override
	public E peek() {
		return array.getLast();
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("Stack: ");
		res.append('[');
		for (int i = 0; i < array.getSize(); i++) {
			res.append(array.get(i));
			if (i != array.getSize() - 1)
				res.append(", ");
		}
		res.append("] top");
		return res.toString();
	}
}
