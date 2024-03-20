package org.stone.study.algo.linklist;

import static org.stone.study.algo.linklist.ListUtil.init;
import static org.stone.study.algo.linklist.ListUtil.reverse;

/**
 * 链表是否是回文。
 * 正常思维是反转链表到一个临时链表中，再和原始链表对比；这里通过递归后序遍历链表实现
 */
public class Palindrome {
    private static ListNode left = null;

    public static void main(String[] args) {
//        ListNode head = init(new int[]{1, 2, 3, 2, 1});
        ListNode head = init(new int[]{1, 2, 2, 1});
//        ListNode head = init(new int[]{1,2});

        System.out.println("isPalindrome:" + isPalindrome(head));
        System.out.println("isPalindrome:" + isPalindrome2(head));
    }

    /**
     * reverse the second half, then compare the first and second half，
     * @param head
     * @return
     */
    public static boolean isPalindrome2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the second half
        ListNode p2 = reverse(slow);
        ListNode p1 = head;
        // compare the first and the second half is same
        while(p1 != null && p2 != null) {
            if(p1.value != p2.value) return false;
            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }

    public static boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }

    private static boolean traverse(ListNode right) {
        if(right == null) return true;

        boolean ans = traverse(right.next);
        ans = ans && (right.value == left.value);
        left = left.next;
        return ans;
    }
}
