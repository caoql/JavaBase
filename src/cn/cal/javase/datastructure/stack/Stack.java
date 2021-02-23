package cn.cal.javase.datastructure.stack;

/**
 * 描述：栈接口
 *
 * @author 曹启龙
 * @date 2019-03-26 16:59
 */
public interface Stack<E> {
    // 获取栈中元素个数
    int getSize();

    // 判断栈是否为空
    boolean isEmpty();

    // 压栈
    void push(E e);

    // 出栈
    E pop();

    // 获取栈顶元素
    E peek();
}
