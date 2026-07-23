// First attempt:
// The longest substring could be A, B, C, etc.
// So search for A first. The rest are gaps.
// If k = 5,
// Then the longest possible substring of As would be one of the connected
// substrings with 5 gaps between them.
// Then just remove the first one and then account the next one
// Out of all those, choose the longest number attained. Call this localMax1
// Repeat for other alphabets until localMax26
// Then choose max(localMax1, localMax2, ..., localMax26)
// Use the gap's indexes to calculate the substring length

// Improve it by putting all the distinct alphabets in s.string in a set. This
// set size will be the outer loop and will be O(26).

// Time complexity: 26 * s.length

// The first index to be added to indexes should be the index of the furthest
// character to the left of the first character that is equal to the alphabet

class Solution {
    public int characterReplacement(String s, int k) {
        // Find the set of alphabets in the string
        HashSet<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) set.add(c);

        // Create the max variable
        int max = Integer.MIN_VALUE;

        // Find the longest possible substring for each alphabet
        for (char c : set) {
            // System.out.println("Alphabet: " + c);
            
            // Always start at -1th index, so always offset startIdx 
            // by 1 to the right
            ArrayList<Integer> indexes = new ArrayList<>();
            indexes.add(-1);

            // Then add the indexes of all the characters not equal to alphabet
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != c) indexes.add(i);
            }

            // If we have more k than indexes, then we can make every character
            // be the same alphabet
            if (k + 1 >= indexes.size()) return s.length();

            // Iterate through the indexes
            int substringLength = 0;
            int i = 0;
            for (i = 0; i + k + 1 < indexes.size(); i++) {
                int startIdx = indexes.get(i) + 1;
                int endIdx = indexes.get(i + k + 1) - 1;
                substringLength = endIdx - startIdx + 1;

                if (substringLength > max) max = substringLength;
            }

            // Need to do one last check from the last i up until the last
            // char equal to the alphabet
            // This is because the last char could be way beyond 
            // indexes.get(i + k + 1), so there could still be a longer substring
            
            // So find the last index equal to the alphabet
            int lastIdxEqualToAlphabet = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == c) lastIdxEqualToAlphabet = j;
            }

            // Calculate the last substring length to check
            int startIdx = indexes.get(i) + 1;
            int endIdx = Math.max(indexes.get(i + k) - 1, 
                lastIdxEqualToAlphabet);
            substringLength = endIdx - startIdx + 1;
            if (substringLength > max) max = substringLength;
        }
        return max;
    }
}