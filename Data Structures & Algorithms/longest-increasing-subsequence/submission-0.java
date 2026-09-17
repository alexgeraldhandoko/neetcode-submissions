class Solution {
    boolean debug = false;
    int[] nums;
    int[][] dp;

    public int lengthOfLIS(int[] nums) {
        this.nums = nums;
        // The idx will be used as both the number of possible integers and the 
        // max ptr value
        dp = new int[nums.length + 1][nums.length]; 
        for (int i = 0; i < nums.length + 1; i++) {
            for (int j = 0; j < nums.length; j++) {
                dp[i][j] = -1;
            }
        }
        return recurrence(nums.length, nums.length - 1);
    }

    public int recurrence(int lastIntIdx, int ptr) {
        if (ptr < 0) {
            return 0;
        }
        
        if (dp[lastIntIdx][ptr] != -1) return dp[lastIntIdx][ptr];

        if (lastIntIdx == nums.length) {
            return Math.max(
                recurrence(ptr, ptr - 1) + 1,
                recurrence(lastIntIdx, ptr - 1)
            );
        }

        int lastInt = nums[lastIntIdx];
        int currInt = nums[ptr];

        if (lastInt > currInt) {
            if (debug) {
                System.out.println("-------------------------");
                System.out.println("lastInt: " + lastInt);
                System.out.println("currInt: " + currInt);
                System.out.println("-------------------------");
            }
            dp[lastIntIdx][ptr] = Math.max(
                recurrence(ptr, ptr - 1) + 1,
                recurrence(lastIntIdx, ptr - 1)
            );
            return dp[lastIntIdx][ptr];
        } else {
            if (debug) {
                System.out.println("-------------------------");
                System.out.println("lastInt: " + lastInt);
                System.out.println("currInt: " + currInt);
                System.out.println("-------------------------");
            }
            dp[lastIntIdx][ptr] = recurrence(lastIntIdx, ptr - 1);
            return dp[lastIntIdx][ptr];
        }
    }
}