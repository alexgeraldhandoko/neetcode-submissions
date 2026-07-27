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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Always keep a pointer that's n nodes behind it
        // The front and back pointers
        // Once the front pointer reaches the end, the back pointer
        // deletes that node

        if (head.next == null && n == 1) return null;

        ListNode frontPtr = head;
        ListNode backPtr = head;

        int len = 0;

        for (int i = 0; i < n; i++) {
            frontPtr = frontPtr.next;
        }

        // In the case where n = len of linked list, the frontPtr would be null
        // So remove the first element
        if (frontPtr == null) return head.next;

        // System.out.println("backPtr: " + backPtr.val);
        // System.out.println("frontPtr: " + frontPtr.val);

        while (frontPtr != null && frontPtr.next != null) {
            frontPtr = frontPtr.next;
            backPtr = backPtr.next;
        }

        // System.out.println("backPtr: " + backPtr.val);
        // System.out.println("frontPtr: " + frontPtr.val);

        backPtr.next = backPtr.next.next;
        return head;
    }
}