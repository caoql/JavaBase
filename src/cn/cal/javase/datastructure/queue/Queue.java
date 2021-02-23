package cn.cal.javase.datastructure.queue;

/**
 * 描述：队列接口
 *
 * @author 曹启龙
 * @date 2019-03-26 17:33
 */
public interface Queue<E> {
    // 获取队列的长度
    int getSize();

    // 判断队列是否为空
    boolean isEmpty();

    // 进队
    void enqueue(E e);

    // 出队
    E dequeue();

    // 获得队首元素
    E getFront();
}
