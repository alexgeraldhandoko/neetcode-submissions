class Solution {
    public int findMin(int[] nums) {
        int index = helper(nums, nums.length - 1, 0);
        return nums[(index + 1) % nums.length];
    }

    public int helper(int[] nums, int hi, int lo) {
        if (lo >= hi) {
            return lo;
        }
        // Calculate mid
        int mid = (hi + lo) / 2;
        // Check if mid is peak
        int n = nums.length;
        int leftMid = ( (mid - 1) + n) % n;
        int rightMid = (mid + 1) % n;
        if (nums[mid] > nums[leftMid] && nums[mid] > nums[rightMid]) {
            return mid;
        }
        // If not peak, then recurse to the correct half
        int start = nums[0];
        if (nums[mid] < start) {
            return helper(nums, mid - 1, lo);
        } else {
            return helper(nums, hi, mid + 1);
        }
    }
}