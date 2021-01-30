package cn.cal.javase.algorithm.lru;

import java.util.HashMap;

/**
 * LRU算法实现缓存 双向链表 + 哈希表 实现
 *
 * @param <K>
 * @param <V>
 */
public class LruCache<K, V> {

    private int capacity;

    private Node head;
    private Node tail;
    private HashMap<K, Node> cache;

    public LruCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity is more than 0");
        }

        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);

        head = new Node(null, null);
        tail = new Node(null, null);

        head.next = tail;
        tail.pre = head;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is not null");
        }

        if (!cache.containsKey(key)) {
            return null;
        }

        Node node = cache.get(key);
        // 从原来的位置删除
        unlink(node);
        // 插入到头上面去
        appendHead(node);

        return node.value;
    }

    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key is not null");
        }

        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            V oldValue = node.value;
            // 从原来的位置删除
            unlink(node);
            node.value = value;
            //  插入到头上面去
            appendHead(node);
            // 更新
            cache.put(key, node);
            return oldValue;
        }

        // 先判断空间是否足够
        if (cache.size() >= capacity) {
            // 删除最后一个位置的节点
            Node node = removeTail();
            // cache同步删除
            cache.remove(node.key);
        }

        //  新节点插入到头上面去
        Node node = new Node(key, value);
        appendHead(node);
        cache.put(key, node);

        return null;
    }

    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is not null");
        }

        if (!cache.containsKey(key)) {
            return null;
        }

        Node node = cache.get(key);
        // 从原来的位置删除
        unlink(node);
        cache.remove(key);

        return node.value;
    }

    private void appendHead(Node node) {
        Node next = head.next;

        node.next = next;
        next.pre = node;

        node.pre = head;
        head.next = node;
    }

    private void unlink(Node node) {
        Node pre = node.pre;
        Node next = node.next;

        pre.next = next;
        next.pre = pre;

        node.pre = null;
        node.next = null;
    }

    // 其实删除最后一个元素是unlink一个节点的特殊方法而已，可以把最后一个节点标记出来就好了
    private Node removeTail() {
        Node node = tail.pre;
        unlink(node);
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("cache top [");
        Node node = head.next;
        while (node != null) {
            if (node.next != tail) {
                sb.append(node).append(", ");
                node = node.next;
            } else {
                sb.append(node);
                break;
            }
        }
        sb = sb.append("] tail");
        return sb.toString();
    }

    private class Node {
        Node pre;
        Node next;
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + ":" + value;
        }
    }

}
