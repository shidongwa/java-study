package org.stone.study.algo.linklist;

import static org.stone.study.algo.linklist.ListUtil.init;
import static org.stone.study.algo.linklist.ListUtil.printList;

public class RemoveDuplicate {

    public static void main(String[] args) {
//        ListNode head = init(new int[]{1, 2, 2, 3});
//        ListNode head = init(new int[]{1, 1, 2, 3});
//        ListNode head = init(new int[]{1, 2, 3, 3});
        ListNode head = init(new int[]{1, 1, 1, 1});
        printList(head);

//        head = removeDuplicate1(head);
        head = removeDuplicate2(head);
        printList(head);
    }

    /**
     * for duplicated node, only keep one
     * @param head
     * @return
     */
    public static ListNode removeDuplicate1(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode fast = head.next;
        ListNode slow = head;
        while(fast != null) {
            if(fast.value != slow.value) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }

        slow.next = null;
        return head;
    }

    /**
     * remove all the duplicate nodes
     * @param head
     * @return
     */
    public static ListNode removeDuplicate2(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = head;
        ListNode slow = dummy;
        while(fast != null) {
            while(fast.next != null && fast.value == fast.next.value) {
                fast = fast.next;
            }
            if(slow.next == fast) {
                slow = slow.next;
            } else {
                slow.next = fast.next;
            }

            fast = fast.next;
        }

        return dummy.next;
    }
}
