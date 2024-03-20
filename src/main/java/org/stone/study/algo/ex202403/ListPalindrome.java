package org.stone.study.algo.ex202403;


import static org.stone.study.algo.ex202403.ListUtil.findTheMid;
import static org.stone.study.algo.ex202403.ListUtil.reverseToTheEnd;

public class ListPalindrome {

    /**
     * 判断链表中的节点值是否构成回文
     * @param args
     */
    public static void main(String[] args) {
//        ListNode head = ListUtil.initList(new int[]{1, 2, 3, 2, 1});
        ListNode head = ListUtil.initList(new int[]{1, 2, 3, 2, 2});

        System.out.println("origin List:");
        ListUtil.printList(head);
        System.out.println("is palindrome: " + isPalindrome(head));
    }

    /**
     * 判断以 head 开头的链表节点是否构成回文
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        // 找到中间节点。奇数节点返回中间节点；偶数节点返回 2 个中间节点中的第 2 个节点
        ListNode mid = findTheMid(head);
        // 反转中间节点和尾节点间的元素
        ListNode newHead = reverseToTheEnd(mid);
        // 顺序比较前后 2 组节点是否一样
        while(head != null && newHead != null) {
            if(head.getVal() != newHead.getVal()) return false;

            head = head.getNext();
            newHead = newHead.getNext();
        }

        return true;
    }
}
