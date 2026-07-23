class Solution {
    int height;
    int width;
    public int uniquePaths(int m, int n) {
        // Once the robot hits the right edge, it will have no choice but keep moving down
        // Once the robot hits the bottom edge, it will have no choice but keep moving right
        // So everytime it hits one of these two edges, just add 1 to the count
        
        // That's too slow because we will visit intermediary cells many times and the number
        // of ways to reach the finish from that one cell is actually fixed, so it doesn't have
        // to be recalculated

        // Let dp[r][c] be the total number of ways that the robot can reach the finish from
        // cell (r, c)

        // This should be bottom up DP
        height = m;
        width = n;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) dp[i][j] = -1;
        }
        dp[m - 1][n - 1] = 1;
        return dippy(0, 0, dp);
    }

    public int dippy(int m, int n, int[][] dp) {
        // Base case
        if (m >= height || n >= width) return 0;

        // Tabulation case
        if (dp[m][n] >= 0) return dp[m][n];

        int waysFromBottom = dippy(m + 1, n, dp);
        int waysFromRight = dippy(m, n + 1, dp);
        return dp[m][n] = waysFromBottom + waysFromRight;
    }
}