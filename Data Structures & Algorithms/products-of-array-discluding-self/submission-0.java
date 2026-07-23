class Solution {
    public int[] productExceptSelf(int[] nums) {
        // Create an array of suffix product
        int[] sP = new int[nums.length];
        sP[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            sP[i] = sP[i - 1] * nums[i - 1];
        }
        // Create an array of prefix product
        int[] pP = new int[nums.length];
        pP[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            pP[i] = pP[i + 1] * nums[i + 1];
        }
        // Output the result array
        int[] r = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            r[i] = sP[i] * pP[i];
        }
        return r;
    }
}