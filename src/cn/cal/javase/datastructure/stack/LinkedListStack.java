package cn.cal.javase.datastructure.stack;

import cn.cal.javase.datastructure.linked.LinkedList;

/**
 * 描述：基于链表自己实现的栈
 *
 * @author 曹启龙
 * @date 2019-03-27 15:33
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedListStack() {
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

    // 压栈
    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    // 出栈
    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }
}
