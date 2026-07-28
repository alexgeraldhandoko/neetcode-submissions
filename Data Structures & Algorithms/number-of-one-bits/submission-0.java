class Solution {
    public int hammingWeight(int n) {
        // Take last bit 
        // Examine if it is 1
        // Shift right
        // Repeat
        int cnt = 0;
        while (n > 0) {
            if (n % 2 != 0) cnt++;
            n = n / 2;
        }
        return cnt;
    }
}