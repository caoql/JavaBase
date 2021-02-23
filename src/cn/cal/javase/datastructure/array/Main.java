package cn.cal.javase.datastructure.array;

/**
 * 描述： 动态数组测试
 *
 * @author 曹启龙
 * @date 2019-03-26 13:54
 */
public class Main {

    public static void main(String[] args) {
        DynamicArray<Integer> array = new DynamicArray<>(6);
        System.out.println(array.getCapacity());
        System.out.println(array.getSize());
        System.out.println(array.isEmpty());
        array.addLast(5);
        array.addLast(10);
        array.add(1, 15);
        array.addLast(25);
        array.addFirst(20);
        System.out.println(array);
        System.out.println(array.get(0));
        array.set(0, 8);
        System.out.println(array);
        System.out.println(array.contains(25));
        System.out.println(array);
        System.out.println(array.indexOf(25));
        System.out.println(array);
        System.out.println(array.removeLast());
        System.out.println(array);
        System.out.println(array.removeFirst());
        // 这里就会缩容了
        System.out.println(array);
        System.out.println(array.remove(0));
        System.out.println(array);
        System.out.println(array.removeElement(10));
        System.out.println(array);
        // 测试扩容
        array.add(45);
        System.out.println(array);
        array.add(46);
        System.out.println(array);
        array.add(47);
        System.out.println(array);
        array.add(48);
        System.out.println(array);
        array.add(49);
        System.out.println(array);
    }

}
