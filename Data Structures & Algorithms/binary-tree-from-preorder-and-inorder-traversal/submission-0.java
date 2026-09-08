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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Helper helper = new Helper();
        TreeNode ans = helper.buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        return ans;
    }
}

class Helper {
    static boolean debug = false;
    public TreeNode buildTree(int[] preorder, int[] inorder, int preorderLow, int preorderHigh, 
            int inorderLow, int inorderHigh) {
        // Base conditions
        if (inorderHigh < inorderLow) return null;
        else if (inorderHigh == inorderLow) return new TreeNode(inorder[inorderLow], null, null);
        if (debug) {
            System.out.println(String.format("Preorder array: %d to %d", preorder[preorderLow],
                    preorder[preorderHigh]));
            System.out.println(String.format("Inorder array: %d to %d", inorder[inorderLow],
                    inorder[inorderHigh]));
        }

        // Find the current root
        int root = preorder[preorderLow];
        // Initialise all the other necessary variables
        int preorderLowLeftSubtree = 0;
        int preorderHighLeftSubtree = 0;
        int preorderLowRightSubtree = 0;
        int preorderHighRightSubtree = 0;
        
        int inorderLowLeftSubtree = 0;
        int inorderHighLeftSubtree = 0;
        int inorderLowRightSubtree = 0;
        int inorderHighRightSubtree = 0;

        // Use the current root to divide the inorder array into left and right subtree
        // If either of the subtree is of size 0, then that subtree is just null
        HashSet<Integer> elementsOfLeftSubtree = new HashSet<>();
        HashSet<Integer> elementsOfRightSubtree = new HashSet<>();
        int x = inorderLow;
        while (inorder[x] != root) {
            if (debug) {
                System.out.println("Adding " + inorder[x] + " to left subtree");
            }
            elementsOfLeftSubtree.add(inorder[x]);
            x++;
        }
        inorderLowLeftSubtree = inorderLow;
        inorderHighLeftSubtree = x - 1;
        inorderLowRightSubtree = x + 1;
        inorderHighRightSubtree = inorderHigh;
        x++;
        while (x <= inorderHigh) {
            if (debug) {
                System.out.println("Adding " + inorder[x] + " to the right subtree");
            }
            elementsOfRightSubtree.add(inorder[x]);
            x++;
        }
        // Use the elements of the right subtree to find the root of the right subtree in the preorder array
        x = preorderLow;
        while (!elementsOfRightSubtree.isEmpty() && !elementsOfRightSubtree.contains(preorder[x])) x++;
        preorderLowLeftSubtree = preorderLow + 1;
        preorderHighLeftSubtree = elementsOfRightSubtree.isEmpty() ? preorderHigh : x - 1;
        preorderLowRightSubtree = x;
        preorderHighRightSubtree = preorderHigh;
        
        // We have now obtained the preorder array for left and right subtree
        // We have also obtained the inorder array for left and right subtree
        TreeNode leftSubtree = null;
        TreeNode rightSubtree = null;
        // Time to build the left subtree
        if (elementsOfLeftSubtree.isEmpty()) leftSubtree = null;
        else leftSubtree = buildTree(preorder, inorder, preorderLowLeftSubtree, preorderHighLeftSubtree,
                inorderLowLeftSubtree, inorderHighLeftSubtree);
        // Build the right subtree
        if (elementsOfRightSubtree.isEmpty()) rightSubtree = null;
        else rightSubtree = buildTree(preorder, inorder, preorderLowRightSubtree, preorderHighRightSubtree,
                inorderLowRightSubtree, inorderHighRightSubtree);
        // Construct the tree rooted at the current root
        TreeNode ans = new TreeNode(root, leftSubtree, rightSubtree); 
        // Return the tree rooted at the current root
        return ans;
    }
}