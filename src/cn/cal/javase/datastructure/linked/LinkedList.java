package cn.cal.javase.datastructure.linked;

/**
 * 
 * 描述：链表：最简单的动态数据结构 1.单链表
 * 
 * @author 曹启龙
 * @date 2019-03-27 11:42
 */
public class LinkedList<E> {

	// 引入虚拟头节点统一插入删除操作
	private Node dummyHead;
	private int size;

	public LinkedList() {
		this.dummyHead = new Node();
		this.size = 0;
	}

	// 获取链表中的元素个数
	public int getSize() {
		return size;
	}

	// 返回链表是否为空
	public boolean isEmpty() {
		return size == 0;
	}

	// 在链表的index(0-based)位置添加新的元素e,O(n)
	// 在链表中不是一个常用的操作，练习用：）
	public void add(int index, E e) {
		if (index < 0 || index > size)
			throw new IllegalArgumentException("Add failed. Illegal index.");
		// 先找到要插入位置节点的前一个节点，让插入的节点的next节点指向前一个节点的next节点，前一个节点的next在指向要插入的节点
		Node pre = dummyHead;
		for (int i = 0; i < index; i++) {
			pre = pre.next;
		}
		// pre.next = new Node(e, pre.next);
		Node node = new Node(e);
		node.next = pre.next;
		pre.next = node;
		size++;
	}

	// 在链表头添加新的元素e,O(1)
	public void addFirst(E e) {
		add(0, e);
	}

	// 在链表末尾添加新的元素e,O(n)
	public void addLast(E e) {
		add(size, e);
	}

	// 获得链表的第index(0-based)个位置的元素,O(n)
	// 在链表中不是一个常用的操作，练习用：）
	public E get(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("Get failed. Illegal index.");
		// 获取到第一个元素
		Node cur = dummyHead.next;
		// 获取到index位置的元素
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur.e;
	}

	// 获得链表的第一个元素，O(1)
	public E getFirst() {
		return get(0);
	}

	// 获得链表的最后一个元素,O(n)
	public E getLast() {
		return get(size);
	}

	// 查找链表中是否有元素e,O(n)
	public boolean contains(E e) {
		// 获取到第一个元素
		Node cur = dummyHead.next;
		while (cur != null) {
			if ((cur.e == null && e == null) || (cur.e != null && cur.e.equals(e))) {
				return true;
			}
			cur = cur.next;
		}
		return false;
	}

	// 修改链表的第index(0-based)个位置的元素为e,O(n)
	// 在链表中不是一个常用的操作，练习用：）
	public E set(int index, E e) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("Set failed. Illegal index.");
		// 获取到第一个元素
		Node cur = dummyHead.next;
		// 获取到index位置的元素
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		E old = cur.e;
		cur.e = e;
		return old;
	}

	// 从链表中删除index(0-based)位置的元素, 返回删除的元素,O(n)
	// 在链表中不是一个常用的操作，练习用：）
	public E remove(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("Remove failed. Index is illegal.");
		// 找到待删除节点的前一个节点，让删除节点的前一个节点的next指向待删除节点的next,待删除节点的next指向null,等待gc回收
		Node pre = dummyHead;
		for (int i = 0; i < index; i++) {
			pre = pre.next;
		}
		Node delNode = pre.next;
		E delR = delNode.e;
		pre.next = delNode.next;
		delNode.next = null;
		size--;
		return delR;
	}

	// 从链表中删除第一个元素, 返回删除的元素,O(1)
	public E removeFirst() {
		return remove(0);
	}

	// 从链表中删除最后一个元素, 返回删除的元素,0(n)
	public E removeLast() {
		return remove(size - 1);
	}

	// 从链表中删除元素e,找到第一个元素删除，O(1)
	public boolean removeElement(E e) {
		Node pre = dummyHead;
		// 找到相等的pre
		while (pre.next != null) {
			if ((pre.next.e == null && e == null) || (pre.next.e != null && pre.next.e.equals(e)))
				break;
			pre = pre.next;
		}
		if (pre.next != null) {
			Node delNode = pre.next;
			pre.next = delNode.next;
			delNode.next = null;
			size--;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (Node cur = dummyHead.next; cur != null; cur = cur.next)
			res.append(cur + "->");
		res.append("null");
		return res.toString();
	}

	// 结点
	private class Node {
		public E e;
		public Node next;

		public Node(E e, Node next) {
			this.e = e;
			this.next = next;
		}

		public Node(E e) {
			this(e, null);
		}

		public Node() {
			this(null, null);
		}

		@Override
		public String toString() {
			if (e == null)
				return null;
			return e.toString();
		}
	}
}
