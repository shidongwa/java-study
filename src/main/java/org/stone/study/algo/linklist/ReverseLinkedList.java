package org.stone.study.algo.linklist;

/**
 * 反转链表
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        // initialize list
        ListNode head = ListUtil.initList(10);
        ListUtil.printList(head);

        // 递归反转全链表
        // ListNode newHead = reverseList1(head);
        //ListUtil.printList(head);

        // 非递归方式反转全链表
        //ListNode newHead = reverseList2(head);
        //ListUtil.printList(newHead);

        // 递归反转链表前3个节点
        //ListNode newHead = reverseN(head, 3);
        //ListUtil.printList(newHead);

        // 递归反转链表中第m到n之间的节点, 1开始的索引计数
        ListNode newHead = reverseBetween(head, 3, 5);
        ListUtil.printList(newHead);
    }

    /**
     * 递归方式反转
     * @param head
     * @return
     */
    public static ListNode reverseList1(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode newHead = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 非递归方式反转
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode pre = null;
        ListNode cur = head;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    private static ListNode successor = null;
    /**
     * 递归方式反转前n个节点
     * @param head
     * @param n
     * @return
     */
    public static ListNode reverseN(ListNode head, int n) {
        if(n == 1) {
            successor = head.next;
            return head;
        }

        ListNode newHead = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;

        return newHead;
    }

    /**
     * 反转链表中第m到第n个之间的节点，包括m和n
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == 1) {
            return reverseN(head, n);
        }

        head.next = reverseBetween(head.next, m - 1, n -1);
        return head;
    }
}
