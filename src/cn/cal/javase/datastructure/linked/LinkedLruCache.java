package cn.cal.javase.datastructure.linked;

import java.util.Objects;

/**
 * 基于链表的LRU算法实现，最近最少使用。
 * 思路：越靠近链表尾部的元素是越早访问的数据。
 * 1.该数据节点在链表中，则删除原来的节点，在头节点插入该节点。
 * 2.不在链表中，分2种情况。
 * 2.1 链表未满，直接在头结点插入该节点。
 * 2.2 链表已满，删除链尾的节点，在头结点插入该节点。
 */
public class LinkedLruCache {
    /**
     * 链表的长度，缓存的容量
     */
    private int size;
    /**
     * 链表中的节点个数
     */
    private int count = 0;

    private static final int INIT_SIZE = 10;

    /**
     * 虚拟头结点
     */
    private LinkedNode dummyHead = new LinkedNode(0, null);

    public LinkedLruCache(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("size must > 0");
        }
        this.size = size;
    }

    public LinkedLruCache() {
        this(INIT_SIZE);
    }

    public void put(LinkedNode node) {
        // 1.存在就替换
        LinkedNode pre = dummyHead;
        LinkedNode cur = pre.next;
        // 最后一个元素的前面
        LinkedNode lastPre = null;
        while (cur != null) {
            if (Objects.equals(cur.data, node.data)) {
                // 删除当前节点
                pre.next = cur.next;
                cur.next = null;
                // 插入头结点
                node.next = dummyHead.next;
                dummyHead.next = node;
                return;
            }
            lastPre = pre;
            pre = pre.next;
            cur = pre.next;
        }

        // 2.不存在
        // 2.1 未满
        if (count < size) {
            // 插入头结点
            node.next = dummyHead.next;
            dummyHead.next = node;
            count++;
            return;
        }
        // 已满,删除最后一个元素
        LinkedNode last = lastPre.next;
        lastPre.next = null;
        last.next = null;
        // 插入头结点
        node.next = dummyHead.next;
        dummyHead.next = node;
    }

    private boolean isExist(LinkedNode node) {
        LinkedNode pre = dummyHead.next;
        while (pre != null) {
            if (Objects.equals(pre.data, node.data)) {
                return true;
            }
            pre = pre.next;
        }
        return false;
    }

    static class LinkedNode {
        private int data;
        private LinkedNode next;

        public LinkedNode(int data, LinkedNode next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedNode node5 = new LinkedNode(5, null);
        LinkedNode node4 = new LinkedNode(4, null);
        LinkedNode node3 = new LinkedNode(3, null);
        LinkedNode node2 = new LinkedNode(2, null);
        LinkedNode node1 = new LinkedNode(1, null);

        LinkedLruCache cache = new LinkedLruCache(3);
        cache.put(node1);
        cache.put(node1);
        cache.put(node2);
        cache.put(node1);
        cache.put(node3);
        cache.put(node4);
        cache.put(node5);

        System.out.println(cache);
    }

    @Override
    public String toString() {
        String content = getValue(dummyHead.next);
        return "{content : " + content + ", size : " + size + " count: " + count;
    }

    private String getValue(LinkedNode node) {
        if (node == null) {
            return "NULL";
        }
        return node.data + " -> " + getValue(node.next);
    }
}
