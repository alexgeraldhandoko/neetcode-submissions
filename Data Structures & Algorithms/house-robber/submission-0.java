class Solution {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) dp[i] = -1;
        return helper(nums, dp, nums.length - 1);
    }

    public int helper(int[] nums, int[] dp, int idx) {
        // At each house, we have two choices:
        // 1. Rob that house and then skip two houses forward
        // 2. Don't rob that house and move on to the next house
        // 3. Return the max result

        // Top down DP

        // Base case
        if (idx < 0) return 0;

        // DP tabulation case
        if (dp[idx] >= 0) return dp[idx];

        return dp[idx] = Math.max(helper(nums, dp, idx - 1),
            helper(nums, dp, idx - 2) + nums[idx]);
    }
}