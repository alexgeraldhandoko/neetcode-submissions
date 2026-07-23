class Solution {
    int[] dp;

    public int climbStairs(int n) {
        dp = new int[n + 1];
        for (int i = 0; i < n + 1; i++) dp[i] = -1;
        return helper(n);
    }

    public int helper(int n) {
        // Base cases
        if (n == 0) return 1;
        if (n < 0) return 0;

        // Memoized case
        if (dp[n] != -1) return dp[n];

        // Recursive case
        int left = helper(n - 1);
        int right = helper(n - 2);
        dp[n] = left + right;
        return left + right;
    }
}