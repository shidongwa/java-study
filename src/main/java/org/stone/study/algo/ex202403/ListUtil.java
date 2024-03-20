package org.stone.study.algo.ex202403;

public class ListUtil {

    public static ListNode initList(int[] arr) {
        ListNode dummy = new ListNode(-1);

        ListNode pre = dummy;
        for(int e : arr) {
            ListNode n = new ListNode(e);
            pre.setNext(n);
            pre = n;
        }

        return dummy.getNext();
    }

    public static void printList(ListNode head) {
        ListNode p = head;
        while(p != null) {
            System.out.print(p.getVal() + ", ");
            p = p.getNext();
        }
    }

    /**
     * 从 head 节点反转链表到结尾
     * @param head
     * @return
     */
    public static ListNode reverseToTheEnd(ListNode head) {
        if(head == null) return head;

        ListNode pre = null;
        ListNode cur = head;
        while(cur != null) {
            ListNode next = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = next;
        }

        return pre;
    }

    /**
     * 找链表的中间节点。奇数节点返回中间节点；偶数节点返回 2 个中间节点中的第 2 个节点，本场景主要方便回文比较
     * 1, 2, 3 返回 2
     * 1，2，3，4 返回 3
     * @param head
     * @return
     */
    public static ListNode findTheMid(ListNode head) {
        if(head == null) return head;

        ListNode slow = head, fast = head;
        while(fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return slow;
    }

    /**
     * 找链表中尾节点
     * @param head
     * @return
     */
    public static ListNode findTailNode(ListNode head) {
        if(head == null) return head;

        ListNode p = head;
        while(p.getNext() != null) {
            p = p.getNext();
        }

        return p;
    }

    /**
     * 找链表中节点值为 val 的节点
     * @param head
     * @return
     */
    public static ListNode findNode(ListNode head, int val) {
        ListNode p = head;
        while(p != null) {
            if(p.getVal() == val) return p;
            p = p.getNext();
        }

        return null;
    }
}
