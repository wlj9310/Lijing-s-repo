class LinkedListIterator {
	private ListNode cur;
	public LinkedListIterator (ListNode head)
	{
		cur = head;
	}
	public boolean hasNext() {
		return cur != null;
	}
	public ListNode next() {
		if (cur == null) {
			return null;
		}
		ListNode result = cur;
		cur = cur.next;
		return result;
	}
}