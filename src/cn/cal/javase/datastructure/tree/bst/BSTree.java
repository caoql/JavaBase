package cn.cal.javase.datastructure.tree.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * 描述： 二分搜索树（Binary Search Tree）-自己的实现，不包含重复元素和null元素 
 * 1.不管是先序，中序，后序遍历都是深度优先遍历
 * 2.层次遍历：广度优先遍历
 * 3.性能分析：空间换时间 
 * 4.例图分析：
 *          10
 *         /  \
 *        6    15
 *        \     /
 *         8    11
 * @author 曹启龙
 * @date 2019-03-28 13:09
 */
public class BSTree<E extends Comparable<E>> {
	// 根节点
	private Node root;
	// 长度
	private int size;

	public BSTree() {
		root = null;
		size = 0;
	}

	// 返回树中元素的个数
	public int size() {
		return size;
	}

	// 判断是否为空
	public boolean isEmpty() {
		return size == 0;
	}

	// 添加元素,0(lgN),最坏情况下退化为链表是O(n)
	public void add(E e) {
		if (e == null) {
			throw new IllegalArgumentException("Add failed. argument can not null");
		}
		root = add(root, e);
	}

	// 向以node为根的二分搜索树中插入元素e,递归算法
	// 返回插入新节点后二分搜索树的根
	private Node add(Node node, E e) {
		// 根节点为空
		if (node == null) {
			size++;
			return new Node(e);
		}
		// 小于根节点,左节点做根节点,并把返回的根节点作为当前根节点的左节点
		if (e.compareTo(node.e) < 0) {
			node.left = add(node.left, e);
		}
		// 大于跟节点,右节点做根节点,并把返回的根节点作为当前根节点的右节点
		else if (e.compareTo(node.e) > 0) {
			node.right = add(node.right, e);
		}
		// 等于情况不重复添加 node.e.compareTo(e) == 0
		return node;
	}

	// 判断该树中是否包含某个元素,O(lgN),最坏情况下蜕化为链表，O(n)
	public boolean contains(E e) {
		if (e == null) {
			return false;
		}
		return contains(root, e);
	}

	// 判断某个根节点下面是否包含某个元素,递归算法
	private boolean contains(Node node, E e) {
		if (node == null) {
			return false;
		}
		// 元素值小于根节点,在左子树中找
		if (e.compareTo(node.e) < 0) {
			return contains(node.left, e);
		}
		// 元素值大于根节点,在右子树中找
		else if (e.compareTo(node.e) > 0) {
			return contains(node.right, e);
		}
		// 元素值等于根节点值e.compareTo(node.e) == 0,找到
		else {
			return true;
		}
	}

	// 获取最小值,O(lgN),最坏退化成左链表，O(N),退化成右链表,O(1)
	public Node getMin() {
		if (root == null) {
			throw new IllegalArgumentException("failed. BSTree is empty");
		}
		return getMin(root);
	}

	private Node getMin(Node node) {
		// 判断是否有左孩子,无则当前节点是最小值
		if (node.left == null) {
			return node;
		} else { // 有则递归
			return getMin(node.left);
		}
	}

	// 获取最大值,O(lgN),最坏退化成右链表，O(N),退化成左链表,O(1)
	public Node getMax() {
		if (root == null) {
			throw new IllegalArgumentException("failed. BSTree is empty");
		}
		return getMax(root);
	}

	private Node getMax(Node node) {
		// 判断是否有右孩子,无则当前节点是最大值
		if (node.right == null) {
			return node;
		} else { // 有则递归
			return getMax(node.right);
		}
	}

	// 删除指定的元素,O(lgN),退化成链表后就是0(N)
	public boolean remove(E e) {
		if (root == null) {
			throw new IllegalArgumentException("failed. BSTree is empty");
		}
		if (e == null) {
			return false;
		}
		int size1 = size;
		root = remove(root, e);
		int size2 = size;
		return size1 - size2 > 0;
	}

	/**
	 * 利用这个图来理解,删除4，删除15，删除6，符合删除的场景
	 *            10
	 *         /     \
	 *         6      15
	 *       /  \     /
	 *      4    8   13
	 *      \   / \
	 *       5 7   9
	 */      
	// 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
	// 返回删除节点后新的二分搜索树的根
	private Node remove(Node node, E e) {
		// node为null,就是没有执行删除e操作,返回node节点就好
		if (node == null) {
			return node;
		}
		// 左子树中删
		if (e.compareTo(node.e) < 0) {
			node.left = remove(node.left, e);
			// 右子树中删
		} else if (e.compareTo(node.e) > 0) {
			node.right = remove(node.right, e);
		} else {
			// 元素相等,删除的就是这个值
			if (node.left == null) {
				Node rightNode = node.right;
				node.right = null;
				size--;
				return rightNode;
			}
			if (node.right == null) {
				Node leftNode = node.left;
				node.left = null;
				size--;
				return leftNode;
			}
			// 待删除节点左右子树均不为空的情况
			// 找后继节点,可以从左子树中找最大值,也可以从右子树中找最小值,用这个节点顶替待删除节点的位置,我的实现是从右子树中找最小值
			Node successor = getMin(node.right);
			// 删除这个最小节点
			successor.right = removeMin(node.right);
			successor.left = node.left;
			node.left = node.right = null;
			return successor;
		}
		return node;
	}

	// 删除最小值
	public E removeMin() {
		Node node = getMin();
		root = removeMin(root);
		return node.e;
	}

	// 删除该节点下的最小值
	private Node removeMin(Node node) {
		if (node == null) {
			return node;
		}
		// 判断是否有左孩子,无则当前节点是最小值,他就应该被删除
		if (node.left == null) {
			Node rightNode = node.right;
			// 断掉node节点的右连接
			node.right = null;
			size--;
			// 返回node.right给上层调用,作为上层的左子树，这样会断掉上层和node节点的左连接，这样node没有任何连接指向了，交给gc回收
			return rightNode;
		}
		node.left = removeMin(node.left);
		return node;
	}

	// 删除最大值
	public E removeMax() {
		Node node = getMax();
		root = removeMax(root);
		return node.e;
	}

	// 删除该节点下的最大值,返回这个根
	private Node removeMax(Node node) {
		if (node == null) {
			return node;
		}
		// 判断是否有右孩子,无则当前节点是最大值
		if (node.right == null) {
			// 当前位置由左孩子替代
			Node leftNode = node.left;
			node.left = null;
			size--;
			return leftNode;
		}
		// 有则递归
		node.right = removeMax(node.right);
		return node;
	}

	// 前序遍历-根左右,O(n)
	public void preOrder() {
		preOrder(root);
	}

	// 递归算法
	private void preOrder(Node node) {
		// 递归终止条件
		if (node == null) {
			return;
		}
		// 输出根节点
		System.out.println(node.e);
		// 遍历左子树
		preOrder(node.left);
		// 遍历右子树
		preOrder(node.right);
	}

	// 前序遍历-非递归实现,利用栈
	public void preOrderNr() {
		if (root == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			System.out.println(cur.e);
			if (cur.right != null) {
				stack.push(cur.right);
			}
			if (cur.left != null) {
				stack.push(cur.left);
			}
		}
	}

	// 中序遍历-左根右,O(N)
	public void inOrder() {
		inOrder(root);
	}

	// 递归算法
	private void inOrder(Node node) {
		if (node == null) {
			return;
		}
		// 遍历左子树
		inOrder(node.left);
		// 输出根节点
		System.out.println(node.e);
		// 遍历右子树
		inOrder(node.right);
	}

	// 后续遍历,O(N)
	public void postOrder() {
		postOrder(root);
	}

	// 递归算法
	private void postOrder(Node node) {
		if (node == null)
			return;
		// 遍历左子树
		postOrder(node.left);
		// 遍历右子树
		postOrder(node.right);
		// 输出根节点
		System.out.println(node.e);
	}

	// 层次遍历,根,同一级从左至右,广度优先,利用队列实现
	public void levelOrder() {
		if (root == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node cur = queue.remove();
			// 根
			System.out.println(cur.e);
			// 左
			if (cur.left != null) {
				queue.add(cur.left);
			}
			// 右
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
	}

	// 生成以node为根节点，深度为depth的描述二叉树的字符串
	private void generateBSTString(Node node, int depth, StringBuilder res) {
		if (node == null) {
			res.append(generateDepthString(depth) + "null\n");
			return;
		}
		// 输出根节点
		res.append(generateDepthString(depth) + node.e + "\n");
		// 遍历左子树
		generateBSTString(node.left, depth + 1, res);
		// 遍历右子树
		generateBSTString(node.right, depth + 1, res);
	}

	// 生成深度
	private String generateDepthString(int depth) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < depth; i++)
			res.append("--");
		return res.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		generateBSTString(root, 0, sb);
		return sb.toString();
	}

	// 节点类,外面不关心
	private class Node {
		public E e;
		public Node left, right;

		public Node(E e) {
			this.e = e;
			this.left = null;
			this.right = null;
		}

		@Override
		public String toString() {
			if (e == null)
				return null;
			return e.toString();
		}
	}
}
