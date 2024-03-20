package org.stone.study.algo.ex202403;

public class IntersectList {

    public static void main(String[] args) {
        ListNode head1 = ListUtil.initList(new int[]{1, 2, 3, 7, 8});
        ListNode head2 = ListUtil.initList(new int[]{4, 5});

        ListNode p1 = ListUtil.findTailNode(head2);
        ListNode p2 = ListUtil.findNode(head1, 7);
        p1.setNext(p2);
        System.out.println("origin head1:");
        ListUtil.printList(head1);
        System.out.println();
        System.out.println("origin head2:");
        ListUtil.printList(head2);
        System.out.println();
        System.out.println("is intersect at node with val: " + isIntersect(head1, head2).getVal());
    }

    /**
     * 求 2 个链表的相交节点。如果不存在的话返回 null
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode isIntersect(ListNode head1, ListNode head2) {
        ListNode p1 = head1;
        ListNode p2 = head2;

        while(p1 != null && p2 != null) {
            if(p1 == p2) return p1;

            p1 = p1.getNext();
            if(p1 == null) p1 = head2;

            p2 = p2.getNext();
            if(p2 == null) p2 = head1;
        }

        return null;
    }

}
