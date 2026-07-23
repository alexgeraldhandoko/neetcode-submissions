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
    public void reorderList(ListNode head) {
        // Split into two halves, if odd length then the first half is the shorter one
        // Reverse the second half
        // From the reversed second half, insert into the gaps of the first half

        if (head == null || head.next == null) return;

        // Count the number of elements in linked list
        int n = 0;
        ListNode originalHead = head;
        while (head != null) {
            n++;
            head = head.next;
        }
        head = originalHead;

        // Go to the second half
        int half = (n / 2);
        int cnt = 0;
        while (head != null && cnt < half) {
            head = head.next;
            cnt++;
        }
        ListNode borderHead = head;
        head = originalHead;

        // Separate the two halves
        cnt = 1;
        while (cnt < half) {
            head = head.next;
            cnt++;
        }
        head.next = null;
        head = originalHead;

        // Reverse the second half
        ListNode originalBorderHead = borderHead;
        System.out.println("original border head is: " + borderHead.val);
        ListNode prev = null;
        while (borderHead != null) {
            ListNode originalNext = borderHead.next;
            borderHead.next = prev;
            prev = borderHead;
            borderHead = originalNext;
        }
        borderHead = prev;

        // Inspecting the contents of the second half
        ListNode inspect = borderHead;
        System.out.println("Inspecting the contents of the second half: ");
        while (inspect != null) {
            System.out.println(inspect.val);
            inspect = inspect.next;
        }

        // Insert the second half into the first half
        while (head != null) {
            ListNode originalHeadNext = head.next;
            ListNode originalBorderNext = borderHead.next;

            head.next = borderHead;
            borderHead.next = originalHeadNext == null ? borderHead.next : originalHeadNext;

            head = originalHeadNext;
            borderHead = originalBorderNext;
        }
    }
}