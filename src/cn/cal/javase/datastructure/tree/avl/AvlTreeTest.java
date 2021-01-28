package cn.cal.javase.datastructure.tree.avl;

/**
 * 描述：  平衡二叉树测试
 * @author 曹启龙  
 * @date 2019-03-22 17:41
 */
public class AvlTreeTest {

	public static void main(String[] args) {
		AvlTree<Integer, String> avlTree = new AvlTree<>();
		avlTree.add(10);
		avlTree.add(7);
		avlTree.add(4,"hi");
		avlTree.add(8);
		System.out.println("size: " + avlTree.size());
		System.out.println("isEmpty: " + avlTree.isEmpty());
		System.out.println("contains(4): " + avlTree.contains(4));
		System.out.println("getMinKey: " + avlTree.getMinKey());
		System.out.println("getMaxKey: " + avlTree.getMaxKey());
		System.out.println("getMinValue: " + avlTree.getMinValue());
		System.out.println("getMaxValue: " + avlTree.getMaxValue());
		System.out.println("set(4, \"hello\"): " + avlTree.set(4, "hello"));
		System.out.println("get(4): " + avlTree.get(4));
		System.out.println("remove(8): " + avlTree.remove(8));
	}

}
