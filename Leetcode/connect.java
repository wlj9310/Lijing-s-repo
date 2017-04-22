/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class connect {
    public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> q1 = new LinkedList<TreeLinkNode>();
        Queue<TreeLinkNode> q2 = new LinkedList<TreeLinkNode>();
        if (root == null) {
            return;
        }
        q1.add(root);
        while (!q1.isEmpty() || !q2.isEmpty()) {
            if (!q1.isEmpty()) {
                while (!q1.isEmpty()) {
                    TreeLinkNode cur = q1.remove();
                    if (cur.left != null) {
                        q2.add(cur.left);
                    }
                    if (cur.right != null) {
                        q2.add(cur.right);
                    }
                    cur.next = q1.peek();
                }
            }
            if (!q2.isEmpty()) {
                while (!q2.isEmpty()) {
                    TreeLinkNode cur = q2.remove();
                    if (cur.left != null) {
                        q1.add(cur.left);
                    }
                    if (cur.right != null) {
                        q1.add(cur.right);
                    }
                    cur.next = q2.peek();
                }
            }
        }
    }
}