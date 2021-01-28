package cn.cal.javase.datastructure.tree.bst;

/**
 * 描述： 
 * 10,6,8,15,11
 *          10
 *         /  \
 *        6    15
 *        \     /
 *         8    11
 * remove 6,15,8之后add 6,8,15
 *          10
 *         /  \
 *        6    11
 *        \     \
 *         8    15
 * @author 曹启龙
 * @date 2019-03-20 10:08
 */
public class Main {
	public static void main(String[] args) {
		BSTree<Integer> bst = new BSTree<Integer>();
		// 测试增加
		//bst.add(null);
		bst.add(10);
		bst.add(10);
		bst.add(6);
		bst.add(8);
		bst.add(15);
		bst.add(11);
		System.out.println("BSTree=>");
		System.out.println(bst);
		System.out.println("添加个数,size()=>" + bst.size());
		System.out.println("判断元素是否为空,isEmpty()=>"+ bst.isEmpty());
		System.out.println("元素包含,contains(8)=>" + bst.contains(8));
		System.out.println("元素包含,contains(12)=>" + bst.contains(12));
		System.out.println("最小值,getMin()=>" + bst.getMin());
		System.out.println("最大值,getMax()=>" + bst.getMax());
		System.out.println("removeMin()=>" + bst.removeMin());
		System.out.println("removeMax()=>" + bst.removeMax());
		System.out.println("remove(8)=>" + bst.remove(8));
		System.out.println("remove(9)=>" + bst.remove(9));
		System.out.println("删除6，15，8 之后，BSTree=>");
		System.out.println(bst);
		bst.add(6);
		bst.add(8);
		bst.add(15);
		System.out.println("又添加6，8，15之后，BSTree=>");
		System.out.println(bst);
		System.out.println("--------------------------前序遍历");
		bst.preOrder();
		System.out.println("--------------------------非递归栈实现前序遍历");
		bst.preOrderNr();
		System.out.println("--------------------------中序遍历");
		bst.inOrder();
		System.out.println("--------------------------后续遍历");
		bst.postOrder();
		System.out.println("--------------------------层次遍历");
		bst.levelOrder();
		
	}
}
