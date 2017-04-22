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
        if (root == null) {
            return;
        }
        q1.add(root);
        while (!q1.isEmpty()) {
            TreeLinkNode cur = q1.remove();
            if (cur.left == null) {
                break;
            }
            cur.left.next = cur.right;
            if (cur.next != null) {
                cur.right.next = cur.next.left;
            }
            q1.add(cur.left);
            q1.add(cur.right);
        }
    }
}