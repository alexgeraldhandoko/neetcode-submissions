class Solution {
    String str;
    int max = Integer.MIN_VALUE;
    int globalFwd;
    int globalBwd;

    public String longestPalindrome(String s) {
        if (s.length() == 1) return s;
        
        // Handle odd
        for (int i = 0; i < s.length(); i++) {
            expandOdd(i, s);
        }

        // Handle even
        for (int i = 0; i < s.length(); i++) {
            expandEven(i, s);
        }

        return s.substring(Math.max(0, globalBwd), Math.min(globalFwd, s.length()));
        
    }

    public void expandOdd(int center, String s) {
        int fwdPtr = center;
        int bwdPtr = center;

        while (fwdPtr < s.length() && bwdPtr >= 0) {
            if (s.charAt(fwdPtr) == s.charAt(bwdPtr)) {
                fwdPtr++;
                bwdPtr--;
            } else {
                int potentialMax = fwdPtr - bwdPtr - 1;
                if (potentialMax > max) {
                    globalFwd = fwdPtr;
                    globalBwd = bwdPtr + 1;
                    max = potentialMax;
                }
                return;
            }
        }
        int potentialMax = fwdPtr - bwdPtr - 1;
        if (potentialMax > max) {
            globalFwd = fwdPtr;
            globalBwd = bwdPtr + 1;
            max = potentialMax;
        }
    }

    public void expandEven(int center, String s) {
        if (s.length() == 2) {
            if (s.charAt(0) == s.charAt(1)) {
                globalFwd = 2;
                globalBwd = 0;
            } else {
                globalBwd = 0;
                globalFwd = 1;
            }
        }
        
        int fwdPtr = center + 1;
        int bwdPtr = center;

        while (fwdPtr < s.length() && bwdPtr >= 0) {
            if (s.charAt(fwdPtr) == s.charAt(bwdPtr)) {
                fwdPtr++;
                bwdPtr--;
            } else {
                int potentialMax = fwdPtr - bwdPtr - 1;
                if (potentialMax > max) {
                    globalFwd = fwdPtr;
                    globalBwd = bwdPtr + 1;
                    max = potentialMax;
                }
                return;
            }
        }

        int potentialMax = fwdPtr - bwdPtr - 1;
        if (potentialMax > max) {
            globalFwd = fwdPtr;
            globalBwd = bwdPtr + 1;
            max = potentialMax;
        }
    }
}