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
        // Build an array list of the nodes, then reverse the array list
        // Just keep inserting the elements from the reversed list into the original
        // until there's a duplicate
        // When there's a duplicate, stop. Done.
        
        // Get the reverse linked list in array list format
        ArrayList<ListNode> arr = new ArrayList<>();
        ListNode originalHead = head;
        while (head != null) {
            arr.add(head);
            head = head.next;
        }
        Collections.reverse(arr);

        // Reset head to original head
        head = originalHead;

        // Execute main alg
        int cnt = 0;
        while (cnt < arr.size() && head != arr.get(cnt)) {
            // System.out.println("original: " + head.val);
            // System.out.println("reversed: " + arr.get(cnt).val);
            ListNode tmp = head.next;
            head.next = arr.get(cnt);
            arr.get(cnt).next = tmp;
            head = tmp;
            if (head == arr.get(cnt)) break;
            cnt++;
        }
        head.next = null;
    }
}