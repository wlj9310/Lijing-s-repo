/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class oddEvenList {
    public ListNode oddEvenList(ListNode head) {
        ListNode oddhead = new ListNode(0);
        ListNode evenhead = new ListNode(0);
        ListNode pointer = head;
        ListNode op = oddhead;
        ListNode ep = evenhead;
        while (pointer != null) {
            op.next = pointer;
            pointer = pointer.next;
            op = op.next;
            ep.next = pointer;
            if (pointer == null) {
                break;
            } else {
                pointer = pointer.next;
                ep = ep.next;
            }
        }
        op.next = evenhead.next;
        return oddhead.next;
    }
}