/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class convertBST {
    public TreeNode convertBST(TreeNode root) {
        int[] sum = new int[1];
        sum[0] = 0;
        convertBST(root, sum);
        return root;
    }
    private void convertBST(TreeNode root, int[] sum) {
        if (root == null) {
            return;
        }
        convertBST(root.right, sum);
        sum[0] += root.val;
        root.val = sum[0];
        convertBST(root.left, sum);
    }
}