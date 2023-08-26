package org.stone.study.algo.linklist;

/**
 * K个一组反转链表
 */
public class RerverseKGroup {

    public static void main(String[] args) {
        ListNode head = ListUtil.initList(10);
        ListUtil.printList(head);

        ListNode newHead = reverseKGroup(head, 2);
        ListUtil.printList(newHead);
    }

    /**
     * K个一组，反转链表head
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null) return head;

        int count = 0;
        ListNode cur = head;
        while(cur != null && count < k) {
            ++count;
            cur = cur.next;
        }
        if(count < k) return head;

        // 反转head到cur之间的节点，不包括cur节点
        ListNode newHead = reverseList(head, cur);
        // 递归反转下一组开始的节点
        ListNode newNode = reverseKGroup(cur, k);
        // 修改两组之间节点指针，第一组结束节点指向第二组开始节点
        head.setNext(newNode);

        return newHead;
    }

    /**
     * 反转head到end之间的链表，不包括end
     * @param head
     * @param end
     * @return
     */
    private static ListNode reverseList(ListNode head, ListNode end) {
        ListNode pre = null, cur = head, next = null;
        while(cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
}
