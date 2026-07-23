class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) dp[i][j] = -1;
        }
        return dippy(text1, text2, dp, m - 1, n - 1);
    }

    public int dippy(String text1, String text2, int[][] dp, int m, int n) {
        // Base case
        if (m < 0 || n < 0) return 0;

        // Tabulation case
        if (dp[m][n] >= 0) return dp[m][n];
        
        // If the 2 characters match, then increment and ignore the 2 characters
        if (text1.charAt(m) == text2.charAt(n)) {
            return dp[m][n] = dippy(text1, text2, dp, m - 1, n - 1) + 1;
        }

        // If the 2 characters don't match, then we have two possibilities
        return dp[m][n] = Math.max(dippy(text1, text2, dp, m - 1, n),
            dippy(text1, text2, dp, m, n - 1));
    }
}