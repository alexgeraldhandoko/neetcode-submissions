class Solution {
    int cnt = 0;
    int[][] dp;
    boolean debug = false;

    public int countSubstrings(String s) {
        dp = new int[s.length()][s.length()];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        recurrence(s, 0, s.length() - 1);
        if (debug) {
            System.out.println(Arrays.deepToString(dp));
        }
        return cnt;
    }

    public int recurrence(String s, int a, int b) {
        if (debug) {
            System.out.println("Assessing substring: " + s.substring(a, b + 1));
        }
        // DP Case
        if (dp[a][b] != -1) {
            return dp[a][b];
        }

        // Recurrence base cases
        // 1. If length less than or equal to 0
        if (b - a < 0) { 
            dp[a][b] = 0;
            return dp[a][b];
        } 
        // 2. If length is 1
        else if (b - a == 0) {
            dp[a][b] = 1;
            cnt++;
            return dp[a][b];
        } 
        // 3. If length is 2
        else if (b - a == 1) {
            recurrence(s, a + 1, b);
            recurrence(s, a, b - 1);
            if (s.charAt(b) == s.charAt(a)) {
                dp[a][b] = 1;
                cnt++;
                return dp[a][b];
            } else {
                dp[a][b] = 0;
                return dp[a][b];
            }
        }

        // Recurrence cases
        // 1. If the left pointer moved right
        recurrence(s, a + 1, b);
        // 2. If the right pointer moved left
        recurrence(s, a, b - 1);
        // 3. If both pointers moved together
        int res = recurrence(s, a + 1, b - 1);
        if (res == 1 && s.charAt(a) == s.charAt(b)) {
            dp[a][b] = 1;
            cnt++;
            return dp[a][b];
        } else {
            dp[a][b] = 0;
            return dp[a][b];
        }
    }
}

// A string of size 0 or less is not a palindromic substring
        // A string of size 1 is a palindromic substring
        // A string of size 2 is a palindromic substring iff the 1st and 2nd chars are the same
        
        // Check method 1:
        // Otherwise, 
        // An even sized string is palindromic iff: 
        // the first half read forwards == the second half read backwards, i.e. mirrored
        // An odd sized string is palindromic iff:
        // taking out the middle character and merging the result gives an even-sized palindromic substring

        // Check method 2:
        // Otherwise,
        // An even sized string is palindromic iff:
        // The two end characters are the same and the substring created from moving the two end pointers
        // closer together is a palindrome
        // An odd sized string is palindromic iff: 
        // The two end characters are the same and the substring created from moving the two end pointers
        // closer together is a palindrome

        // DP: Everytime before a function call returns 1 (i.e. true), just add 1 to the palindrome count
        // 1 means true
        // 0 means false
        // -1 means unset

        // Create two end pointers
        // Move the two pointers together towards each other, or one of them towards the other end
        // This denotes the substring we are considering
        // Recurse left pointer toward the right
        // Recurse right pointer toward the left
        // If moving both pointers together towards each other, make decision palindrome or not based on
        // the return result of the recurrence call and the equality of two end chars