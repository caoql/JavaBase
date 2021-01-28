package cn.cal.javase.datastructure.tree.avl;

/**
 * 描述：AVL树是根据它的发明者G.M. Adelson-Velsky和E.M. Landis命名的。
 * 它是最先发明的自平衡二叉查找树，也被称为高度平衡树。相比于"二叉查找树"，它的特点是：AVL树中任何节点的两个子树的高度最大差别为1。
 * 
 * @author 曹启龙
 * @date 2019-03-22 17:32
 */
public class AvlTree<K extends Comparable<K>, V> {
	// 根节点
	private Node root;
	// 树的节点个数
	private int size;

	public AvlTree() {
		root = null;
		size = 0;
	}

	// 获取树的节点数
	public int size() {
		return size;
	}

	// 判断是否非空
	public boolean isEmpty() {
		return size == 0;
	}

	// 判断是否包含某个key
	public boolean contains(K key) {
		return getNode(root, key) != null;
	}

	// 获取某个key的值
	public V get(K key) {
		Node node = getNode(root, key);
		return node == null ? null : node.value;
	}

	// 获取某个最小Key的值
	public K getMinKey() {
		Node node = minNode(root);
		return node == null ? null : node.key;
	}

	// 获取某个最大Key的值
	public K getMaxKey() {
		Node node = MaxNode(root);
		return node == null ? null : node.key;
	}

	// 获取某个最小Key的值
	public V getMinValue() {
		Node node = minNode(root);
		return node == null ? null : node.value;
	}

	// 获取某个最大Key的值
	public V getMaxValue() {
		Node node = MaxNode(root);
		return node == null ? null : node.value;
	}

	// 设置某个key的值
	public V set(K key, V newValue) {
		Node node = getNode(root, key);
		if (node == null)
			throw new IllegalArgumentException(key + " doesn't exist!");
		V oldValue = node.value;
		node.value = newValue;
		return oldValue;
	}

	// 重构的添加方法
	public void add(K key) {
		if (key == null)
			return;
		root = add(root, key, null);
	}

	// 向二分搜索树中添加新的元素(key, value)
	public void add(K key, V value) {
		if (key == null)
			return;
		root = add(root, key, value);
	}

	// 向以node为根的二分搜索树中插入元素(key, value)，递归算法
	// 返回插入新节点后二分搜索树的根
	private Node add(Node node, K key, V value) {
		// 递归终止条件
		if (node == null) {
			size++;
			return new Node(key, value);
		}
		// 递归体
		// 1.加入节点
		if (key.compareTo(node.key) < 0)
			// 比节点的值小就往左子树添加
			node.left = add(node.left, key, value);
		else if (key.compareTo(node.key) > 0)
			// 比节点的值大就往右子树添加
			node.right = add(node.right, key, value);
		else // key.compareTo(node.key) == 0,相等就替换值
			node.value = value;
		// 2.更新height
		node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
		// 3.计算平衡因子
		int balanceFactor = getBalanceFactor(node);
		// Math.abs(balanceFactor)
		System.out.println("unbalanced : " + balanceFactor);

		return node;
	}

	// 从二分搜索树中删除键为key的节点
	public V remove(K key) {
		Node node = getNode(root, key);
		if (node != null) {
			root = remove(root, key);
			return node.value;
		}
		return null;
	}

	// 删除掉以node为根的二分搜索树中的节点
	// 返回删除节点后新的二分搜索树的根
	private Node remove(Node node, K key) {
		if (node == null)
			return null;
		if (key.compareTo(node.key) < 0) {
			node.left = remove(node.left, key);
			return node;
		} else if (key.compareTo(node.key) > 0) {
			node.right = remove(node.right, key);
			return node;
		} else { // key.compareTo(node.key) == 0
			// 待删除节点左子树为空的情况
			if (node.left == null) {
				Node rightNode = node.right;
				node.right = null;
				size--;
				return rightNode;
			}
			// 待删除节点右子树为空的情况
			if (node.right == null) {
				Node leftNode = node.left;
				node.left = null;
				size--;
				return leftNode;
			}

			// 待删除节点左右子树均不为空的情况
			// 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
			// 用这个节点顶替待删除节点的位置
			Node successor = minNode(node.right);
			successor.right = removeMin(node.right);
			successor.left = node.left;

			node.left = node.right = null;

			return successor;
		}
	}

	// 删除掉以node为根的二分搜索树中的最小节点
	// 返回删除节点后新的二分搜索树的根
	private Node removeMin(Node node) {
		if (node == null)
			throw new IllegalArgumentException(node + " doesn't exist!");
		if (node.left == null) {// 当前节点就是最小的节点
			Node rightNode = node.right;
			// 断开连接
			node.right = null;
			size--;
			// 返回给上一个连接
			return rightNode;
		}
		node.left = removeMin(node.left);
		return node;
	}

	// 返回以node为根的二分搜索树的最小值所在的节点
	private Node minNode(Node node) {
		if (node == null)
			return null;
		if (node.left == null)
			return node;
		return minNode(node.left);
	}

	// 返回以node为根的二分搜索树的最大值所在的节点
	private Node MaxNode(Node node) {
		if (node == null)
			return null;
		if (node.right == null)
			return node;
		return MaxNode(node.right);
	}

	// 返回以node为根节点的二分搜索树中，key所在的节点
	private Node getNode(Node node, K key) {
		if (node == null || key == null)
			return null;
		if (key.equals(node.key))
			return node;
		else if (key.compareTo(node.key) < 0)
			return getNode(node.left, key);
		else // if(key.compareTo(node.key) > 0)
			return getNode(node.right, key);
	}

	// 获得节点node的高度
	private int getHeight(Node node) {
		if (node == null)
			return 0;
		return node.height;
	}

	// 获得节点node的平衡因子
	private int getBalanceFactor(Node node) {
		if (node == null)
			return 0;
		return getHeight(node.left) - getHeight(node.right);
	}

	// 树的节点
	private class Node {
		public K key;
		public V value;
		public Node left, right;
		// 树的高度,叶子节点为1,父节点比子节点加1
		public int height;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
			height = 1;
		}
	}
}
