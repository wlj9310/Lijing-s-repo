class BSTIterator {
	private Deque<TreeNode> stack;
	public BSTIterator(TreeNode root) {
		stack = new LinkedList<TreeNode>();
		TreeNode cur = root;
		while (cur != null) {
			stack.offerFirst(cur);
			cur = cur.left;
		}
	} 
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	public TreeNode next() {
		TreeNode result = stack.pollFirst();
		TreeNode cur = result;
		if (cur.right != null) {
			stack.offerFirst(cur.right);
			cur = cur.right;
			while (cur != null) {
				stack.offerFirst(cur);
				cur = cur.left;
			}
		}
		return result;
	}
}