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
    ArrayList<List<Integer>> finalList = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        q.offer(root);
        helper();
        return finalList;
    }

    public void helper() {
        // While finishing current queue of nodes
        // Create a next level queue of nodes and a list of them
        // When current queue is finished, store that list in a list
        Queue<TreeNode> newQ = new LinkedList<>();
        List<Integer> arr = new ArrayList<>();
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) continue;
            arr.add(node.val);
            newQ.add(node.left);
            newQ.add(node.right);
        }
        if (!arr.isEmpty()) finalList.add(arr);
        q = newQ;
        if (!q.isEmpty()) helper();
    }
}