class Solution {
    int start = 0;
    int end = 0;

    public int rob(int[] nums) {
        end = nums.length - 1;
        int[][] dp = new int[nums.length][2];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = Integer.MIN_VALUE;
            dp[i][1] = Integer.MIN_VALUE;
        }
        return helper(nums, dp, nums.length - 1, 0);
    }

    public int helper(int[] nums, int[][] dp, int idx, int isEndTaken) {
        // Base case
        if (idx < 0) return 0;

        // Tabulation case
        if (dp[idx][isEndTaken] >= 0) return dp[idx][isEndTaken];
        
        // If at the end
        if (idx == end) {
            int robThisHouse = helper(nums, dp, idx - 2, 1) + nums[idx];
            int dontRobThisHouse = helper(nums, dp, idx - 1, 0);
            return dp[idx][isEndTaken] = Math.max(robThisHouse, dontRobThisHouse);
        }

        // If at the start
        if (idx == start) {
            if (isEndTaken == 1) return 0;
            return dp[idx][isEndTaken] = nums[idx];
        }

        // If somewhere in the middle
        int robThisHouse = helper(nums, dp, idx - 2, isEndTaken) + nums[idx];
        int dontRobThisHouse = helper(nums, dp, idx - 1, isEndTaken);
        return dp[idx][isEndTaken] = Math.max(robThisHouse, dontRobThisHouse);
    }
}