package cn.cal.javase.datastructure.linked;

/**
 * 描述：链表测试
 *
 * @author 曹启龙
 * @date 2019-03-27 11:42
 */
public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        System.out.println(linkedList.getLast());
        linkedList.add(2, 666);
        System.out.println(linkedList);
        System.out.println(linkedList.set(2, 777));
        System.out.println(linkedList);
        System.out.println(linkedList.removeFirst());
        System.out.println(linkedList);
        System.out.println(linkedList.removeLast());
        System.out.println(linkedList);
        System.out.println(linkedList.remove(1));
        System.out.println(linkedList);
        System.out.println(linkedList.contains(3));
        System.out.println(linkedList.removeElement(3));
        System.out.println(linkedList);
        System.out.println(linkedList.getSize());
        System.out.println(linkedList.isEmpty());
    }

}
