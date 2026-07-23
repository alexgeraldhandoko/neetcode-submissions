class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Find the place to insert the new interval first
        // Then merge the intervals until find an end that fits without overlapping
        // Put everything in array list first
        if (intervals.length == 0) return new int[][]{{newInterval[0], newInterval[1]}};
        ArrayList<int[]> intervalList = new ArrayList<>();
        for (int[] interval : intervals) intervalList.add(interval);

        // Then insert the new interval into the arraylist
        int insertIdx = 0;
        for (int i = 0; i < intervalList.size(); i++) {
            int[] currInterval = intervalList.get(i);
            if (newInterval[0] < currInterval[0]) {
                intervalList.add(i, newInterval);
                insertIdx = i;
                break;
            }
            if (i == intervalList.size() - 1) {
                intervalList.add(newInterval);
                break;
            }
        }

        // Then merge intervals until no overlap
        int loopVar = 0;
        while (loopVar < intervalList.size() - 1) {
            int[] currentInterval = intervalList.get(loopVar);
            int[] nextInterval = intervalList.get(loopVar + 1);
            if (currentInterval[1] >= nextInterval[0]) {
                currentInterval[1] = Math.max(currentInterval[1], nextInterval[1]);
                intervalList.remove(loopVar + 1);
                continue;
            }
            loopVar++;
        }

        int[][] out = new int[intervalList.size()][2];
        for (int i = 0; i < out.length; i++) {
            out[i][0] = intervalList.get(i)[0];
            out[i][1] = intervalList.get(i)[1];
        }

        return out;

    }
}