class TreeNode{
	int value;
	TreeNode left;
	TreeNode right;
	public TreeNode(int value) {
		this.value = value;
	}
}
public class insertTreeNode {
	public TreeNode insert_recursion(TreeNode root, int value) {
		if (root == null) {
			return new TreeNode(value);
		}
		if (root.value < value) {
			root.right = insertTreeNode(root.right, value);
		} else if (root.value > value) {
			root.left = insertTreeNode(root.left, value);
		}
		return root;
	}

	public TreeNode insert_iteration(TreeNode root, int value) {
		if (root == null) {
			return new TreeNode(value);
		}
		TreeNode original = root;
		TreeNode pre = null;
		while (root != null) {
			pre = root;
			if (root.value < value) {
				root = root.right;
			} else if (root.value > value) {
				root = root.left;
			} else {
				return original;
			}
		}
		if (pre.value < value) {
			pre.right = new TreeNode(value);
		} else if (pre.value > value) {
			pre.left = new TreeNode(value);
		}
		return original;
	}
}
