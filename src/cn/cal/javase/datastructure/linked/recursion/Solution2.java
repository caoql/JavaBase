package cn.cal.javase.datastructure.linked.recursion;

/**
 * 
 * 描述：用虚拟头节点删除所有的传入进来的元素 leetcode-203 递归实现
 * 
 * @author 曹启龙
 * @date 2019-03-27 17:38
 */
public class Solution2 {

	/**
	 * 
	 * 描述： 刪除头节点是head的链表中所有的元素val
	 * 
	 * @param head 链表的头节点
	 * @param val  要删除的元素
	 * @return 链表的头节点
	 * @author 曹启龙
	 * @date 2019-03-27 18:53
	 */
	public ListNode removeElements(ListNode head, int val) {
		// 空链表返回的头节点是空
		if (head == null)
			return head;
		// 在这个头节点的下一个节点作为头节点的链表中删除元素val,并返回头节点
		ListNode res = removeElements(head.next, val);
		// 当前节点的值等于要删除的元素，那么当前节点是要删除的节点
		if (head.val == val) {
			// 删除这个节点，让gc回收
			head.next = null;
			return res;
		} else {
			// 作为当前头节点的下一个节点
			head.next = res;
			return head;
		}
		// 下面写的上面的简洁版，但是上面的更好理解
		// head.next = removeElements(head.next, val);
		// return head.val == val ? head.next : head;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 6, 3, 4, 5, 6 };
		ListNode head = new ListNode(nums);
		System.out.println(head);

		ListNode res = (new Solution2()).removeElements(head, 6);
		System.out.println(res);
	}
}
