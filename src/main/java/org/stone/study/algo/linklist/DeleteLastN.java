package org.stone.study.algo.linklist;

import static org.stone.study.algo.linklist.ListUtil.init;
import static org.stone.study.algo.linklist.ListUtil.printList;

public class DeleteLastN {

    public static void main(String[] args) {
        ListNode head = init(new int[]{1, 2, 3, 4, 5});
        printList(head);
        head = deleteLastN(head, 1);
        printList(head);
    }

    /**
     * delete the N node, N is count from right to left
     * @param head
     * @param n
     * @return
     */
    public static ListNode deleteLastN(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p1 = dummy;
        for(int i = 1; i <= n; i++) {
            p1 = p1.next;
        }

        ListNode p2 = dummy;
        // locate p2 (the left node of the node to be deleted)
        while(p1 != null && p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        p2.next = p2.next.next;

        return dummy.next;
    }
}
