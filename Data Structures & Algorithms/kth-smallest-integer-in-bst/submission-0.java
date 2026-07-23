/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    ArrayList<Integer> arr = new ArrayList<>();

    public int kthSmallest(TreeNode root, int k) {
        // Do in-order traversal of the BST, then index into the (k + 1)-th index
        // This takes O(n)
        bstToSortedArray(root);
        return arr.get(k - 1);
    }

    public void bstToSortedArray(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            arr.add(root.val); return;
        }
        if (root.left != null) bstToSortedArray(root.left);
        arr.add(root.val);
        if (root.right != null) bstToSortedArray(root.right);
    }
}