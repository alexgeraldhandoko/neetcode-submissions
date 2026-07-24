/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        // Traverse through the linked list
        // Store the list in an array by reverse index
        // Then iterate through the array to create the reversed linked list
        
        // Try using recursion
        // Wishful thinking: I assume that I have the rest of the linked list
        boolean debug = true;
        ListNode[] master = new ListNode[1];
        reverse(head, master);
        return master[0];
    }

    public ListNode reverse(ListNode head, ListNode[] master) {
        boolean debug = true;
        ListNode ans = null;
        if (head == null) {
            if (debug) {
                System.out.println("master is assigned null");
            }
            master[0] = null;
            return null;
        }
        else if (head.next == null) {
            ans = head;
            master[0] = head;
            if (debug) {
                System.out.println("I am assigning master to value: " + head.val);
            }
            return ans;
        }
        ans = reverse(head.next, master);
        ans.next = new ListNode(head.val);
        if (debug) {
            System.out.println("I am inserting the value: " + head.val);
            System.out.println("After the value: " + ans.val);
        }
        return ans.next;
    }
}