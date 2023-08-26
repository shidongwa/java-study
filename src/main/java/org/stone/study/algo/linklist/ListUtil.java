package org.stone.study.algo.linklist;

public class ListUtil {
    public static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while(head != null) {
            sb.append(head.value).append(",");
            head = head.next;
        }
        if(sb.length() > 0)
            sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb.toString());
    }

    public static ListNode initList(int n) {
        ListNode head = new ListNode(1);
        int i = 2;
        ListNode pre = head;
        while(i < n) {
            ListNode node = new ListNode(i);
            pre.setNext(node);
            pre = node;
            ++i;
        }

        return head;
    }
}
