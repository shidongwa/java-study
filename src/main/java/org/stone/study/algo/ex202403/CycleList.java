package org.stone.study.algo.ex202403;

import static org.stone.study.algo.ex202403.ListUtil.findNode;
import static org.stone.study.algo.ex202403.ListUtil.findTailNode;

public class CycleList {

    public static void main(String[] args) {
        ListNode head = ListUtil.initList(new int[]{1, 2, 3, 4, 5});

        // 构造链表中的循环
        ListNode p1 = findTailNode(head);
        ListNode p2 = findNode(head, 3);
        p1.setNext(p2);

        System.out.println("has cycle:" + hasCycle(head));
    }

    /**
     * 通过快慢指针判断链表是否有环
     * @param head
     * @return
     */
    private static boolean hasCycle(ListNode head) {
        if(head == null || head.getNext() == null) return false;

        ListNode slow = head, fast = head.getNext().getNext();

        while(fast != null && fast.getNext() != null) {
            if(slow == fast) return true;
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return false;
    }
}
