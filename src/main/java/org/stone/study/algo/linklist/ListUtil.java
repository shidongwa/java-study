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

    /**
     * reverse linked list
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode pre = null;
        ListNode cur = head;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    /**
     * init Linked List by Array
     * @param arr
     * @return
     */
    public static ListNode init(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        ListNode cur;
        for(int i = 0; i < arr.length; i++) {
            cur = new ListNode(arr[i]);
            pre.next = cur;
            pre = cur;
        }

        return dummy.next;
    }
}
