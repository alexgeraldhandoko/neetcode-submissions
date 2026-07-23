/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    ListNode start;
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        start = head;
        return helper(true, null, head, head.next);
    }

    public boolean helper(boolean isInitial, ListNode prevNode, ListNode currNode, ListNode nextNode) {
        // If end up at the start again, then there's a loop
        if (currNode == start && !isInitial) return true;

        // If current node or its next node is null, then there's no loop
        if (currNode == null || nextNode == null) return false;

        // Reverse the direction
        currNode.next = prevNode;

        // Establish the new nodes for the next call
        ListNode newPrevNode = currNode;
        ListNode newCurrNode = nextNode;
        ListNode newNextNode = nextNode.next;

        return helper(false, newPrevNode, newCurrNode, newNextNode);
    }
}