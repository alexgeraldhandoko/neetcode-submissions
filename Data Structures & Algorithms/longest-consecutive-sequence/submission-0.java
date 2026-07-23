class Solution {
    public int longestConsecutive(int[] nums) {
        // An array of numbers can be divided into consecutive sequences
        // For the alg to run in O(n), we must visit each sequence only a constant number of times
        // Here, we aim to visit each sequence only once
        // We need to start from the smallest number in the sequence to the top number in the
        // sequence
        // We must therefore only start counting from the bottom of the sequence until
        // the top of the sequence

        // Create the hashmap
        Set<Integer> h = new HashSet<>();

        for (int num : nums) {
            h.add(num);
        }

        int longest = 0;

        for (int num : h) {
            if (!h.contains(num - 1)) { // Then this is the bottom of a consecutive sequence
                int length = 1;
                while (h.contains(num + length)) {
                    length++;
                }
                longest = Math.max(length, longest);
            }
        }

        return longest;
    }
}