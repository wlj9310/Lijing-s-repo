import java.util.*;

class TreeNode {
	int value;
	TreeNode left;
	TreeNode right;
	public TreeNode(int value) {
		this.value = value;
	}
}


public class TreeTraversal {
	public List<Integer> PreOrder(TreeNode root) {
		if (root == null) {
			return null;
		}
		List<Integer> result = new ArrayList<>();
		Deque<TreeNode> stack = new LinkedList<>();
		stack.offerFirst(root);
		while (!stack.isEmpty()) {
			TreeNode cur = stack.pollFirst();
			result.add(cur.value);
			if (cur.right != null) {
				stack.offerFirst(cur.right);
			}
			if (cur.left != null) {
				stack.offerFirst(cur.left);
			}
		}
		return result;
	}
	
	public List<Integer> InOrder(TreeNode root) {
		if (root == null) {
			return null;
		}
		List<Integer> result = new ArrayList<>();
		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode helper = root;
		while (helper != null || !stack.isEmpty()) {
			if (helper != null) {
				stack.offerFirst(helper);
				helper = helper.left;
			} else {
				helper = stack.pollFirst();
				result.add(helper.value);
				helper = helper.right;
			}
		}
		return result;
	}
	public List<Integer> PostOrder(TreeNode root) {
		if (root == null) {
			return null;
		}
		List<Integer> result = new ArrayList<>();
		Deque<TreeNode> stack = new LinkedList<>();
		stack.offerFirst(root);
		TreeNode prev = null;
		while (!stack.isEmpty()) {
			TreeNode cur = stack.peekFirst();
			if (prev == null || prev.left == cur || prev.right == cur) {
				if (cur.left != null) {
					stack.offerFirst(cur.left);
				} else if (cur.right != null) {
					stack.offerFirst(cur.right);
				} else {
					result.add(cur.value);
					stack.pollFirst();
				}
			} else if (prev == cur.left) {
				if (cur.right != null) {
					stack.offerFirst(cur.right);
				} else {
					result.add(cur.value);
					stack.pollFirst();
				}
			} else {
				result.add(cur.value);
				stack.pollFirst();
			}
			prev = cur;
		}
		return result;
	}
}
