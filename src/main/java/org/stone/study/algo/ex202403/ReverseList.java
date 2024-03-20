package org.stone.study.algo.ex202403;

/**
 * 1，反转链表
 * 2，每 K 个节点一组，反转链表
 */
public class ReverseList {

    public static void main(String[] args) {
        ListNode head = ListUtil.initList(new int[]{1, 2, 3, 4, 5});

        System.out.println("origin List:");
        ListUtil.printList(head);
        System.out.println();
        System.out.println("after reverse:");
//        ListUtil.printList(reverse1(head, null));
//        ListUtil.printList(reverse2(head));
        ListUtil.printList(reverseKGroup(head, 2));
    }

    /**
     * 非递归方式反转链表head 到 end，不包括 end
     * @param head
     * @param end: 不包括
     * @return
     */
    public static ListNode reverse1(ListNode head, ListNode end) {
        if(head == end || head.getNext() == end) return head;

        ListNode pre = null;
        ListNode cur = head;
        while(cur != end) {
            ListNode next = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = next;
        }

        return pre;
    }

    /**
     * 递归方式反转链表
     * @param head
     * @return
     */
    public static ListNode reverse2(ListNode head) {
        if(head == null || head.getNext() == null) return head;

        ListNode next = head.getNext();
        ListNode newHead = reverse2(next);
        next.setNext(head);
        head.setNext(null);

        return newHead;
    }

    /**
     * k个一组反转链表，不足 k 个时不反转
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        // 加入 dummy 好计算 K 个节点
        ListNode dummy = new ListNode(-1);
        dummy.setNext(head);
        ListNode p = dummy;
        for(int i = 0; i < k; i++) {
            if(p == null || p.getNext() == null) return head;
            p = p.getNext();
        }

        // 第 2 组的首个节点
        ListNode next = p.getNext();
        // g1Head为第 1 组反转后的头节点
        ListNode g1Head = reverse1(head, next);

        // 从第 2 组开始继续递归，g2Head为第 2 组反转后的头节点
        ListNode g2Head = reverseKGroup(next, k);
        // head 现在为第 1 组的尾节点了
        head.setNext(g2Head);

        return g1Head;
    }
}
