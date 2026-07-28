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
    boolean debug = true;
    // Each node needs to be larger than the largest element in the left subtree
    // Each node needs to be smaller than the smallest element in the right subtree
    // The elements in the left subtree can be put inside a max heap
    // The elements in the right subtree can be put inside a min heap
    // Both heaps progressively get larger
    // We need a post-order traversal
    // Why? Because we need to gather all the elements of the left subtree first
    // Then gather all the elements of the right subtree
    // Then we need to compare these elements with the current node

    // The heap implementation doesn't work because the min heap may need to be transferred to the
    // max heap and vice versa
    // Plus it's a "global" data structure and only builds from the bottom up only on the left subtree
    // of the root
    // It might be possible but is quite complicated and problematic
    
    // What if we do in-order traversal to check if the next value is larger than the previous value?
    // If we get a sorted order of elements, then it should be a correct BST right?

    public boolean isValidBST(TreeNode root) {
        ArrayList<Integer> arr = new ArrayList<>();
        Helper.inOrderTraversal(root, arr);
        int prev = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) <= prev) return false;
            prev = arr.get(i);
        }
        return true;
    }
}

class Helper {
    public static void inOrderTraversal(TreeNode root, ArrayList<Integer> arr) {
        if (root.left != null) inOrderTraversal(root.left, arr);
        arr.add(root.val);
        if (root.right != null) inOrderTraversal(root.right, arr);
    }
}