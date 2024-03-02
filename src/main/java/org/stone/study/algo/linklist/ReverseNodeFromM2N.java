package org.stone.study.algo.linklist;

import static org.stone.study.algo.linklist.ListUtil.init;
import static org.stone.study.algo.linklist.ListUtil.printList;

/**
 * reverse from node m to n, 1-based index, inclusive m and n
 */
public class ReverseNodeFromM2N {

    public static void main(String[] args) {
        ListNode head = init(new int[]{1, 2, 3, 4, 5});

        // 1,2,3,4,5
        printList(head);
        head = reverseFromM2N(head, 2, 3);
        // 1,4,5
        printList(head);
    }

    /**
     *
     * @param head
     * @param from: 1-based index
     * @param to
     * @return
     */
    public static ListNode reverseFromM2N(ListNode head, int from, int to) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // pre node before node from
        ListNode pre = dummy;
        for(int i = 1; i < from; i++) {
            pre = pre.next;
        }

        // reverse nodes between m and n
        ListNode cur = pre.next;
        ListNode tail = pre.next;
        for(int i = from; i <= to; i++) {
            ListNode next = cur.next;
            if(i == to) { // update tail node for the last iteration
               tail.next = next;
            }

            cur.next = pre.next;
            pre.next = cur;
            cur = next;
        }

        return dummy.next;
    }
}
