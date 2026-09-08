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
    public ListNode mergeKLists(ListNode[] lists) {
        // Maintain a heap from the first elements of the linked list
        // Then keep selecting the next smallest element
        
        // Edge cases
        if (lists.length == 0 || lists == null) {
            return null;
        }

        // Prepare data structures
        int k = lists.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        HashMap<Integer, ArrayList<ListNode>> map = new HashMap<>();

        // Populate the pq and arr with the first linked list elements
        for (int i = 0; i < k; i++) {
            if (lists[i] != null) {
                int num = lists[i].val;
                pq.offer(num);
                if (!map.containsKey(num)) map.put(num, new ArrayList<>());
                map.get(num).add(lists[i]);
            }
        }

        ListNode masterList = new ListNode();
        ListNode masterListHead = masterList;
        while (!pq.isEmpty()) {
            // Get the next smallest number from the pq
            int next_smallest_num = pq.poll();
            // Get a node that contains that number from the map
            ListNode node = map.get(next_smallest_num).get(0);
            // Append that node to a master linked list
            masterList.next = node;
            masterList = masterList.next;
            // Remove that node from the map
            map.get(next_smallest_num).remove(0);
            // If that node has a next node
            if (node.next != null) {
                // Get that node's next node
                ListNode nextNode = node.next;
                // Put that next node into the map and the pq
                pq.offer(nextNode.val);
                if (!map.containsKey(nextNode.val)) map.put(nextNode.val, new ArrayList<>());
                map.get(nextNode.val).add(nextNode);
            }
        }
        return masterListHead.next;
    }
}