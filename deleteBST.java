class TreeNode {
	int value;
	TreeNode left;
	TreeNode right;
	public TreeNode(int value) {
		this.value = value;
	}
}
public class deleteBST {
	public TreeNode delete (TreeNode root, int target) {
		if (root == null) {
			return null;
		}
		if (root.value > target) {
			root.left = delete(root.left, target);
			return root;
		} else if (root.value < target) {
			root.right = delete(root.right, target);
			return root;
		}
		// guarantee that root == null  or root.value = target
		if (root.left == null) {
			return root.right;
		} else if (root.right == null) {
			return root.left;
		} else if (root.right.left == null) {
			root.right.left = root.left;
			return root.right;
		} else {
			TreeNode successor = getSuccessor(root);
			successor.right = root.right;
			successor.left = root.left;
			return successor;
		}
	}
	private TreeNode getSuccessor(TreeNode root) {
		TreeNode pre = root.right;
		TreeNode cur = root.right.left;
		while (cur.left != null) {
			pre = cur;
			cur = cur.left;
		}
		pre.left = cur.right;
		return cur;
	}
}