/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    boolean debug = false;
    StringBuilder sb = new StringBuilder();
    int ptr = 0;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        sb = new StringBuilder();
        serializeHelper(root);
        if (debug) {
            System.out.println("serialized tree: " + sb.toString());
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        ptr = 0;
        String[] elems = data.split(",");
        if (debug) {
            System.out.println("deserialized receives: ");
            for (String elem : elems) {
                System.out.print(elem);
            }
            System.out.println();
        }
        TreeNode res = deserializeHelper(elems);
        return res;
    }

    public void serializeHelper(TreeNode root) {
        if (root == null) {
            sb.append("N,");
            return;
        }
        sb.append(Integer.toString(root.val));
        sb.append(",");
        serializeHelper(root.left);
        serializeHelper(root.right);
    }

    public TreeNode deserializeHelper(String[] elems) {
        if (debug) {
            System.out.println("ptr: " + ptr);
        }
        String elem = elems[ptr];
        if (elem.equals("N")) {
            return null;
        } 
        TreeNode root = new TreeNode(Integer.parseInt(elem));
        ptr++;
        root.left = deserializeHelper(elems);
        ptr++;
        root.right = deserializeHelper(elems);
        if (debug) {
            System.out.println("root: " + root.val);
            if (root.left != null) System.out.println("root.left: " + root.left.val);
            else System.out.println("root.left: null");
            if (root.right != null) System.out.println("root.right: " + root.right.val);
            else System.out.println("root.right: null");
        }
        return root;
    }   
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));