/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class addTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0;
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (l1 != null || l2 != null || sum != 0) {
            sum = sum + (((l1 == null) ? 0 : l1.val) + ((l2 == null) ? 0 : l2.val));
            head.next = new ListNode(sum % 10);
            sum = sum / 10;
            head = head.next;
            l1 = (l1 == null ? l1 : l1.next);
            l2 = (l2 == null ? l2 : l2.next);
        }
        return dummy.next;
    }
}