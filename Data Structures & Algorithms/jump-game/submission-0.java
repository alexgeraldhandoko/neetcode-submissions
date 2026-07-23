class Solution {
    public boolean canJump(int[] nums) {
        // If find a number that is larger then restock with that number? 
        int curr = 0;
        for (int num : nums) {
            if (curr < 0) return false;
            if (num > curr) curr = num;
            curr--;
        }
        return true;
    }
}