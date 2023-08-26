package org.stone.study.algo.linklist;

/**
 * 链表是否是回文。
 * 正常思维是反转链表到一个临时链表中，再和原始链表对比；这里通过递归后序遍历链表实现
 */
public class Palindrome {
    private static ListNode left = null;

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(1);
        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        System.out.println("isPalindrome:" + isPalindrome(head));
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
