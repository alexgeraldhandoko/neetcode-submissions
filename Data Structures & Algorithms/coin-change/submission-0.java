class Solution {
    int[][] dp;
    boolean debug = false;

    public int coinChange(int[] coins, int amount) {
        // At each point, either take again or don't take
        // But can only take if it doesn't cause the amount to be negative
        // Take the minimum
        dp = new int[amount + 1][coins.length];
        for (int[] row : dp) {
            Arrays.fill(row, -2);
        }
        recurrence(coins, amount, coins.length - 1);
        return dp[amount][coins.length - 1];
    }

    public int recurrence(int[] coins, int amount, int ptr) {
        // Base case
        if (ptr < 0 || amount < 0) {
            return -1;
        }

        // DP memoization
        if (dp[amount][ptr] != -2) {
            return dp[amount][ptr];
        }

        if (amount == 0) {
             if (debug) {
                System.out.println("amount: " + amount);
                System.out.println("ptr: " + ptr);
            }
            dp[amount][ptr] = 0;
            return dp[amount][ptr];
        }
        
        if (debug) System.out.println("test");

        // Recurrence cases
        int takeRes = recurrence(coins, amount - coins[ptr], ptr);
        int noTakeRes = recurrence(coins, amount, ptr - 1);
        if (takeRes == -1 && noTakeRes == -1) {
            dp[amount][ptr] = -1;
            return dp[amount][ptr];
        } else if (takeRes == -1) {
            dp[amount][ptr] = noTakeRes;
            return dp[amount][ptr];
        } else if (noTakeRes == -1) {
            dp[amount][ptr] = takeRes + 1;
            return dp[amount][ptr];
        } else {
            dp[amount][ptr] = Math.min(takeRes + 1, noTakeRes);
            return dp[amount][ptr];
        }
    }
}

// Define recurrence(int[] coins, amount, ptr) as the min. no. of coins needed to return the
// amount