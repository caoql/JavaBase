package cn.cal.javase.datastructure.linked;

/**
 * 回文链表
 */
public class SolutionFACE0206 {

    public static void main(String[] args) {
        ListNode node6 = new ListNode(1, null);
        ListNode node5 = new ListNode(2, node6);
        ListNode node4 = new ListNode(3, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        SolutionFACE0206 solution = new SolutionFACE0206();
        System.out.println(solution.isPalindrome(node1));
    }

    private boolean fastSlowPointer(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        ListNode newHead = null; //头插法构造前半链表的逆序
        ListNode slowNext = slow.next; //由于头插法时，　slow的next节点被更改，因此需要一个额外变量纪录slow指针的下一位置
        while (fast != null && fast.next != null) {
            fast = fast.next.next; //快指针前进两步
            slow.next = newHead;
            newHead = slow; //头插法添加slow节点
            slow = slowNext;
            slowNext = slowNext.next;
        }

        if (fast != null) //如果fast不是null, 则此时链表长度为奇数，snow节点指向中间位置，　将slow向后移动
            //指向后半链表的起点　
            slow = slow.next;

        //比较前半链表的逆序与后半链表是否相同
        while (newHead != null) {
            if (newHead.val != slow.val)
                return false;
            newHead = newHead.next;
            slow = slow.next;
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        return fastSlowPointer(head);
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }
}
