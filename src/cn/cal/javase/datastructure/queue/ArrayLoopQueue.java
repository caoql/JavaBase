package cn.cal.javase.datastructure.queue;

/**
 * 描述：自己基于数组实现的普通循环队列 1.另外一种实现是预留一个空位出来
 *
 * @author 曹启龙
 * @date 2019-03-26 17:58
 */
public class ArrayLoopQueue<E> implements Queue<E> {
    // 存放元素的个数
    private E[] data;
    // front指向队首，tail指向队尾
    private int front, tail;
    // 元素的个数
    private int size;

    public ArrayLoopQueue() {
        this(10);
    }

    public ArrayLoopQueue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("capacity can not <= 0" + capacity);
        // data = (E[])new Object[capacity + 1];
        data = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public int getCapacity() {
        // return data.length - 1;
        return data.length;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        // return front == tail;
        return size == 0;
    }

    // 进队,从数组尾部进
    @Override
    public void enqueue(E e) {
        // 容量校验
        // if((tail + 1) % data.length == front)
        if (size == data.length)
            resize(getCapacity() * 2);
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    // O(1)均摊，考虑到缩容，解決普通队列出队时间复杂度是O(n)的问题
    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        // 获取原来的值
        E old = data[front];
        // 原来的位置值设为null
        data[front] = null;
        // 像时钟一样循环,front指向下一个元素
        front = (front + 1) % data.length;
        // 数量减1
        size--;
        // 缩容，避免复杂度震荡
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return old;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return data[front];
    }

    // 扩容
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(String.format("ArrayLoopQueue: size=%d, capacity=%d, front=%d, tail=%d。", size, data.length,
                front, tail));
        sBuilder.append("  => front:[");
        int len = size + front;
        for (int i = front; i < len; i++) {
            int temp = i;
            if (temp >= data.length) {
                temp = i % data.length;
            }
            sBuilder.append(data[temp]);
            // 判断是否是最后一个元素
            if (temp != tail - 1) {
                sBuilder.append(" ");
            }
        }
        /*
         * for (int i = front; i != tail; i = (i + 1) % data.length) {
         * sBuilder.append(data[i]); if ((i + 1) % data.length != tail)
         * sBuilder.append(", "); }
         */
        sBuilder.append("] tail");
        return sBuilder.toString();
    }
}
