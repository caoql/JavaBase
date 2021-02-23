package cn.cal.javase.datastructure.queue;

/**
 * 描述：队列测试
 *
 * @author 曹启龙
 * @date 2019-03-26 17:32
 */
public class Main {

    public static void main(String[] args) {
        Queue<String> queue = new ArrayLoopQueue<>(5);
        queue.enqueue("java");
        queue.enqueue("python");
        queue.enqueue("js");
        queue.enqueue("go");
        System.out.println(queue);
        // 缩容测试
        System.out.println(queue.dequeue());
        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue);
        System.out.println("------------------------------------");
        // 扩容测试
        queue.enqueue("jedis");
        System.out.println(queue);
        queue.enqueue("mysql1");
        System.out.println(queue);
        queue.enqueue("mysql2");
        System.out.println(queue);
        queue.enqueue("mysql3");
        System.out.println(queue);
        System.out.println("------------------------------------");
        System.out.println(queue.getFront());

        System.out.println("-------------------");

        LinkedListQueue<Integer> queue2 = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue2.enqueue(i);
            System.out.println(queue2);
            if (i % 3 == 2) {
                System.out.println("dequeue: " + queue2.dequeue());
                System.out.println(queue2);
            }
        }

    }

}
