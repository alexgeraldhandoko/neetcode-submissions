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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        boolean debug = false;
        if (list1 == null && list2 == null) {
            return null;
        }
        else if (list1 == null) {
            return list2;
        }
        else if (list2 == null) {
            return list1;
        }
        // Initialise the return list
        ListNode ans = null;
        if (list1.val <= list2.val) {
            ans = new ListNode(list1.val);
            list1 = list1.next;
        }
        else {
            ans = new ListNode(list2.val);
            list2 = list2.next;
        }
        ListNode p = ans; // Pointer helper
        // Populate the return list
        while (list1 != null && list2 != null) {
            if (debug) {
                System.out.println("list1: " + list1.val + ", list2: " + list2.val);
            }
            if (list1.val <= list2.val) {
                p.next = new ListNode(list1.val);
                p = p.next;
                list1 = list1.next;
            }
            else {
                p.next = new ListNode(list2.val);
                p = p.next;
                list2 = list2.next;
            }
        }
        if (debug) {
            System.out.println("This is the list2's value: " + list2.val);
        }
        // Fill in the leftovers
        if (list1 != null) {
            while (list1 != null) {
                p.next = new ListNode(list1.val);
                p = p.next;
                list1 = list1.next;
            }
        }
        else if (list2 != null) {
            while (list2 != null) {
                if (debug) {
                    System.out.println("This is the leftover from list2 that I" +
                        " am inserting: " + list2.val);
                }
                p.next = new ListNode(list2.val);
                p = p.next;
                list2 = list2.next;
            }
        }
        return ans;
    }
}