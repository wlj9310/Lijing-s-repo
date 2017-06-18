/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class mergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
    ListNode dummy = new ListNode(0);
    ListNode prev = dummy;
    PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>(){
      @Override
      public int compare(ListNode x, ListNode y) {
        if (x.val == y.val) {
          return 0;
        }
        return x.val < y.val ? -1 : 1;
      }
    });
    for (ListNode l : lists) {
      if (l != null) {
        heap.offer(l);
      }
    } 
    while (!heap.isEmpty()) {
      ListNode cur = heap.poll();
      prev.next = cur;
      if (cur.next != null) {
        heap.offer(cur.next);
      }
      prev = cur;
    }
    return dummy.next;
  }
}