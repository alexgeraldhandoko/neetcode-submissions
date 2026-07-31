class Solution {
    public int search(int[] nums, int target) {
        // Do binary search to find the smallest value
        // Now we have two increasing halves
        // Binary search the left half for the target
        // Binary search the right half for the target
        // If found, return the index
        // If not found, return -1

        if (nums.length == 1) {
            if (nums[0] == target) {
                return 0;
            } else {
                return -1;
            }
        }

        int lo = 0;
        int hi = nums.length - 1;
        int lowestIdx = 0;
        int ref = nums[0];

        while (true) {
            int mid = (lo + hi) / 2;

            // System.out.println("lo: " + lo);
            // System.out.println("hi: " + hi);
            // System.out.println("mid: " + mid);

            if (lo == hi) {
                lowestIdx = lo;
                break;
            }

            if (Math.abs(lo - hi) == 1) {
                if (nums[lo] > nums[hi]) lowestIdx = hi;
                else if (nums[hi] > nums[lo]) lowestIdx = lo;
                break;
            }

            if (mid < nums.length && nums[mid] > nums[mid + 1]) {
                lowestIdx = mid + 1;
                break;
            }

            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                lowestIdx = mid;
                break;
            }

            if (nums[mid] > ref) lo = mid + 1;
            else hi = mid - 1;
        }

        // System.out.println("lowestIdx: " + lowestIdx);

        int idx1 = Arrays.binarySearch(nums, 0, lowestIdx, target);
        int idx2 = Arrays.binarySearch(nums, lowestIdx, nums.length, target);

        // System.out.println("idx1: " + idx1);
        // System.out.println("idx2: " + idx2);

        if (idx1 < 0 && idx2 < 0) return -1;
        if (idx1 < 0) return idx2;
        if (idx2 < 0) return idx1;
        return -1;
    }
}